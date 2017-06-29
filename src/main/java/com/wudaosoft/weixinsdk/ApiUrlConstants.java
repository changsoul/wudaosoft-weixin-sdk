/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk;

/**
 * <p> </p>
 * @author Changsoul.Wu
 * @date 2014年4月1日 下午4:09:44
 */
public final class ApiUrlConstants {
	
	public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	public static final String CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	public static final String GROUPS_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create";
	public static final String GROUPS_GET = "https://api.weixin.qq.com/cgi-bin/groups/get";
	public static final String GROUPS_GET_ID = "https://api.weixin.qq.com/cgi-bin/groups/getid";
	public static final String GROUPS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/update";
	
	public static final String MEMBERS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/members/update";
	
	public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	public static final String USER_GET = "https://api.weixin.qq.com/cgi-bin/user/get";
	
	public static final String MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload";
	public static final String MEDIA_UPLOAD_FOR_ARTICLE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
	public static final String MEDIA_DOWNLOAD = "http://api.weixin.qq.com/cgi-bin/media/get";
	
	public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";
	public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";
	public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	
	public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	public static final String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	public static final String OAUTH2_LINK = "https://open.weixin.qq.com/connect/oauth2/authorize";
	public static final String OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	public static final String OAUTH2_USERINFO = "https://api.weixin.qq.com/sns/userinfo";
	
	public static final String ARTICLES_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
	public static final String ARTICLES_UPLOAD_TO_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_news";
	public static final String ARTICLES_MATERIAL_UPDATE_NEWS = "https://api.weixin.qq.com/cgi-bin/material/update_news";
	public static final String ARTICLES_SEND = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
	public static final String ARTICLES_PREVIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
	
	public static final String ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material";
	public static final String GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material";
	public static final String DEL_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/del_material";
	public static final String GET_MATERIAL_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";
	public static final String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";

}
