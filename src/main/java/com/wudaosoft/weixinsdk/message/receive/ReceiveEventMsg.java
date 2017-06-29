/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.message.receive;

import java.io.Serializable;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年3月31日 下午3:50:58
 */
public class ReceiveEventMsg implements Serializable {

	private static final long serialVersionUID = -5729485128923993375L;
	
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String event;
	private String eventKey;
	private String ticket;
	private String latitude;
	private String longitude;
	private String precision;

	public ReceiveEventMsg() {
	}

	/**
	 * 开发者微信号
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * 开发者微信号
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * 发送方帐号（一个OpenID）
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * 发送方帐号（一个OpenID）
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * 消息创建时间 （整型）
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 消息创建时间 （整型）
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 消息类型，event
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 消息类型，event
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、SCAN(用户已关注时)、LOCATION(上报地理位置)、CLICK(点击菜单拉取消息)、VIEW(点击菜单跳转链接)
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、SCAN(用户已关注时)、LOCATION(上报地理位置)、CLICK(点击菜单拉取消息)、VIEW(点击菜单跳转链接)
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * 事件KEY值
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * 事件KEY值
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * 二维码的ticket，可用来换取二维码图片
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * 二维码的ticket，可用来换取二维码图片
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * 地理位置纬度
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 地理位置纬度
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 地理位置经度
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 地理位置经度
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 地理位置精度
	 * @return the precision
	 */
	public String getPrecision() {
		return precision;
	}

	/**
	 * 地理位置精度
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
}
