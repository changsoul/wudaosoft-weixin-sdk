/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.message;

/**
 * <p>新闻消息实体 Articles </p>
 * @author Changsoul.Wu
 * @date 2014年4月1日 上午10:40:24
 */
public class NewsArticles {
	
	private String title;
	private String description;
	private String picUrl;
	private String url;

	public NewsArticles() {
	}

	/**
	 * @param title 标题
	 * @param description 描述
	 */
	public NewsArticles(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/**
	 * @param title 标题
	 * @param description 描述
	 * @param url 点击后跳转的链接
	 */
	public NewsArticles(String title, String description, String url) {
		this(title, description);
		this.url = url;
	}

	/**
	 * 标题,可选.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题,可选.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 描述,可选.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述,可选.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80,可选.
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80,可选.
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 点击后跳转的链接,可选.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 点击后跳转的链接,可选.
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
