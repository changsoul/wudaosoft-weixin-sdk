/**
 *    Copyright 2009-2018 Wudao Software Studio(wudaosoft.com)
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.wudaosoft.weixinsdk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wudaosoft.weixinsdk.material.MaterialApi;
import com.wudaosoft.weixinsdk.menu.MenuApi;
import com.wudaosoft.weixinsdk.message.send.CustomMsgSender;
import com.wudaosoft.weixinsdk.message.send.article.ArticlesApi;
import com.wudaosoft.weixinsdk.oauth2.OAuth2Api;
import com.wudaosoft.weixinsdk.qrcode.QRCodeApi;
import com.wudaosoft.weixinsdk.usermanage.UserApi;
import com.wudaosoft.weixinsdk.usermanage.UserGroupApi;

/**
 * @author Changsoul Wu
 * 
 */
@Configuration
public class WeiXinAutoCommonConfiguration {

	@Bean
	public MaterialApi officialMaterialApi(WeiXinConfig officialWeixinConf) {
		return new MaterialApi(officialWeixinConf);
	}

	@Bean
	public MenuApi officialMenuApi(WeiXinConfig officialWeixinConf) {
		return new MenuApi(officialWeixinConf);
	}

	@Bean
	public CustomMsgSender officialCustomMsgSender(WeiXinConfig officialWeixinConf) {
		return new CustomMsgSender(officialWeixinConf);
	}

	@Bean
	public ArticlesApi officialArticlesApi(WeiXinConfig officialWeixinConf) {
		return new ArticlesApi(officialWeixinConf);
	}

	@Bean
	public OAuth2Api officialOAuth2Api(WeiXinConfig officialWeixinConf) {
		return new OAuth2Api(officialWeixinConf);
	}

	@Bean
	public QRCodeApi officialQRCodeApi(WeiXinConfig officialWeixinConf) {
		return new QRCodeApi(officialWeixinConf);
	}

	@Bean
	public UserApi officialUserApi(WeiXinConfig officialWeixinConf) {
		return new UserApi(officialWeixinConf);
	}

	@Bean
	public UserGroupApi officialUserGroupApi(WeiXinConfig officialWeixinConf) {
		return new UserGroupApi(officialWeixinConf);
	}

}
