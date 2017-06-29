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
public enum MenuType {
	
	click,
	view;
	
	public static MenuType getMenuTypeByName(String name){
		
		for(MenuType type : values()){
			if(type.name().equals(name))
				return type;
		}
		return null;
	}
}
