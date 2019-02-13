package com.yellowcong.baidu.yuyin.constant;


/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午5:05:53<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class Constants {

	//获取授权的地址
	//%d 表示匹配的参数
	public static String TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=%s&client_secret=%s";
	
	//字符串转化为声音的url
	/**
	 * 文字变 语音参数
	 * tex 	必填 	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
	 * lan 	必填 	语言选择,填写zh
	 * tok 	必填 	开放平台获取到的开发者 access_token
	 * ctp 	必填 	客户端类型选择，web端填写1
	 * cuid 必填 	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
	 * spd 	选填 	语速，取值0-9，默认为5中语速
	 * pit 	选填 	音调，取值0-9，默认为5中语调
	 * vol 	选填 	音量，取值0-9，默认为5中音量
	 * per 	选填 	发音人选择，取值0-4, 0,1,4为女声，1，3为男声，默认为女声
	 */
	public static String TEXT_AUDIO_URL = "http://tsn.baidu.com/text2audio";
	
	//默认语言
	public static String DEFAULT_LAN = "zh";
	
	//客户端类型，默认为1 
	public static String DEFAULT_CTP = "1";
	
	//	选填 	发音人选择，取值0-4, 0,1,4为女声，1，3为男声，默认为女声
	public static String DEFAULT_PER = "4";
	
	//将音频转化为字符串
	public static String AUDIO_TEXT_URL = "http://vop.baidu.com/server_api";
	
}
