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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wudaosoft.weixinsdk.CommonApi;
import com.wudaosoft.weixinsdk.WeiXinMessageProcess;
import com.wudaosoft.weixinsdk.aes.AesException;

/** 
 * @author Changsoul Wu
 * 
 */
@Controller
@RequestMapping("/api/weixin/msgserver")
public class WeiXinMessageController {

	private static final Logger log = LoggerFactory.getLogger(WeiXinMessageController.class);
	
	private WeiXinMessageProcess process;

	@Autowired
	public WeiXinMessageController(WeiXinMessageProcess process) {
		super();
		this.process = process;
		log.debug("init WeiXinMessageController success...");
	}

	@GetMapping
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		resp.setContentType("application/json; encoding=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		if (signature != null && timestamp != null && nonce != null && echostr != null) {

			boolean flag = CommonApi.checkSignature(process.getWeiXinConfig().getToken(), signature, timestamp, nonce);

			if (flag) {
				log.info("check weixin signature success");
				resp.getWriter().write(echostr);
			} else {
				log.info("check weixin signature failed");
			}
		}
	}

	@PostMapping
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/xml; encoding=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		
		String respXML = null;
		
		try {
					
			try {
				respXML = process.processRequest(req);
			} catch (AesException e) {
				log.warn(e.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			
			if(respXML == null)
				respXML = "";
			
			log.debug("respXML:" + respXML);
			
			resp.getWriter().print(respXML);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
