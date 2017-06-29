/* Copyright(c)2010-2015 WUDAOSOFT.COM
 * Email:changsoul.wu@gmail.com
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.material;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.CommonApi;
import com.wudaosoft.weixinsdk.GlobalReturnCode;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;
import com.wudaosoft.weixinsdk.type.MediaType;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2015-8-10 上午1:45:40
 */
public class MaterialApi {
	
	
	public static String addImageMaterial(File media) {
		
		return CommonApi.mediaUploadForever(MediaType.image, media);
	}
	
	public static String addImageMaterialThumb(File media) {
		
		return CommonApi.mediaUploadForever(MediaType.thumb, media);
	}
	
	public static GlobalReturnCode getMaterial(String mediaId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("media_id", mediaId);
		
		String url = ApiUrlConstants.GET_MATERIAL+"?access_token="+CommonApi.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public static GlobalReturnCode delMaterial(String mediaId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("media_id", mediaId);
		
		String url = ApiUrlConstants.DEL_MATERIAL+"?access_token="+CommonApi.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public static GlobalReturnCode getMaterialCount() {
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("access_token", CommonApi.getAccessToken());
		
		String url = ApiUrlConstants.GET_MATERIAL_COUNT;
		
		JSONObject resp = HttpClientUtils.getForJsonResult(url, params);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public static GlobalReturnCode batchgetMaterial(MediaType type, int offset, int count) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("type", type);
		data.put("offset", offset);
		data.put("count", count);
		
		String url = ApiUrlConstants.BATCHGET_MATERIAL+"?access_token="+CommonApi.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}

}
