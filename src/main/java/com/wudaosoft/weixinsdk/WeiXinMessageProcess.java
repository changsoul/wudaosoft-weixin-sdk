/**
 *    Copyright 2009-2018 Wudao Software Studio(wudaosoft.com)
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.wudaosoft.weixinsdk;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

import com.wudaosoft.weixinsdk.aes.AesException;
import com.wudaosoft.weixinsdk.aes.WXBizMsgCrypt;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.exception.WeiXinException;
import com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler;
import com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveImageMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLinkMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLocationMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveTextMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVideoMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVoiceMsg;
import com.wudaosoft.weixinsdk.type.EventType;
import com.wudaosoft.weixinsdk.type.MsgType;
import com.wudaosoft.weixinsdk.utils.XmlUtils;
import com.wudaosoft.weixinsdk.xml.XmlObject;

/**
 * <p>微信服务器回调处理 </p>
 * <p>请实现WeiXinMessageHandler接口,用于处理和响应消息 </p>
 * @author Changsoul.Wu
 * @date 2014年3月31日 上午9:54:02
 */
public class WeiXinMessageProcess {
	
	private static final Logger log = LoggerFactory.getLogger(WeiXinMessageProcess.class);
	
	private static final String ENCRYPT_TYPE_AES = "aes";

	private WeiXinConfig wxConf;
	private WeiXinMessageHandler messageHandler;
	
	public WeiXinMessageProcess(WeiXinConfig config, WeiXinMessageHandler messageHandler) {
		Assert.notNull(config, "The WeiXinConfig must not be null");
		Assert.hasText(config.getToken(), "The 'sign token' must not be empty");
		Assert.notNull(messageHandler, "The WeiXinMessageHandler must not be null");
		this.wxConf = config;
		this.messageHandler = messageHandler;
	}

	public void setWeixinConfig(WeiXinConfig config) {
		this.wxConf = config;
	}
	
	public void setMessageHandler(WeiXinMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}

	/**
	 * 回调消息处理入口
	 * @param request
	 * @param msgSignature 
	 * @param encryptType 
	 * @param messageHandler
	 * @return
	 * @throws IOException
	 * @throws WeiXinException
	 * @throws AesException 
	 */
	public String processRequest(HttpServletRequest request) throws IOException, WeiXinException, AesException{
		
		String respXML = null;
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String encryptType = request.getParameter("encrypt_type");
		String msgSignature = request.getParameter("msg_signature");
		
		if(!ENCRYPT_TYPE_AES.equals(encryptType)) {
			if(!CommonApi.checkSignature(wxConf.getToken(), signature, timestamp, nonce)){
				return respXML;
			}
		}
		
		Document doc = XmlUtils.readXml(request.getInputStream());
		
		if (doc != null) {
			
			XmlObject req = XmlObject.fromDocument(doc);
			
			if(ENCRYPT_TYPE_AES.equals(encryptType)) {
				String fromEncryptMsg = req.getString("Encrypt");
				
				if(fromEncryptMsg == null || fromEncryptMsg.trim().length() == 0)
					return respXML;
				
				String decryptedMsg = WXBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, fromEncryptMsg, wxConf);
				
				req = XmlObject.fromDocument(XmlUtils.readXml(decryptedMsg));
			}
			
			String msgTypeStr = req.getString("MsgType");
			
			log.debug("Receive msgType:" + msgTypeStr);
			
			if(msgTypeStr != null){
				
				MsgType msgType = MsgType.getMsgTypeByName(msgTypeStr);
				
				if(msgType == null)
					return respXML;
				
				switch (msgType) {
				case text:
					respXML = messageHandler.processTextMsg(req.buildWeiXinBean(ReceiveTextMsg.class));
					break;
					
				case image:
					respXML = messageHandler.processImageMsg(req.buildWeiXinBean(ReceiveImageMsg.class));
					break;
					
				case voice:
					respXML = messageHandler.processVoiceMsg(req.buildWeiXinBean(ReceiveVoiceMsg.class));
					break;
					
				case event:
					respXML = processEventMsg(req.buildWeiXinBean(ReceiveEventMsg.class));
					break;
					
				case video:
					respXML = messageHandler.processVideoMsg(req.buildWeiXinBean(ReceiveVideoMsg.class));
					break;
					
				case location:
					respXML = messageHandler.processLocationMsg(req.buildWeiXinBean(ReceiveLocationMsg.class));
					break;
					
				case link:
					respXML = messageHandler.processLinkMsg(req.buildWeiXinBean(ReceiveLinkMsg.class));
					break;
	
				default:
					break;
				}
			}
			
			if(ENCRYPT_TYPE_AES.equals(encryptType) && respXML != null && respXML.trim().length() != 0) {
				
				respXML = WXBizMsgCrypt.encryptMsg(respXML, CommonApi.create_timestamp(), CommonApi.create_nonce_str(), wxConf);
			}
		}
		
		return respXML;
	}

	/**
	 * 事件处理入口
	 * @param ReceiveEventMsg
	 * @return
	 */
	private String processEventMsg(ReceiveEventMsg eventMsg) {
		
		String respXML = null;
		
		if (eventMsg != null) {
			log.debug("Receive eventType:" + eventMsg.getEvent());
			
			EventType eventType = EventType.getEventTypeByName(eventMsg.getEvent());
			
			if(eventType == null)
				return respXML;
			
			switch (eventType) {
			case subscribe:
				respXML = messageHandler.processSubscribeEvent(eventMsg);
				break;
			case unsubscribe:
				respXML = messageHandler.processUnsubscribeEvent(eventMsg);
				break;
			case CLICK:
				respXML = messageHandler.processClickEvent(eventMsg);
				break;
			case VIEW:
				respXML = messageHandler.processViewEvent(eventMsg);
				break;
			case SCAN:
				respXML = messageHandler.processScanEvent(eventMsg);
				break;
			case LOCATION:
				respXML = messageHandler.processLocationEvent(eventMsg);
				break;
			default:
				break;
			}
		}
		
		return respXML;
	}

}
