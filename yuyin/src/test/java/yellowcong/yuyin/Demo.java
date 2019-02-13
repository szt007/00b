package yellowcong.yuyin;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yellowcong.baidu.utils.AudioUtils;
import com.yellowcong.baidu.utils.FileUtils;
import com.yellowcong.baidu.yuyin.AudioClient;
import com.yellowcong.baidu.yuyin.model.Result;
import com.yellowcong.baidu.yuyin.model.Text2Audio;

/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午5:31:46<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class Demo {
	
	private AudioClient client = null;
	
	@Before
	public void setUp(){
		String appid = "XXOVSG9gc385oEfKWCoMKA9m";
		String secret = "iMXdn2NmPkfQxSVWjNLgmh7x9t8XV89j";
		
//		String appid = "lhnN8uVsySG6SzdbEBSx2RwA";
//		String secret = "V71cijc3Q9DZ5ZbFTd8sthFekDR3dTAC";
		client = new AudioClient(appid,secret);
		
	}
	
	@Test
	public void testText2Audio(){
		
		InputStream in = client.text2Audio("把你挠吐露皮了");
		FileUtils.copyInputStreamToFile(in, new File("D:/xx.mp3"));
	}
	
	@Test
	public void test2Text2Audio(){
		Text2Audio word = new Text2Audio();
		word.setTex("王贱狗,着急了吧");
		//语音 0-4
		word.setPer("4");
		InputStream in = client.text2Audio(word);
		FileUtils.copyInputStreamToFile(in, new File("D:/xx.mp3"));
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午10:52:00<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:将音频转文件
	 * @throws Exception
	 */
	@Test
	public void testAduio2Text() throws Exception{
		Text2Audio word = new Text2Audio();
		word.setTex("你是逗逼");
		InputStream in = client.text2Audio(word);
		final File outFile = new File("D:/test.mp3");
		FileUtils.copyInputStreamToFile(in, outFile);
		
		//开启线程 ，播放音乐
		new Thread() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//播放文件
				try {
					AudioUtils.getInstance().playMP3(outFile.getAbsolutePath());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.start();
		
		//转义翻译结果
		Result result = client.audio2Text(outFile);
		
		//打印翻译的结果
		System.out.println(result.getResult()[0]);
		
	}
	
	/**
	 * 创建日期:2018年1月14日<br/>
	 * 创建时间:下午6:07:47<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:测试token的获取
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testGetToken() throws Exception, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		String token = client.getToken();
		Assert.assertNotNull(token);
		//24.16580a9942bdf925310a25aabd947dee.2592000.1544179352.282335-14706298
		System.out.println(token);//token 每次都不一样
	}
	
}
