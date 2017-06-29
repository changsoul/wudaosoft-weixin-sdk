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
 * @date 2014年3月31日 下午4:08:09
 */
public enum MediaType {
	
	image,
	voice,
	video,
	thumb,
	news;
	
	public static MediaType getMediaTypeByName(String name){
		
		for(MediaType type : values()){
			if(type.name().equals(name))
				return type;
		}
		return null;
	}
}
