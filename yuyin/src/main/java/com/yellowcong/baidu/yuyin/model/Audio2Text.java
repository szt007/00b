package com.yellowcong.baidu.yuyin.model;

/**
 * 语音变文字参数
 * 语音识别接口支持 POST 方式
 * 目前 API 仅支持整段语音识别的模式，即需要上传整段语音进行识别
 * 语音数据上传方式有两种：隐示发送和显示发送
 * 原始语音的录音格式目前只支持评测 8k/16k 采样率 16bit 位深的单声道语音
 * 压缩格式支持：pcm（不压缩）、wav、opus、speex、amr、x-flac
 * 系统支持语言种类：中文（zh）、粤语（ct）、英文（en）
 * @author yellowcong
 * @date 2016年3月29日
 */
public class Audio2Text {
	/**
	 * sting 	必填 	语音压缩的格式，请填写上述格式之一，不区分大小写
	 */
	private String format;	
	/**
	 * 	int 	必填 	采样率，支持 8000 或者 16000
	 */
	private int rate; 
	/**
	 * int 	必填 	声道数，仅支持单声道，请填写 1
	 */
	private int channel; 	
	/**
	 * string 	必填 	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
	 */
	private String cuid; 	
	/**
	 * string 	必填 	开放平台获取到的开发者 access_token
	 */
	private String token; 	
	/**
	 * string 	选填 	语音下载地址
	 */
	private String url; 	
	
	/**
	 * 本地语音文件的的字节数，单位字节
	 */
	private int len;

	/**
	 * 本地语音文件的的二进制语音数据 ，需要进行base64 编码。与len参数连一起使用。
	 */
	private String speech;
	
	/**
	 * 用户服务器的识别结果回调地址，确保百度服务器可以访问
	 */
	//private String callback;
	
	/**
	 * 可下载的语音下载地址，与callback连一起使用，确保百度服务器可以访问。
	 */
	//private String url ;

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getSpeech() {
		return speech;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
}
