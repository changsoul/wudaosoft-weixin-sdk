/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>全局返回码 </p>
 * @author Changsoul.Wu
 * @date 2014年4月1日 下午2:55:20
 */
public class GlobalReturnCode {
	
	private int errcode = 0;
	private String errmsg = "ok";
	
	private JSONObject data;

	public GlobalReturnCode() {
	}

	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public GlobalReturnCode systemError() {
		this.errcode = -4;
		this.errmsg = "system error";
		return this;
	}

	@Override
	public String toString() {
		return "GlobalReturnCode [errcode=" + errcode + ", errmsg=" + errmsg
				+ ", data=" + data + "]";
	}

}
