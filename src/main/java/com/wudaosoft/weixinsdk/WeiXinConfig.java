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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.aes.AesException;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;

/**
 * <p>
 * 微信平台配置信息
 * </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年4月18日 上午11:24:38
 */
public class WeiXinConfig {

	private static final Logger log = LoggerFactory.getLogger(WeiXinConfig.class);

	private Lock accessTokenLock = new ReentrantLock();
	private Lock jsAPITicketLock = new ReentrantLock();

	private String appId;
	private String appsecret;
	private String token;
	private String encodingAesKey;

	private String accessToken = null;
	private long expiresIn = 7100 * 1000;
	private long jsAPITicketExpiresIn = 7100 * 1000;
	private String jsAPITicket = null;
	private long tokenSecond = System.currentTimeMillis();
	private long jsAPITicketSecond = System.currentTimeMillis();
	private byte[] aesKey;

	protected WeiXinConfig() {
	}
	
	public WeiXinConfig(String appId, String appsecret) {
		Assert.hasText(appId, "'appId' must not be empty");
		Assert.hasText(appsecret, "'appsecret' must not be empty");
		this.appId = appId;
		this.appsecret = appsecret;
	}

	public WeiXinConfig(String appId, String appsecret, String token, String encodingAesKey) throws AesException {
		this(appId, appsecret);
		Assert.hasText(token, "'token' must not be empty");
		this.token = token;
		this.encodingAesKey = encodingAesKey;

		if (encodingAesKey != null) {
			if (encodingAesKey.length() != 43) {
				throw new AesException(AesException.IllegalAesKey);
			}
			aesKey = Base64.decodeBase64(encodingAesKey + "=");
		}
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

	public WeiXinConfig setEncodingAesKey(String encodingAesKey) throws AesException {
		this.encodingAesKey = encodingAesKey;
		if (encodingAesKey != null) {
			if (encodingAesKey.length() != 43) {
				throw new AesException(AesException.IllegalAesKey);
			}
			aesKey = Base64.decodeBase64(encodingAesKey + "=");
		}
		return this;
	}

	public byte[] getAesKey() {
		return aesKey;
	}

	public String getAccessToken() {

		accessTokenLock.lock();

		try {
			long now = System.currentTimeMillis();

			if (accessToken == null || now - tokenSecond > expiresIn) {
				JSONObject data = HttpClientUtils.getForJsonResult(ApiUrlConstants.ACCESS_TOKEN
						+ "?grant_type=client_credential&appid=" + appId + "&secret=" + appsecret);

				if (data != null) {
					if (data.containsKey("access_token")) {
						accessToken = data.getString("access_token");

						expiresIn = (data.getIntValue("expires_in") - 60) * 1000;
					} else {
						log.debug("getAccessToken error:" + data);
					}
					tokenSecond = now;
				}
			}

			return accessToken;

		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			accessTokenLock.unlock();
		}
	}

	public String getJsAPITicket() {

		jsAPITicketLock.lock();

		try {
			String url = ApiUrlConstants.JSAPI_TICKET + "?access_token=" + getAccessToken() + "&type=jsapi";

			long now = System.currentTimeMillis();

			if (jsAPITicket == null || now - jsAPITicketSecond > jsAPITicketExpiresIn) {
				JSONObject data = HttpClientUtils.getForJsonResult(url);

				if (data != null) {
					if (data.containsKey("ticket")) {
						jsAPITicket = data.getString("ticket");

						jsAPITicketExpiresIn = (data.getIntValue("expires_in") - 60) * 1000;
					} else {
						log.debug("getJsAPITicket error:" + data);
					}
					jsAPITicketSecond = now;
				}
			}

			return jsAPITicket;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			jsAPITicketLock.unlock();
		}
	}
}
