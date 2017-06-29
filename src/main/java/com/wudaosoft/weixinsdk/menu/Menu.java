/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。 </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年4月4日 上午11:06:13
 */
public class Menu {

	private List<BaseMenu> button;

	public Menu() {
	}
	
	public List<BaseMenu> getButton() {
		return button;
	}

	public void setButton(List<BaseMenu> button) {
		this.button = button;
	}
	
	public void addButton(BaseMenu btn) {
		if(button == null)
			button = new ArrayList<BaseMenu>(3);
		
		if(button.size() < 3) {
			button.add(btn);
		}
	}

	@Override
	public String toString() {
		return "Menu [button=" + button + "]";
	}
}
