/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.usermanage;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月2日 上午11:29:03
 */
public class Group {

	private int id;
	private String name;
	private int count;
	
	public Group() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
