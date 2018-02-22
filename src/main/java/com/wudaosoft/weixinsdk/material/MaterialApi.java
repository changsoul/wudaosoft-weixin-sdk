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
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.type.MediaType;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2015-8-10 上午1:45:40
 */
public class MaterialApi {
	
	private WeiXinConfig wxConf;
	
	public MaterialApi(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}

	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	public String addImageMaterial(File media) {
		
		return CommonApi.mediaUploadForever(MediaType.image, media, wxConf);
	}
	
	public String addImageMaterialThumb(File media) {
		
		return CommonApi.mediaUploadForever(MediaType.thumb, media, wxConf);
	}
	
	public GlobalReturnCode getMaterial(String mediaId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("media_id", mediaId);
		
		String url = ApiUrlConstants.GET_MATERIAL+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode delMaterial(String mediaId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("media_id", mediaId);
		
		String url = ApiUrlConstants.DEL_MATERIAL+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.get(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode getMaterialCount() {
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("access_token", wxConf.getAccessToken());
		
		String url = ApiUrlConstants.GET_MATERIAL_COUNT;
		
		JSONObject resp = wxConf.get(url, params);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	public GlobalReturnCode batchgetMaterial(MediaType type, int offset, int count) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("type", type);
		data.put("offset", offset);
		data.put("count", count);
		
		String url = ApiUrlConstants.BATCHGET_MATERIAL+"?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = wxConf.post(url, JSON.toJSONString(data));
		
		return JsonUtils.buildSendResult(resp);
	}

}
