/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.message.response;

import java.util.ArrayList;
import java.util.List;

import com.wudaosoft.weixinsdk.message.NewsArticles;
import com.wudaosoft.weixinsdk.type.MsgType;
import com.wudaosoft.weixinsdk.xml.XmlObject;

/**
 * <p>被动响应消息 生成类</p>
 * @author Changsoul.Wu
 * @date 2014年4月1日 上午10:07:15
 */
public class ResponseMsgBuilder {

	private ResponseMsgBuilder() {
	}
	
	/**
	 * 生成回复文本消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 * @return
	 */
	public static String buildTextMsg(String toUserName, String fromUserName, String content){
		String msgType = MsgType.text.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		String xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("Content", content)
				.build();
		
		return xml;
	}
	
	/**
	 * 生成回复图片消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过上传多媒体文件，得到的id。
	 */
	public static String buildImageMsg(String toUserName, String fromUserName, String mediaId){
		String msgType = MsgType.image.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		String xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("Image", XmlObject.create().put("MediaId", mediaId).buildMap())
				.build();
		
		return xml;
	}
	
	/**
	 * 生成回复语音消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过上传多媒体文件，得到的id。
	 */
	public static String buildVoiceMsg(String toUserName, String fromUserName, String mediaId){
		String msgType = MsgType.voice.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		String xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("Voice", XmlObject.create().put("MediaId", mediaId).buildMap())
				.build();
		
		return xml;
	}
	
	/**
	 * 生成回复视频消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过上传多媒体文件，得到的id
	 * @param title 视频消息的标题
	 * @param description 视频消息的描述
	 */
	public static String buildVideoMsg(String toUserName, String fromUserName, String mediaId, String title, String description){
		String msgType = MsgType.video.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		String xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("Video", XmlObject.create()
						.put("MediaId", mediaId)
						.put("Title", title)
						.put("Description", description)
						.buildMap())
				.build();
		
		return xml;
	}
	
	/**
	 * 生成回复音乐消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param title 音乐标题
	 * @param description 音乐描述
	 * @param musicUrl 音乐链接
	 * @param hqMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @param thumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
	 */
	public static String buildMusicMsg(String toUserName, String fromUserName, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId){
		String msgType = MsgType.music.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		String xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("Music", XmlObject.create()
						.put("Title", title)
						.put("Description", description)
						.put("MusicUrl", musicUrl)
						.put("HQMusicUrl", hqMusicUrl)
						.put("ThumbMediaId", thumbMediaId)
						.buildMap())
				.build();
		
		return xml;
	}
	
	/**
	 * 生成回复图文消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param articles 一个或多个ResponseNewsArticles，
	 * @return
	 */
	public static String buildNewsMsg(String toUserName, String fromUserName, NewsArticles... articles){
		if(articles == null)
			return "";
		
		String msgType = MsgType.news.toString();
		long createTime = System.currentTimeMillis() / 1000;
		
		XmlObject xml = XmlObject.create()
				.put("ToUserName", toUserName)
				.put("FromUserName", fromUserName)
				.put("MsgType", msgType)
				.put("CreateTime", createTime)
				.put("ArticleCount", articles.length);
				
		List<XmlObject> item = new ArrayList<XmlObject>(articles.length);
		for(NewsArticles article : articles) {
			XmlObject articleXml = XmlObject.create()
					.put("Title", article.getTitle())
					.put("Description", article.getDescription())
					.put("Url", article.getUrl())
					.put("PicUrl", article.getPicUrl());
			
			
			item.add(XmlObject.create().put("item", articleXml));
		}
		
		xml.put("Articles", item);
		
		return xml.build();
	}
	
	public static void main(String[] args) {
		NewsArticles article = new NewsArticles();
		article.setTitle("哈哈");
		article.setDescription("嘿嘿");
		article.setPicUrl("http://1.a");
		article.setUrl("http://2.a");
		
		NewsArticles article1 = new NewsArticles();
		article1.setTitle("妈的");
		article1.setDescription("草");
		article1.setPicUrl("http://3.a");
		article1.setUrl("http://4.a");
		
		System.out.println(buildNewsMsg("touser", "fromuser", article, article));
		
	}

}
