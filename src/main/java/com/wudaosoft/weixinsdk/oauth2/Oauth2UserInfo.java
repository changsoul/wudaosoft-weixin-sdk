/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.oauth2;

import java.util.List;

import com.wudaosoft.weixinsdk.GlobalReturnCode;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月2日 下午1:26:30
 */
public class Oauth2UserInfo extends GlobalReturnCode {
	
	private String openid;
	private String nickname;
	private Integer sex;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private List<String> privilege;

	public Oauth2UserInfo() {
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
	 * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 * @return
	 */
	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "Oauth2UserInfo [openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", province=" + province
				+ ", country=" + country + ", headimgurl=" + headimgurl
				+ ", privilege=" + privilege + "]";
	}

}
