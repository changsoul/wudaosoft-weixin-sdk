/* 
 * Copyright(c)2010-2018 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.handler;

import com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveImageMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLinkMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLocationMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveTextMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVideoMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVoiceMsg;

/** 
 * @author Changsoul Wu
 * 
 */
public abstract class WeiXinMessageHandlerAdapter implements WeiXinMessageHandler {

	
	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processTextMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveTextMsg)
	 */
	@Override
	public String processTextMsg(ReceiveTextMsg textMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processImageMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveImageMsg)
	 */
	@Override
	public String processImageMsg(ReceiveImageMsg imageMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processVoiceMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveVoiceMsg)
	 */
	@Override
	public String processVoiceMsg(ReceiveVoiceMsg voiceMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processVideoMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveVideoMsg)
	 */
	@Override
	public String processVideoMsg(ReceiveVideoMsg videoMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processLocationMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveLocationMsg)
	 */
	@Override
	public String processLocationMsg(ReceiveLocationMsg locationMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processLinkMsg(com.wudaosoft.weixinsdk.message.receive.ReceiveLinkMsg)
	 */
	@Override
	public String processLinkMsg(ReceiveLinkMsg linkMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processSubscribeEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processSubscribeEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processUnsubscribeEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processUnsubscribeEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processClickEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processClickEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processViewEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processViewEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processScanEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processScanEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler#processLocationEvent(com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg)
	 */
	@Override
	public String processLocationEvent(ReceiveEventMsg eventMsg) {
		return null;
	}

}
