/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk.message.receive;

import java.io.Serializable;

/**
 * <p>
 * </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年3月31日 上午11:17:15
 */
public class ReceiveTextMsg implements Serializable {

	private static final long serialVersionUID = -6557267304826432950L;

	private String msgId;
	private String msgType;
	private String toUserName;
	private String fromUserName;
	private String content;
	private String createTime;
	
	public ReceiveTextMsg(){}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}