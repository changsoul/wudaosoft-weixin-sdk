/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.exception;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年3月31日 上午10:35:26
 */
public class WeiXinException extends Exception {

	static final long serialVersionUID = -1634447102016222968L;

	/**
	 * 
	 */
	public WeiXinException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WeiXinException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public WeiXinException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WeiXinException(Throwable cause) {
		super(cause);
	}
}
