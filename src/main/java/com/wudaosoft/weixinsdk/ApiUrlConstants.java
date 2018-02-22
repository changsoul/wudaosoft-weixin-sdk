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
	
	public static final String WEIXIN_API_SERVER_HOST = "https://api.weixin.qq.com";
	
	public static final String ACCESS_TOKEN = "/cgi-bin/token";
	public static final String JSAPI_TICKET = "/cgi-bin/ticket/getticket";
	
	public static final String CUSTOM_SEND = "/cgi-bin/message/custom/send";
	
	public static final String GROUPS_CREATE = "/cgi-bin/groups/create";
	public static final String GROUPS_GET = "/cgi-bin/groups/get";
	public static final String GROUPS_GET_ID = "/cgi-bin/groups/getid";
	public static final String GROUPS_UPDATE = "/cgi-bin/groups/update";
	
	public static final String MEMBERS_UPDATE = "/cgi-bin/groups/members/update";
	
	public static final String USER_INFO = "/cgi-bin/user/info";
	public static final String USER_GET = "/cgi-bin/user/get";
	
	public static final String MEDIA_UPLOAD = "/cgi-bin/media/upload";
	public static final String MEDIA_UPLOAD_FOR_ARTICLE = "/cgi-bin/media/uploadimg";
	
	public static final String MEDIA_DOWNLOAD = "http://api.weixin.qq.com/cgi-bin/media/get";
	
	public static final String MENU_CREATE = "/cgi-bin/menu/create";
	public static final String MENU_GET = "/cgi-bin/menu/get";
	public static final String MENU_DELETE = "/cgi-bin/menu/delete";
	
	public static final String QRCODE_CREATE = "/cgi-bin/qrcode/create";
	public static final String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	public static final String OAUTH2_LINK = "https://open.weixin.qq.com/connect/oauth2/authorize";
	public static final String OAUTH2_ACCESS_TOKEN = "/sns/oauth2/access_token";
	public static final String OAUTH2_REFRESH_TOKEN = "/sns/oauth2/refresh_token";
	public static final String OAUTH2_USERINFO = "/sns/userinfo";
	
	public static final String ARTICLES_UPLOAD = "/cgi-bin/media/uploadnews";
	public static final String ARTICLES_UPLOAD_TO_MATERIAL = "/cgi-bin/material/add_news";
	public static final String ARTICLES_MATERIAL_UPDATE_NEWS = "/cgi-bin/material/update_news";
	public static final String ARTICLES_SEND = "/cgi-bin/message/mass/sendall";
	public static final String ARTICLES_PREVIEW = "/cgi-bin/message/mass/preview";
	
	public static final String ADD_MATERIAL = "/cgi-bin/material/add_material";
	public static final String GET_MATERIAL = "/cgi-bin/material/get_material";
	public static final String DEL_MATERIAL = "/cgi-bin/material/del_material";
	public static final String GET_MATERIAL_COUNT = "/cgi-bin/material/get_materialcount";
	public static final String BATCHGET_MATERIAL = "/cgi-bin/material/batchget_material";

}
