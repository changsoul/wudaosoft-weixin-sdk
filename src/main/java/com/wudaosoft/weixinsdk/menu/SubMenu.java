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
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月3日 下午5:11:42
 */
public class SubMenu extends BaseMenu{
	
	private List<BaseMenu> sub_button;
	
	public SubMenu(){
	}

	public SubMenu(String name) {
		super(name);
	}

	public List<BaseMenu> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<BaseMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
	public void addSubButton(BaseMenu btn) {
		if(sub_button == null)
			sub_button = new ArrayList<BaseMenu>(3);
		
		if(sub_button.size() < 5) {
			sub_button.add(btn);
		}
	}

	@Override
	public String toString() {
		return "SubMenu [name=" + getName() + ", sub_button=" + sub_button + "]";
	}
	
}
