/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.usermanage;

import com.wudaosoft.weixinsdk.GlobalReturnCode;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月2日 下午1:26:30
 */
public class UserInfo extends GlobalReturnCode {
	
	private Integer subscribe;
	private String openid;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private Integer subscribe_time;

	public UserInfo() {
	}

	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	 * @return the subscribe
	 */
	public Integer getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * 用户的标识，对当前公众号唯一
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 用户的昵称
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 用户的语言，简体中文为zh_CN
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 用户所在城市
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 用户所在省份
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 用户所在国家
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 * @return the subscribe_time
	 */
	public Integer getSubscribe_time() {
		return subscribe_time;
	}

	/**
	 * @param subscribe_time the subscribe_time to set
	 */
	public void setSubscribe_time(Integer subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [subscribe=" + subscribe + ", openid=" + openid
				+ ", nickname=" + nickname + ", sex=" + sex + ", language="
				+ language + ", city=" + city + ", province=" + province
				+ ", country=" + country + ", headimgurl=" + headimgurl
				+ ", subscribe_time=" + subscribe_time + "]";
	}
	
}
