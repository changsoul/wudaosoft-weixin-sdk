/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.GlobalReturnCode;
import com.wudaosoft.weixinsdk.WeiXinConfig;
import com.wudaosoft.weixinsdk.httpclient.HttpClientUtils;
import com.wudaosoft.weixinsdk.utils.JsonUtils;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月3日 下午5:30:31
 */
public class MenuApi {
	
	private WeiXinConfig wxConf;
	
	public MenuApi(WeiXinConfig wxConf) {
		super();
		this.wxConf = wxConf;
	}
	
	public WeiXinConfig getWeiXinConfig() {
		return this.wxConf;
	}
	
	/**
	 * 创建菜单
	 * 
	 * 目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。请注意，创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。建议测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
	 * @param menu
	 * 例如:
	 * //左边菜单
	 *	SubMenu sub1 = new SubMenu("菜单1");
	 *	sub1.addSubButton(new ClickMenu("子菜单1", "1"));
	 *	sub1.addSubButton(new ClickMenu("子菜单2", "2"));
	 *	sub1.addSubButton(new ClickMenu("子菜单3", "3"));
	 *	sub1.addSubButton(new ClickMenu("子菜单4", "4"));
	 *	sub1.addSubButton(new ClickMenu("子菜单5", "5"));
	 *	
	 *	//中间菜单
	 *	SubMenu sub2 = new SubMenu("菜单2");
	 *	sub2.addSubButton(new ClickMenu("子菜单1", "1"));
	 *	sub2.addSubButton(new ClickMenu("子菜单2", "2"));
	 *	sub2.addSubButton(new ClickMenu("子菜单3", "3"));
	 *	sub2.addSubButton(new ClickMenu("子菜单4", "4"));
	 *	sub2.addSubButton(new ViewMenu("子菜单5", "http://www.wudaosoft.com"));
	 *	
	 *	//右边菜单
	 *	ViewMenu view3 = new ViewMenu("菜单3", "http://www.wudaosoft.com");
	 *	
	 *	//菜单栏
	 *	Menu menu = new Menu();
	 *	menu.addButton(sub1);
	 *	menu.addButton(sub2);
	 *	menu.addButton(view3);
	 * @return GlobalReturnCode or null if ioerror
	 */
	public GlobalReturnCode menuCreate(Menu menu){
		if(menu == null)
			return null;
		
		JSONObject req = (JSONObject) JSON.toJSON(menu);
		
		return menuCreateByJsonString(req.toString());
	}
	
	public GlobalReturnCode menuCreateByJsonString(String jsonString){
		if(jsonString == null)
			return null;
		
		String url = ApiUrlConstants.MENU_CREATE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, jsonString);
		
		return JsonUtils.buildSendResult(resp);
	}
	
	/**
	 * 菜单查询
	 * @return JSONObject or null if ioerror
	 */
	public JSONObject menuGet(){
		
		String url = ApiUrlConstants.MENU_GET + "?access_token="+wxConf.getAccessToken();
		
		return HttpClientUtils.getForJsonResult(url);
	}
	
	/**
	 * 菜单删除
	 * @return GlobalReturnCode or null if ioerror
	 */
	public GlobalReturnCode menuDelete(){
		
		String url = ApiUrlConstants.MENU_DELETE + "?access_token="+wxConf.getAccessToken();
		
		JSONObject resp = HttpClientUtils.getForJsonResult(url);
		return JsonUtils.buildSendResult(resp);
	}
}
