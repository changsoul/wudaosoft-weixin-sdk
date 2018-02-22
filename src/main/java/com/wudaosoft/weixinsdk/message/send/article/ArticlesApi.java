/* Copyright(c)2010-2015 WUDAOSOFT.COM
 * Email:changsoul.wu@gmail.com
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.message.send.article;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.GlobalReturnCode;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2015-8-9 下午11:41:31
 */
public class ArticlesApi {
	
	private WeiXinConfig wxConf;

	public ArticlesApi(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	public GlobalReturnCode articlesUpload(Articles articles) {
		
		return articlesUploadByJsonString(JSON.toJSONString(articles, SerializerFeature.WriteNullStringAsEmpty));
	}
	
	public GlobalReturnCode articlesUploadToMaterial(Articles articles) {
		
		return articlesUploadToMaterialByJsonString(JSON.toJSONString(articles, SerializerFeature.WriteNullStringAsEmpty));
	}
	
	public GlobalReturnCode articlesUpdate(String mediaId, String index, Article article) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("media_id", mediaId);
		map.put("index", index);
		map.put("articles", article);
		
		return articlesUpdateByJsonString(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
	}
	
	public GlobalReturnCode articlesUploadByJsonString(String jsonString) {
		
		String url = ApiUrlConstants.ARTICLES_UPLOAD+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, jsonString);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode articlesUploadToMaterialByJsonString(String jsonString) {
		
		String url = ApiUrlConstants.ARTICLES_UPLOAD_TO_MATERIAL+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, jsonString);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode articlesUpdateByJsonString(String jsonString) {
		
		String url = ApiUrlConstants.ARTICLES_MATERIAL_UPDATE_NEWS+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, jsonString);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode articlesSend(String mediaId) {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		filter.put("is_to_all", true);
//		filter.put("group_id", "100");
		
		Map<String, Object> mpnews = new HashMap<String, Object>();
		
		mpnews.put("media_id", mediaId);
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("filter", filter);
		data.put("mpnews", mpnews);
		data.put("msgtype", "mpnews");
		
		
		String url = ApiUrlConstants.ARTICLES_SEND+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode articlesPreviewToOpenId(String openId, String mediaId) {
		
		Map<String, Object> mpnews = new HashMap<String, Object>();
		
		mpnews.put("media_id", mediaId);
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("touser", openId);
		data.put("mpnews", mpnews);
		data.put("msgtype", "mpnews");
		
		String url = ApiUrlConstants.ARTICLES_PREVIEW+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode articlesPreviewToWxUsername(String wxUsername, String mediaId) {
		
		Map<String, Object> mpnews = new HashMap<String, Object>();
		
		mpnews.put("media_id", mediaId);
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("towxname", wxUsername);
		data.put("mpnews", mpnews);
		data.put("msgtype", "mpnews");
		
		String url = ApiUrlConstants.ARTICLES_PREVIEW+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}

}
