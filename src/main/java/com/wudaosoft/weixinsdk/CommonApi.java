/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;
import com.wudaosoft.weixinsdk.type.MediaType;
import com.wudaosoft.weixinsdk.utils.DigestUtils;
import com.wudaosoft.weixinsdk.utils.StringUtils;


/**
 * <p>微信公共API </p>
 * @author Changsoul.Wu
 * @date 2014年3月28日 下午3:44:08
 */
public class CommonApi {
	
	private static final Logger log = LoggerFactory.getLogger(CommonApi.class);
	
	private static final String SPLIT = "";
	
	public static String WEI_XIN_ACCOUNT;
	
	public static String APP_ID;
	
	public static String APPSECRET;
	
	public static String TOKEN;
	
	public static byte[] AES_KEY;
	
	public static String ACCESS_TOKEN_URL;
	
	private static long expiresIn = 7100 * 1000;
	
	private static long jsAPITicketExpiresIn = 7100 * 1000;
	
	private static String accessToken = null;
	
	private static String jsAPITicket = null;
	
	private static long tokenSecond = System.currentTimeMillis();
	
	private static long jsAPITicketSecond = System.currentTimeMillis();
	
//	static{
////		String classPath = WeiXinCommonApi.class.getResource("/").getPath();
////		String log4jDir = classPath.substring(0, classPath.indexOf("classes")) + "logs";
////		
////		System.setProperty("log4jDir", log4jDir);
////		
////		String log4jPath = classPath + "log4j.xml";
////		
////		DOMConfigurator.configure(log4jPath);
//		
//		Properties prop = new Properties();
//		try {
//			prop.load(CommonApi.class.getResource("/WeiXinConfig.properties").openStream());
//			WEI_XIN_ACCOUNT = prop.getProperty("WEI_XIN_ACCOUNT");
//			APP_ID = prop.getProperty("APP_ID");
//			APPSECRET = prop.getProperty("APPSECRET");
//			TOKEN = prop.getProperty("TOKEN");
//			ACCESS_TOKEN_URL = ApiUrlConstants.ACCESS_TOKEN + "?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APPSECRET;
//		} catch (IOException e) {
//			log.error(e.getMessage(), e);
//		}
//	}
	
