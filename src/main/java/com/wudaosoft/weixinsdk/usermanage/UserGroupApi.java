/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.usermanage;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.GlobalReturnCode;
import com.wudaosoft.weixinsdk.WeiXinConfig;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月2日 上午11:19:27
 */
public class UserGroupApi {

	private static final Logger log = LoggerFactory.getLogger(UserGroupApi.class);
	
	private WeiXinConfig wxConf;

	public UserGroupApi(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	/**
	 * 创建分组
	 * @param name 组名
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode createGroup(String name) {
		JSONObject req = new JSONObject();
		
		JSONObject sub = new JSONObject();
		sub.put("name", name);
		
		req.put("group", sub);
		
		String url = ApiUrlConstants.GROUPS_CREATE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		
		return JsonUtils.buildSendResult(resp);
	}
	
	/**
	 * 修改分组名
	 * @param id 组ID
	 * @param name 组名
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode updateGroup(int id, String name) {
		JSONObject req = new JSONObject();
		
		JSONObject sub = new JSONObject();
		sub.put("id", id);
		sub.put("name", name);
		
		req.put("group", sub);
		
		String url = ApiUrlConstants.GROUPS_UPDATE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		
		return JsonUtils.buildSendResult(resp);
	}
	
	/**
	 * 查询所有分组
	 * @return List or null
	 */
	public List<Group> getGroups() {
		
		String url = ApiUrlConstants.GROUPS_GET + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.getForJsonResult(url);
		
		try {
			if (resp != null) {
				if (resp.containsKey("groups")) {
					JSONArray groups = resp.getJSONArray("groups");
					List<Group> groupList = new ArrayList<Group>(groups.size());

					for (int i = 0; i < groups.size(); i++) {
						JSONObject g = groups.getJSONObject(i);
						Group group = new Group();
						group.setId(g.getIntValue("id"));
						group.setName(g.getString("name"));
						group.setCount(g.getIntValue("count"));
						groupList.add(group);
					}
					return groupList;
				} else {
					log.error("getGroups error:"+resp);
				}
			}
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 查询用户所在分组
	 * @param openId 用户的OpenID
	 * @return -1 for error
	 */
	public int getGroupId(String openId) {
		JSONObject req = new JSONObject();
		req.put("openid", openId);
		
		String url = ApiUrlConstants.GROUPS_GET_ID + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		
		if(resp != null) {
			if(resp.containsKey("groupid")) {
				return resp.getIntValue("groupid");
			}else{
				log.error("getGroupId error:"+resp);
			}
		}
		
		return -1;
	}
	
	/**
	 * 移动用户分组
	 * @param openId 用户的OpenID
	 * @param toGroupId 分组id
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode moveToGroup(String openId, int toGroupId) {
		JSONObject req = new JSONObject();
		req.put("openid", openId);
		req.put("to_groupid", toGroupId);
		
		String url = ApiUrlConstants.MEMBERS_UPDATE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		
		return JsonUtils.buildSendResult(resp);
	}

}
