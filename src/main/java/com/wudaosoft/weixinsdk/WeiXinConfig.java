/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk;

/**
 * <p>微信平台配置信息 </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年4月18日 上午11:24:38
 */
public class WeiXinConfig {
	private String account;
	private String appId;
	private String appsecret;
	private String token;
	private String encodingAesKey;

	public WeiXinConfig() {
	}

	public WeiXinConfig(String account, String appId, String appsecret, String token, String encodingAesKey) {
		this.account = account;
		this.appId = appId;
		this.appsecret = appsecret;
		this.token = token;
		this.encodingAesKey = encodingAesKey;
	}

	public String getAccount() {
		return account;
	}

	public WeiXinConfig setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getAppId() {
		return appId;
	}

	public WeiXinConfig setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public WeiXinConfig setAppsecret(String appsecret) {
		this.appsecret = appsecret;
		return this;
	}

	public String getToken() {
		return token;
	}

	public WeiXinConfig setToken(String token) {
		this.token = token;
		return this;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public WeiXinConfig setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
		return this;
	}
	
}
