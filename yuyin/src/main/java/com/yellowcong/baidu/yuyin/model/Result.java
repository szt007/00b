package com.yellowcong.baidu.yuyin.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午10:56:48<br/>
 * 创建者 :yellowcong<br/>
 * 机能概要:返回结果的消息
 */
public class Result {
	@JsonProperty("corpus_no")
	private String corpusNo;

	// 错误消息
	@JsonProperty("err_msg")
	private String errMsg;

	// 错误号
	@JsonProperty("err_no")
	private String errNo;

	// 结果
	private String [] result;
	private String sn;
	public String getCorpusNo() {
		return corpusNo;
	}
	public void setCorpusNo(String corpusNo) {
		this.corpusNo = corpusNo;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrNo() {
		return errNo;
	}
	public void setErrNo(String errNo) {
		this.errNo = errNo;
	}
	public String[] getResult() {
		return result;
	}
	public void setResult(String[] result) {
		this.result = result;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
}
