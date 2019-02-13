package yellowcong.yuyin;

import java.io.File;

import org.junit.Test;

import com.yellowcong.baidu.utils.Base64Utils;

/**
 * 创建日期:2018年1月14日<br/>
 * 创建时间:下午7:10:28<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class TestBase64Utils {
	
	@Test
	public void testJpg2Base64(){
		Base64Utils utils = Base64Utils.getInstance();
		String str = utils.file2Base64(new File("D:/demo_identificate.jpg"));
		System.out.println(str);
		
	}
	@Test
	public void testBase642File(){
		
		Base64Utils utils = Base64Utils.getInstance();
		String str = utils.file2Base64(new File("D:/demo_identificate.jpg"));
		
		utils.base64ToFile(str, new File("D://xx.jpg"));
		
	}
	
	
}
