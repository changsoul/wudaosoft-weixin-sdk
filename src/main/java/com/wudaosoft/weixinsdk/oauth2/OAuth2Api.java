/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月9日 上午9:47:32
 */
public class OAuth2Api {
	
	private final static Logger log = LoggerFactory.getLogger(OAuth2Api.class);
	
	private final static String SNSAPI_BASE = "snsapi_base";
	private final static String SNSAPI_USERINFO = "snsapi_userinfo";

	private WeiXinConfig wxConf;

	public OAuth2Api(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	/**
	 * 不弹出授权页面，直接跳转，只能获取用户openid
	 * @param linkText 链接文字
	 * @param redirectUri 授权后重定向的回调链接地址
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值
	 * @return
	 */
	public String buildBaseOauth2Link(String linkText, String redirectUri, String state) {
		return buildOauth2Link(linkText, redirectUri, SNSAPI_BASE, state);
	}
	
	/**
	 * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
	 * @param linkText 链接文字
	 * @param redirectUri 授权后重定向的回调链接地址
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值
	 * @return
	 */
	public String buildUserinfoOauth2Link(String linkText, String redirectUri, String state) {
		return buildOauth2Link(linkText, redirectUri, SNSAPI_USERINFO, state);
	}
	
	/**
	 * @param linkText 链接文字
	 * @param redirectUri 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state
	 * @return
	 */
	private String buildOauth2Link(String linkText, String redirectUri, String scope, String state) {
		if(state == null)
			state = "";
		
		StringBuilder link = new StringBuilder();
		try {
			link.append("<a href=\"").append(ApiUrlConstants.OAUTH2_LINK+"?appid="+wxConf.getAppId())
			.append("&redirect_uri="+URLEncoder.encode(redirectUri, "UTF-8"))
			.append("&response_type=code")
			.append("&scope="+scope)
			.append("&state="+state)
			.append("#wechat_redirect")
			.append("\">");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		link.append(linkText).append("</a>");
		return link.toString();
	}
	
	/**
	 * 通过code换取网页授权access_token
	 * @param code
	 * @return
	 */
	public OAuth2AccessToken getOauth2AccessToken(String code) {
		Map<String, String> params = new HashMap<String, String>(4);
		params.put("appid", wxConf.getAppId());
		params.put("secret", wxConf.getAppsecret());
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		JSONObject resp = HttpClientUtils.getForJsonResult(ApiUrlConstants.OAUTH2_ACCESS_TOKEN, params);
		
		return JsonUtils.buildRequestResult(resp, OAuth2AccessToken.class);
	}
	
	/**
	 * 刷新access_token（如果需要）
	 * @param refreshToken 填写通过access_token获取到的refresh_token参数
	 * @return
	 */
	public OAuth2AccessToken refreshOauth2AccessToken(String refreshToken) {
		Map<String, String> params = new HashMap<String, String>(4);
		params.put("appid", wxConf.getAppId());
		params.put("refresh_token", refreshToken);
		params.put("grant_type", "refresh_token");
		
		JSONObject resp = HttpClientUtils.getForJsonResult(ApiUrlConstants.OAUTH2_REFRESH_TOKEN, params);
		
		return JsonUtils.buildRequestResult(resp, OAuth2AccessToken.class);
	}
	
	/**
	 * 获取用户基本信息
	 * @param oauth2AccessToken 
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public OAuth2UserInfo oAuth2UserInfo(String oauth2AccessToken,String openId, String lang){
		String url = ApiUrlConstants.OAUTH2_USERINFO + "?access_token="+oauth2AccessToken+"&openid="+openId;
		
		if(lang != null )
			url += "&lang="+lang;
		
		JSONObject resp = HttpClientUtils.getForJsonResult(url);

		return JsonUtils.buildRequestResult(resp, OAuth2UserInfo.class);
	}

}
