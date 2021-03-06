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

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.type.MediaType;
import com.wudaosoft.weixinsdk.utils.DigestUtils;
import com.wudaosoft.weixinsdk.utils.StringUtils;

/**
 * 
 * 微信公共API
 * 
 * @author Changsoul.Wu
 * @date 2014年3月28日 下午3:44:08
 */
public class CommonApi {

	private static final Logger log = LoggerFactory.getLogger(CommonApi.class);

	private static final String SPLIT = "";

	/**
	 * 验证微信签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {

		if (signature == null || timestamp == null || nonce == null)
			return false;

		String[] params = { token, timestamp, nonce };

		Arrays.sort(params);

		String str = StringUtils.arrToString(params, SPLIT);

		str = DigestUtils.sha1(str);

		if (signature.equals(str))
			return true;

		return false;
	}

	public static Map<String, String> genJsApiSignature(String jsAPITicket, String url) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("jsapi_ticket", jsAPITicket);
		params.put("noncestr", create_nonce_str());
		params.put("timestamp", create_timestamp());
		params.put("url", url);

		List<Map.Entry<String, String>> paramsList = new ArrayList<Map.Entry<String, String>>(params.entrySet());

		// 按键升序
		Collections.sort(paramsList, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
				return mapping1.getKey().compareTo(mapping2.getKey());
			}
		});

		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String> entry : paramsList) {

			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}

		String signature = DigestUtils.sha1(sb.deleteCharAt(sb.length() - 1).toString());

		params.put("signature", signature);

		return params;
	}

	/**
	 * 公众号在使用接口时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。通过本接口，公众号可以上传或下载多媒体文件。
	 * 但请注意，每个多媒体文件（media_id）会在上传、用户发送到微信服务器3天后自动删除，以节省服务器资源。
	 * 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，
	 * 公众号此后可根据该media_id来获取多媒体。请注意，media_id是可复用的，调用该接口需http协议。
	 * 
	 * @param json
	 * @return
	 */
	public static String mediaUpload(MediaType type, File media, WeiXinConfig wxConf) {
		String url = ApiUrlConstants.MEDIA_UPLOAD + "?access_token=" + wxConf.getAccessToken() + "&type=" + type;

		try {
			JSONObject rs = wxConf.getRequest().post(url, media, "media").json();;

			if (rs.containsKey("media_id")) {
				String mediaId = rs.getString("media_id");
				log.debug("Media upload success! mediaId:" + mediaId);
				return mediaId;
			} else {
				log.debug("Media upload error:" + rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static String mediaUploadForever(MediaType type, File media, WeiXinConfig wxConf) {
		String url = ApiUrlConstants.ADD_MATERIAL + "?access_token=" + wxConf.getAccessToken() + "&type=" + type;

		try {
			JSONObject rs = wxConf.getRequest().post(url, media, "media").json();

			if (rs.containsKey("media_id")) {
				String mediaId = rs.getString("media_id");
				log.debug("Media upload forever upload success! mediaId:" + mediaId);
				return mediaId;
			} else {
				log.debug("Media upload forever error:" + rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。 图片仅支持jpg/png格式，大小必须在1MB以下。
	 * 
	 * @param json
	 * @return
	 */
	public static String mediaUploadingForArticle(File media, WeiXinConfig wxConf) {
		String url = ApiUrlConstants.MEDIA_UPLOAD_FOR_ARTICLE + "?access_token=" + wxConf.getAccessToken();

		try {
			JSONObject rs = wxConf.getRequest().post(url, media, "media").json();

			if (rs.containsKey("url")) {
				String mediaUrl = rs.getString("url");
				log.debug("Media upload for article success! url:" + mediaUrl);
				return mediaUrl;
			} else {
				log.debug("Media upload for article error:" + rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 公众号可以使用本接口获取临时素材（即下载临时的多媒体文件）。请注意，视频文件不支持https下载，调用该接口需http协议。
	 * 本接口即为原“下载多媒体文件”接口。
	 * 
	 * @param dir
	 *            文件目录，不包含文件名
	 * @param mediaId
	 *            微信文件mediaId
	 * @return
	 */
	public static File mediaDownLoad(String dir, String mediaId, WeiXinConfig wxConf) {
		String url = ApiUrlConstants.MEDIA_DOWNLOAD + "?access_token=" + wxConf.getAccessToken() + "&media_id="
				+ mediaId;

		try {

			return wxConf.getRequest().get(url).file(dir);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static BufferedImage getMaterialImage(String mediaId, WeiXinConfig wxConf) {
		String url = ApiUrlConstants.GET_MATERIAL + "?access_token=" + wxConf.getAccessToken();

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("media_id", mediaId);

			return wxConf.getRequest().post(url, JSON.toJSONString(params)).image();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
