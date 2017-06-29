/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */ 
 
package com.wudaosoft.weixinsdk.handler;

import com.wudaosoft.weixinsdk.message.receive.ReceiveEventMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveImageMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLinkMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveLocationMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveTextMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVideoMsg;
import com.wudaosoft.weixinsdk.message.receive.ReceiveVoiceMsg;

/**
 * <p>处理微信服务器回调消息引擎  </p>
 * @author Changsoul.Wu
 * @date 2014年3月31日 上午10:25:26
 */
public interface WeiXinMessageHandler {

	/**
	 * 处理文本消息
	 * @param bean
	 * @return
	 */
	String processTextMsg(ReceiveTextMsg textMsg);

	/**
	 * 处理图片消息
	 * @param bean
	 * @return
	 */
	String processImageMsg(ReceiveImageMsg imageMsg);

	/**
	 * 处理语音消息
	 * @param bean
	 * @return
	 */
	String processVoiceMsg(ReceiveVoiceMsg voiceMsg);

	/**
	 * 处理视频消息
	 * @param bean
	 * @return
	 */
	String processVideoMsg(ReceiveVideoMsg videoMsg);

	/**
	 * 处理位置消息
	 * @param bean
	 * @return
	 */
	String processLocationMsg(ReceiveLocationMsg locationMsg);

	/**
	 * 处理链接消息
	 * @param bean
	 * @return
	 */
	String processLinkMsg(ReceiveLinkMsg linkMsg);

	/**
	 * 处理订阅事件
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 * EventKey	 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 * Ticket	 二维码的ticket，可用来换取二维码图片
	 * @param eventMsg
	 * @return
	 */
	String processSubscribeEvent(ReceiveEventMsg eventMsg);

	/**
	 * 处理取消订阅事件
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 * @param eventMsg
	 * @return
	 */
	String processUnsubscribeEvent(ReceiveEventMsg eventMsg);

	/**
	 * 处理菜单拉取消息事件
	 * 
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，CLICK
	 * EventKey	 事件KEY值，与自定义菜单接口中KEY值对应
	 * @param eventMsg
	 * @return
	 */
	String processClickEvent(ReceiveEventMsg eventMsg);

	/**
	 * 处理菜单跳转链接事件
	 * 
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，VIEW
	 * EventKey	 事件KEY值，设置的跳转URL
	 * @param eventMsg
	 * @return
	 */
	String processViewEvent(ReceiveEventMsg eventMsg);

	/**
	 * 处理扫描带参数二维码事件
	 * 
	 * 1.如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
	 * 2.如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
	 * 
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，SCAN
	 * EventKey	 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	 * Ticket	 二维码的ticket，可用来换取二维码图片
	 * @param eventMsg
	 * @return
	 */
	String processScanEvent(ReceiveEventMsg eventMsg);

	/**
	 * 处理上报地理位置事件
	 * 
	 * ToUserName	开发者微信号
	 * FromUserName	 发送方帐号（一个OpenID）
	 * CreateTime	 消息创建时间 （整型）
	 * MsgType	 消息类型，event
	 * Event	 事件类型，LOCATION
	 * Latitude	 地理位置纬度
	 * Longitude	 地理位置经度
	 * Precision	 地理位置精度
	 * @param eventMsg
	 * @return
	 */
	String processLocationEvent(ReceiveEventMsg eventMsg);

}
