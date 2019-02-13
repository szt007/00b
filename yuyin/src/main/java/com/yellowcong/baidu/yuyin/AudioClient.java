package com.yellowcong.baidu.yuyin;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import com.yellowcong.baidu.utils.AudioUtils;
import com.yellowcong.baidu.utils.Base64Utils;
import com.yellowcong.baidu.utils.HttpClientUtils;
import com.yellowcong.baidu.utils.JsonUtils;
import com.yellowcong.baidu.utils.TrustAnyTrustManager;
import com.yellowcong.baidu.yuyin.constant.Constants;
import com.yellowcong.baidu.yuyin.model.Audio2Text;
import com.yellowcong.baidu.yuyin.model.Result;
import com.yellowcong.baidu.yuyin.model.Text2Audio;

/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午4:57:51<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class AudioClient {
	//"lhnN8uVsySG6SzdbEBSx2RwA";
	//id
	private  String appid ;
	// "V71cijc3Q9DZ5ZbFTd8sthFekDR3dTAC";
	//密钥
	private  String secret ;
	
	//用户的token
	private String token ;
	
	public AudioClient(String appid, String secret) {
		super();
		
		this.appid = appid;
		this.secret = secret;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取Token
	 * @return token
	 */
	public  String getToken(){
		String token = null;
		try {
//			String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id="+VoiceUtils.appid+"&client_secret="+VoiceUtils.secret;
			
			//获取TOKEN的地址
			String tokenUrl = String.format(Constants.TOKEN_URL, this.appid,this.secret);
			
			//发送请求
			String result = TrustAnyTrustManager.connect(tokenUrl);
			
			//获取token
			token = JsonUtils.getMapper().readTree(result).get("access_token").asText();
			
			//设定类的token
			this.token = token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午5:01:36<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:将字符串转化为语音
	 * @param word 需要转化为语音的文字
	 * @return 返回数据流
	 */
	public InputStream text2Audio(String word){
		
		Text2Audio word2Voice = new Text2Audio();
		//设定转化为文件的字符串
		word2Voice.setTex(word);
		
		//调用
		return this.text2Audio(word2Voice);
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午5:50:24<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:将文本转转化为音频
	 * @param word2Voice
	 * @return
	 */
	public InputStream text2Audio(Text2Audio word2Voice){
		//当token不存在的情况
		if(this.isEmpty(this.token)){
			this.token= this.getToken();
		}
		
		//设定token
		word2Voice.setTok(this.token);

		//判断是否有cuid
		if(this.isEmpty(word2Voice.getCuid())){
			word2Voice.setCuid(UUID.randomUUID().toString());
		}
		
		if(this.isEmpty(word2Voice.getLan())){
			//默认的语言
			word2Voice.setLan(Constants.DEFAULT_LAN);
		}
		if(this.isEmpty(word2Voice.getCtp())){
			//访问类型  
			word2Voice.setCtp(Constants.DEFAULT_CTP);
		}
		if(this.isEmpty(word2Voice.getPer())){
			word2Voice.setPer(Constants.DEFAULT_PER);
		}
		
		//获取返回的参数
		String param = this.getParam(word2Voice);
		if(param == null || "".equals(param)){
			return null;
		}
		
		//获取连接诶地址
		String url = Constants.TEXT_AUDIO_URL+"?"+param;
		
		//获取语音数据
		InputStream in= HttpClientUtils.downLoad(url);
		return in;
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午10:46:02<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要: 判断是否为空
	 * @param str
	 * @return
	 */
	private boolean isEmpty(String str){
		if(str == null || str.trim().equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午10:36:03<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:将声音转化为 文字
	 * @param audioFile
	 * @return
	 */
	public Result audio2Text(File audioFile){
		
		Audio2Text parm = new Audio2Text();
		//视屏格式
		parm.setFormat("pcm");
		//采样率， 8000 或者 16000， 推荐 16000 采用率
		parm.setRate(16000);
		
		//单通道
		parm.setChannel(1);
		parm.setCuid(UUID.randomUUID().toString());
		
		//当token不存在的情况
		if(this.token == null || "".equals(this.token)){
			this.token= this.getToken();
		}
		parm.setToken(this.getToken());
		
		File targertFile = audioFile;
		//当不是pcm文件的时候
		if(!audioFile.getName().endsWith("pcm")){
			AudioUtils audio = AudioUtils.getInstance();
			
			//将文件转化为 pcm文件
			File outPcmFile = new File(audioFile.getParentFile().getAbsolutePath()+File.pathSeparator+UUID.randomUUID().toString()+".pcm");
			if(!audio.convertMP32Pcm(audioFile.getAbsolutePath(), outPcmFile.getAbsolutePath())){
				return null;
			}
			targertFile = outPcmFile;
		}
		
		//加密音频文件
		Base64Utils utils = Base64Utils.getInstance();
		
		//读取文件basecode编码
		String mp3Base64Str = utils.file2Base64(targertFile);
		
		//读取文件大小
		int len = utils.getFileSize(targertFile);
		
		parm.setLen(len);
		parm.setSpeech(mp3Base64Str);
		
		String json = JsonUtils.object2Json(parm);
		//发送请求到服务器
		String jsonResult = HttpClientUtils.postJson(Constants.AUDIO_TEXT_URL, json);
		
		//删除临时文件
		if(!targertFile.getAbsolutePath().equals(audioFile.getAbsolutePath())){
			targertFile.delete();
		}
		System.out.println(jsonResult);
		Result result = JsonUtils.json2Object(jsonResult, Result.class);
		return result;
	}
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午5:43:24<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:获取请求的参数
	 * @param voice 
	 * @return
	 */
	public String getParam(Text2Audio word2Voice) {
		String paramStr = null;
		try {
			Class<?> clazz = word2Voice.getClass();
			Field [] fields = word2Voice.getClass().getDeclaredFields();
			StringBuffer sb = new StringBuffer();
			for(Field field:fields){
				String fieldNm = field.getName();
				
				//获取方法名称
				String methodNm = this.getMethodName(field);
				
				//执行方法
				Object obj = clazz.getMethod(methodNm).invoke(word2Voice);
				
				//当参数数据为空的情况，直接
				if(obj != null && !"".equals(obj.toString())){
					sb.append(fieldNm+"="+obj.toString()+"&");
				}
			}
			if(sb.length() >0){
				paramStr = sb.substring(0, sb.length()-1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramStr;
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午5:35:44<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:根据字段的名称来获取get方法的名称
	 * @param field 字段
	 * @return
	 */
	private String getMethodName(Field field){
		String fieldNm = field.getName();
		String methodNm = "get"+fieldNm.substring(0, 1).toUpperCase()+fieldNm.substring(1);
		return methodNm;
	}
	
}
