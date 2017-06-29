/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.menu;


/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月3日 下午5:11:42
 */
public class BaseMenu {
	
	private String name;

	public BaseMenu() {
	}

	/**
	 * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	public BaseMenu(String name) {
		this.name = name;
	}

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + "]";
	}

}