	/**
	 * 验证微信签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		
		if(signature == null || timestamp == null || nonce == null)
			return false;
		
		String[] params = {TOKEN, timestamp, nonce};
		
		Arrays.sort(params);
		
		String str = StringUtils.arrToString(params, SPLIT);
		
		str = DigestUtils.sha1(str);
		
		if(signature.equals(str))
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
		
		//按键升序
		Collections.sort(paramsList,
				new Comparator<Map.Entry<String, String>>() {
					public int compare(Map.Entry<String, String> mapping1,
							Map.Entry<String, String> mapping2) {
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
	
	public static synchronized String getAccessToken() {
		long now = System.currentTimeMillis();
		
		if (accessToken == null || now - tokenSecond > expiresIn) {
			JSONObject data = HttpClientUtils.getForJsonResult(ACCESS_TOKEN_URL);
			
			if (data != null) {
				if(data.containsKey("access_token")){
					accessToken = data.getString("access_token");
					
					expiresIn = (data.getIntValue("expires_in") - 60 ) * 1000;
				}else{
					log.info("getAccessToken error:"+data);
				}
				tokenSecond = now;
			}
		}
		
		return accessToken;
	}
	
	public static synchronized String getJsAPITicket() {
		String url = ApiUrlConstants.JSAPI_TICKET + "?access_token="+CommonApi.getAccessToken()+"&type=jsapi";
		
		long now = System.currentTimeMillis();
		
		if (jsAPITicket == null || now - jsAPITicketSecond > jsAPITicketExpiresIn) {
			JSONObject data = HttpClientUtils.getForJsonResult(url);
			
			if (data != null) {
				if(data.containsKey("ticket")){
					jsAPITicket = data.getString("ticket");
					
					jsAPITicketExpiresIn = (data.getIntValue("expires_in") - 60 ) * 1000;
				}else{
					log.info("getJsAPITicket error:"+data);
				}
				jsAPITicketSecond = now;
			}
		}
		
		return jsAPITicket;
	}

	/**
	 * 公众号在使用接口时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。通过本接口，公众号可以上传或下载多媒体文件。但请注意，每个多媒体文件（media_id）会在上传、用户发送到微信服务器3天后自动删除，以节省服务器资源。
	 * 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体。请注意，media_id是可复用的，调用该接口需http协议。
	 * @param json
	 * @return
	 */
	public static String mediaUpload(MediaType type,File media) {
		String url = ApiUrlConstants.MEDIA_UPLOAD + "?access_token="+CommonApi.getAccessToken()+"&type="+type;
		
		String resp = HttpClientUtils.postWeiXinMedia(url, media);
		
		if (resp != null) {
			try {
				JSONObject rs = JSONObject.parseObject(resp);

				if (rs.containsKey("media_id")) {
					String mediaId = rs.getString("media_id");
					log.info("Media upload success! mediaId:" + mediaId);
					return mediaId;
				} else {
					log.info("Media upload error:" + resp);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	public static String mediaUploadForever(MediaType type,File media) {
		String url = ApiUrlConstants.ADD_MATERIAL + "?access_token="+CommonApi.getAccessToken()+"&type="+type;
		
		String resp = HttpClientUtils.postWeiXinMedia(url, media);
		
		if (resp != null) {
			try {
				JSONObject rs = JSONObject.parseObject(resp);

				if (rs.containsKey("media_id")) {
					String mediaId = rs.getString("media_id");
					log.info("Media upload forever upload success! mediaId:" + mediaId);
					return mediaId;
				} else {
					log.info("Media upload forever error:" + resp);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。
	 * 图片仅支持jpg/png格式，大小必须在1MB以下。
	 * @param json
	 * @return
	 */
	public static String mediaUploadingForArticle(File media) {
		String url = ApiUrlConstants.MEDIA_UPLOAD_FOR_ARTICLE + "?access_token="+CommonApi.getAccessToken();
		
		String resp = HttpClientUtils.postWeiXinMedia(url, media);
		
		if (resp != null) {
			try {
				JSONObject rs = JSONObject.parseObject(resp);

				if (rs.containsKey("url")) {
					String mediaUrl = rs.getString("url");
					log.info("Media upload for article success! url:" + mediaUrl);
					return mediaUrl;
				} else {
					log.info("Media upload for article error:" + resp);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * 公众号可以使用本接口获取临时素材（即下载临时的多媒体文件）。请注意，视频文件不支持https下载，调用该接口需http协议。
	 * 本接口即为原“下载多媒体文件”接口。
	 * 
	 * @param dir 文件目录，不包含文件名
	 * @param mediaId 微信文件mediaId
	 * @return
	 */
	public static File mediaDownLoad(String dir, String mediaId) {
		String url = ApiUrlConstants.MEDIA_DOWNLOAD + "?access_token="+CommonApi.getAccessToken()+"&media_id="+mediaId;
		
		try {

			HttpGet httpGet = new HttpGet(url);
			// httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "");
			HttpResponse httpResponse = HttpClientUtils.getHttpClient().execute(httpGet);

			StatusLine statusLine = httpResponse.getStatusLine();

			Header contentDisposition = httpResponse.getLastHeader("Content-disposition");

			File file = null;

			if (statusLine.getStatusCode() == 200 && contentDisposition != null) {

				String filename = contentDisposition.getValue().split(";")[1].split("=")[1].replace("\"", "");

				file = new File(dir, filename);

				FileOutputStream outputStream = new FileOutputStream(file);

				InputStream inputStream = httpResponse.getEntity().getContent();

				byte buff[] = new byte[4096];
				int counts = 0;
				while ((counts = inputStream.read(buff)) != -1) {
					outputStream.write(buff, 0, counts);
				}
				outputStream.flush();
				outputStream.close();

				log.debug("download weixin media success! file: " + file.getAbsolutePath());
			}

			EntityUtils.consume(httpResponse.getEntity());

			return file;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}
	
	public static boolean getMaterialImage(OutputStream outputStream, String mediaId) {
		String url = ApiUrlConstants.GET_MATERIAL + "?access_token="+CommonApi.getAccessToken();
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("media_id", mediaId);
			
			HttpPost httpPost = new HttpPost(url);
			
			StringEntity reqEntity = new StringEntity(JSON.toJSONString(params), Consts.UTF_8);
			reqEntity.setContentType(HttpClientUtils.JSON_CONTENT_TYPE);
			httpPost.setEntity(reqEntity);
			
			// httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "");
			HttpResponse httpResponse = HttpClientUtils.getHttpClient().execute(httpPost);
			
			StatusLine statusLine = httpResponse.getStatusLine();
			
			if (statusLine.getStatusCode() == 200) {
				
				InputStream inputStream = httpResponse.getEntity().getContent();
				
				byte buff[] = new byte[4096];
				int counts = 0;
				while ((counts = inputStream.read(buff)) != -1) {
					outputStream.write(buff, 0, counts);
				}
				outputStream.flush();
				outputStream.close();
			}
			
			EntityUtils.consume(httpResponse.getEntity());
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return false;
	}
	
	public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

	public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
