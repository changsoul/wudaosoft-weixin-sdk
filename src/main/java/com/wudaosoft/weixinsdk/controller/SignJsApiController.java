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
package com.wudaosoft.weixinsdk.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wudaosoft.weixinsdk.CommonApi;
import com.wudaosoft.weixinsdk.WeiXinConfig;

/**
 * 微信JSSDK签名
 * 
 * @author Changsoul Wu 
 * 
 */
@RestController
@RequestMapping(value="/api/weixin")
public class SignJsApiController {
    
	@Autowired
	private WeiXinConfig officialWeixinConf;
	
	/**
	 * 为微信JSSDK签名
	 * @param url
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/jssign", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Map<String, String>> signJsApi(@RequestParam("url") String url, HttpServletRequest request) {
    	
    	//拒绝外部链接
    	if(!url.contains(request.getServerName())) {
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    	}

    	Map<String, String> data = CommonApi.genJsApiSignature(officialWeixinConf.getJsAPITicket(), url);
    	data.put("appid", officialWeixinConf.getAppId());
    	data.remove("jsapi_ticket");
    	data.remove("url");
    	
    	return ResponseEntity.ok(data);
    }
    
}