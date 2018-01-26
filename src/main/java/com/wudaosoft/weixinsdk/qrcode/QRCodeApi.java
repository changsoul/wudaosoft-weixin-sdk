/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.qrcode;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;

/**
 * <p>生成带参数的二维码 </p>
 * @author Changsoul.Wu
 * @date 2014年4月3日 下午6:24:16
 */
public class QRCodeApi {
	
	private final Logger log = LoggerFactory.getLogger(QRCodeApi.class);
	private final String QR_SCENE = "QR_SCENE"; // 临时
	private final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE"; //永久

	private WeiXinConfig wxConf;

	public QRCodeApi(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	/**
	 * 生成永久二维码
	 * 无过期时间，数量较少（目前参数只支持1--100000）
	 * @param sceneId 非0整型，最大值为100000（目前参数只支持1--100000）
	 * @return QRCode
	 */
	public QRCode createQRCode(int sceneId){
		return createQRCode(QR_LIMIT_SCENE, sceneId, 0);
	}
	
	/**
	 * 生成临时二维码
	 * 最大为1800秒，但能够生成较多数量
	 * @param sceneId 32位非0整型
	 * @param expireSeconds 最大为1800秒
	 * @return QRCode
	 */
	public QRCode createTempQRCode(int sceneId, int expireSeconds){
		return createQRCode(QR_SCENE, sceneId, expireSeconds);
	}
	
	public String showQRCode(String ticket) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ticket", ticket);
		
		String url = HttpClientUtils.buildReqUrl(ApiUrlConstants.QRCODE_SHOW, params);
		return url;
	}
	
	private QRCode createQRCode(String actionName, int sceneId, int expireSeconds){
		JSONObject req = new JSONObject();
		JSONObject sub = new JSONObject();
		JSONObject thr = new JSONObject();
		
		thr.put("scene_id", sceneId);
		sub.put("scene", thr);
		
		if(expireSeconds != 0)
			req.put("expire_seconds", expireSeconds);
		
		req.put("action_name", actionName);
		req.put("action_info", sub);
		
		String url = ApiUrlConstants.QRCODE_CREATE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		QRCode qrc = new QRCode();
		
		if(resp != null) {
			try {
				qrc = JSON.toJavaObject(resp, QRCode.class);
				if(qrc != null && qrc.getTicket() != null)
					qrc.setUrl(showQRCode(qrc.getTicket()));
				return qrc;
			} catch (Exception e) {
				qrc.systemError();
				log.error(e.getMessage(), e);
			}
		}else{
			qrc.systemError();
		}
		return qrc;
	}
}
