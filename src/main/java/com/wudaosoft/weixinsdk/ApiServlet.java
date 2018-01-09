/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.wudaosoft.weixinsdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 * 
 * @author Changsoul.Wu
 * @date 2014年3月28日 下午12:22:35
 */
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 4120271321525038178L;

	private static final Logger log = LoggerFactory.getLogger(ApiServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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

			boolean flag = CommonApi.checkSignature("", signature, timestamp, nonce);

			if (flag) {
				log.info("check weixin signature success");
				resp.getWriter().write(echostr);
			} else {
				log.info("check weixin signature failed");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/xml; encoding=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		
		String respXML = "";
		
		try {
					
//			try {
//				respXML = WeiXinMessageProcess.processRequest(req);
//			} catch (AesException e) {
//				log.warn(e.getMessage());
//			} catch (Exception e) {
//				log.error(e.getMessage(), e);
//			}
			
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
