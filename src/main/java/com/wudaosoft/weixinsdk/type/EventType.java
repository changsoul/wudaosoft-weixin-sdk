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
 * @date 2014年4月3日 下午5:15:20
 */
public enum EventType {
	
	subscribe,
	unsubscribe,
	SCAN,
	LOCATION,
	CLICK,
	VIEW;
	
	public static EventType getEventTypeByName(String name){
		
		for(EventType type : values()){
			if(type.name().equals(name))
				return type;
		}
		return null;
	}
}
