package yellowcong.yuyin;

import org.junit.Test;

import com.yellowcong.baidu.utils.AudioUtils;

/**
 * 创建日期:2018年1月14日
 * 创建时间:下午10:09:39
 * 创建者    :yellowcong
 * 机能概要:MP3转PCM Java方式实现
 */
public class TestAudioUtils {
	//测试播放音频
	@Test
	public void testPaly() throws Exception{
		AudioUtils utils  = AudioUtils.getInstance();
		utils.playMP3("D:/xx.mp3");
	}
	
	@Test
	public void testConvert() throws Exception{
		AudioUtils utils  = AudioUtils.getInstance();
		utils.convertMP32Pcm("D:/xx.mp3", "D:/xx.pcm");
	}
}