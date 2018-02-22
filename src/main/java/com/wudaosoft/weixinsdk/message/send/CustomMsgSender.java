/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk.message.send;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.GlobalReturnCode;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.message.NewsArticles;
import com.wudaosoft.weixinsdk.type.MsgType;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p>
 * 发送客服消息
 * </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年4月1日 下午2:51:10
 */
public class CustomMsgSender {

	private WeiXinConfig wxConf;

	public CustomMsgSender(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}

	/**
	 * 发送文本消息
	 * 
	 * @param toUser
	 *            普通用户openid
	 * @param content
	 *            文本消息内容,支持换行符
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendTextMsg(String toUser, String content) {
		String msgType = MsgType.text.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("content", content);

		req.put("text", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送图片消息
	 * 
	 * @param toUser
	 *            普通用户openid
	 * @param mediaId
	 *            发送的图片的媒体ID
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendImageMsg(String toUser, String mediaId) {
		String msgType = MsgType.image.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);

		req.put("image", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送语音消息
	 * 
	 * @param toUser
	 * @param mediaId
	 *            发送的语音的媒体ID
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendVoiceMsg(String toUser, String mediaId) {
		String msgType = MsgType.voice.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);

		req.put("voice", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送视频消息
	 * 
	 * @param toUser
	 * @param mediaId
	 *            发送的视频的媒体ID
	 * @param title
	 *            视频消息的标题,可选.
	 * @param description
	 *            视频消息的描述,可选.
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendVideoMsg(String toUser, String mediaId, String title, String description) {
		String msgType = MsgType.video.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);
		sub.put("title", title);
		sub.put("description", description);

		req.put("video", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送音乐消息
	 * 
	 * @param toUser
	 * @param title
	 *            音乐标题,可选.
	 * @param description
	 *            音乐描述,可选.
	 * @param musicUrl
	 *            音乐链接
	 * @param hqMusicUrl
	 *            高品质音乐链接，wifi环境优先使用该链接播放音乐
	 * @param thumbMediaId
	 *            缩略图的媒体ID
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendMusicMsg(String toUser, String title, String description, String musicUrl,
			String hqMusicUrl, String thumbMediaId) {
		String msgType = MsgType.music.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("title", title);
		sub.put("description", description);
		sub.put("musicurl", musicUrl);
		sub.put("hqmusicurl", hqMusicUrl);
		sub.put("description", description);
		sub.put("thumb_media_id", thumbMediaId);

		req.put("music", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送图文消息
	 * 
	 * @param toUser
	 * @param articles
	 *            NewsArticles 一个或多个,图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应。
	 * @return GlobalReturnCode
	 */
	public GlobalReturnCode sendNewsMsg(String toUser, NewsArticles... articles) {
		if (articles == null)
			return new GlobalReturnCode().systemError();

		String msgType = MsgType.news.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONArray array = new JSONArray();

		for (NewsArticles article : articles) {
			JSONObject thr = new JSONObject();

			thr.put("Title", article.getTitle());
			thr.put("Description", article.getDescription());
			thr.put("Url", article.getUrl());
			thr.put("PicUrl", article.getPicUrl());

			array.add(thr);
		}

		JSONObject sub = new JSONObject();
		sub.put("articles", array);

		req.put("news", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + wxConf.getAccessToken();

		JSONObject resp = wxConf.post(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}
}
