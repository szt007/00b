package com.yellowcong.baidu.yuyin.model;
/**
 * 
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午5:15:53<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 * 文字变 语音参数
 * tex 	必填 	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
 * lan 	必填 	语言选择,填写zh
 * tok 	必填 	开放平台获取到的开发者 access_token
 * ctp 	必填 	客户端类型选择，web端填写1
 * cuid 必填 	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
 * spd 	选填 	语速，取值0-9，默认为5中语速
 * pit 	选填 	音调，取值0-9，默认为5中语调
 * vol 	选填 	音量，取值0-9，默认为5中音量
 * per 	选填 	发音人选择，取值0-3, 0、4为女声，1、3为男声，默认为女声
 * @author yellowcong
 * @date 2016年3月29日
 *TEXT_AUDIO
 */
public class Text2Audio {
	//必填 	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
	private String tex; 
	//必填 	语言选择,填写zh
	private String lan; 	
	//必填 	开放平台获取到的开发者 access_token
	private String tok ;	
	//必填 	客户端类型选择，web端填写1
	private String ctp 	;
	//必填 	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
	private String cuid ;	
	//选填 	语速，取值0-9，默认为5中语速
	private String spd 	;
	//选填 	音调，取值0-9，默认为5中语调
	private String pit 	;
	//选填 	音量，取值0-9，默认为5中音量
	private String vol 	;
	//选填 	发音人选择，取值0-1, 0为女声，1为男声，默认为女声
	private String per 	;
	
	public String getTex() {
		return tex;
	}
	public void setTex(String tex) {
		this.tex = tex;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getTok() {
		return tok;
	}
	public void setTok(String tok) {
		this.tok = tok;
	}
	public String getCtp() {
		return ctp;
	}
	public void setCtp(String ctp) {
		this.ctp = ctp;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getSpd() {
		return spd;
	}
	public void setSpd(String spd) {
		this.spd = spd;
	}
	public String getPit() {
		return pit;
	}
	public void setPit(String pit) {
		this.pit = pit;
	}
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
}
