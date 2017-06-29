/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk.utils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014-3-29 上午2:43:31
 */
public class StringUtils {
	
	/**
	 * @param t
	 * @param split
	 * @return
	 */
	public static <T> String arrToString(T[] t,String split){
		if(t == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (T t2 : t) {
			sb.append(t2).append(split);
		}
		String str = sb.toString();
		
		if(!"".equals(split))
			str = str.substring(0,str.length()-1);
		
		return str;
	}
	
	/**
	 * @param t
	 * @return
	 */
	public static <T> String arrToString(T[] t){
		return arrToString(t,",");
	}
	
	public static void main(String[] args) {
		System.out.println(arrToString(new String[]{"aa","bb"}, ""));
	}
}
