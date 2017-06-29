/* Copyright(c)2010-2015 WUDAOSOFT.COM
 * Email:changsoul.wu@gmail.com
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.message.send.article;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2015-8-8 上午3:09:57
 */
public class Articles {
	
	private List<Article> articles = new ArrayList<Article>(10);

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		
		if(articles.size() < 10)
			articles.add(article);
	}
}
