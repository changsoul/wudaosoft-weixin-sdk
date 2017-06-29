/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.usermanage;

import java.util.ArrayList;
import java.util.List;

import com.wudaosoft.weixinsdk.GlobalReturnCode;

/**
 * <p>关注者列表 </p>
 * @author Changsoul.Wu
 * @date 2014年4月2日 下午2:48:27
 */
public class SubscribeList extends GlobalReturnCode {
	
	private int total;
	private int count;
	private String nextOpenId;
	private List<String> openIdList;

	public SubscribeList() {
	}

	/**
	 * 关注该公众账号的总用户数
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 拉取的OPENID个数，最大值为10000
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 拉取列表的后一个用户的OPENID
	 * @return the nextOpenId
	 */
	public String getNextOpenId() {
		return nextOpenId;
	}

	/**
	 * @param nextOpenId the nextOpenId to set
	 */
	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
	
	/**
	 * 列表数据，OPENID的列表
	 * @return the openIdList
	 */
	public List<String> getOpenIdList() {
		return openIdList;
	}

	/**
	 * @param openIdList the openIdList to set
	 */
	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public void addOpenId(String openId){
		if(openIdList == null)
			openIdList = new ArrayList<String>(count);
		openIdList.add(openId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "subscribeList [total=" + total + ", count=" + count
				+ ", nextOpenId=" + nextOpenId + ", openIdList=" + openIdList + "]";
	}
	
}
