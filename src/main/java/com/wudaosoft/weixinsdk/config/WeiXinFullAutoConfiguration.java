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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.wudaosoft.weixinsdk.WeiXinMessageProcess;
import com.wudaosoft.weixinsdk.condition.ConditionalOnMissingMyBean;
import com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler;
import com.wudaosoft.weixinsdk.handler.WeiXinMessageHandlerAdapter;

/**
 * @author Changsoul Wu
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.wudaosoft.weixinsdk.controller" })
public class WeiXinFullAutoConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(WeiXinFullAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingMyBean(WeiXinMessageHandler.class)
	public WeiXinMessageHandler weiXinMessageHandler() {
		log.info("Use DefaultWeiXinMessageHandler..");
		return new WeiXinMessageHandlerAdapter(){};
	}
	
	@Bean
	//@ConditionalOnMyBean(WeiXinMessageHandler.class)
	public WeiXinMessageProcess officialWeiXinMessageProcess(WeiXinConfig officialWeixinConf, WeiXinMessageHandler messageHandler) {
		return new WeiXinMessageProcess(officialWeixinConf, messageHandler);
	}

}
