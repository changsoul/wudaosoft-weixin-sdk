/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.type;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年3月31日 上午10:03:39
 */
public enum MsgType {
	
	text,
	image,
	voice,
	video,
	location,
	link,
	event,
	music,
	news;
	
	public static MsgType getMsgTypeByName(String name){
		
		for(MsgType type : values()){
			if(type.name().equals(name))
				return type;
		}
		return null;
	}
}
