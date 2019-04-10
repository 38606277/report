package root.myVoice;


import com.iflytek.cloud.speech.*;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import root.report.common.RO;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
@RequestMapping("/reportServer/MyVoiceApplication")
public class MyVoiceApplication extends RO {
	@RequestMapping(value = "/uploadai", produces = "text/plain;charset=UTF-8")
	public String uploadai( @RequestParam("file") MultipartFile file) throws Exception {
		String aa = "单,继,坤";
		uploadUserWords(aa);
		Map<String,String> map =new HashMap();
		UUID uuid = UUID.randomUUID();
		SRTool sr = new SRTool();
		String pcm = "D:/hb/20190410151336.wav";
		//this.convertAudioFiles("D:/hb/20190410125852.wav","D:/hb/te.pcm");

		String words = null;
		try {
			words = sr.voice2wordswav(file);
			//words = sr.voice2words(pcm);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("讯飞识别的语音结果："+words);
		if("".equals(words)){
			System.out.println("讯飞识别的语音结果：null");
			map.put("status","error");
			map.put("content","对不起，请您在描述一遍！");
			return ErrorMsg("语音识别","没有获取到语音信息");
		}
		String result = SR2Words.sr2words(words);
		System.out.println("讯飞识别的语音结果："+result);
		map.put("status","success");
		map.put("content",result);

		return SuccessMsg("语音识别", map);
	}
	public String convertAudioFiles(String wavfilepath,String pcmfilepath){
		FileInputStream fileInputStream;
		FileOutputStream fileOutputStream;
		try {
			fileInputStream = new FileInputStream(wavfilepath);
			fileOutputStream = new FileOutputStream(pcmfilepath);
			byte[] wavbyte = InputStreamToByte(fileInputStream);
			byte[] pcmbyte = Arrays.copyOfRange(wavbyte, 44, wavbyte.length);
			fileOutputStream.write(pcmbyte);
			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(fileOutputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pcmfilepath;
	}
	/**
	 * 输入流转byte二进制数据
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		long size = fis.getChannel().size();
		byte[] buffer = null;
		if (size <= Integer.MAX_VALUE) {
			buffer = new byte[(int) size];
		} else {
			buffer = new byte[8];
			for (int ix = 0; ix < 8; ++ix) {
				int offset = 64 - (ix + 1) * 8;
				buffer[ix] = (byte) ((size >> offset) & 0xff);
			}
		}
		int len;
		while ((len = fis.read(buffer)) != -1) {
			byteStream.write(buffer, 0, len);
		}
		byte[] data = byteStream.toByteArray();
		IOUtils.closeQuietly(byteStream);
		return data;
	}
	private void uploadUserWords(String aa) throws Exception {
		SpeechUtility.createUtility("appid=5cac4812");//申请的appid
		DataUploader dataUploader = new DataUploader();

		UserWords userwords = new UserWords(aa);
		byte[] datas = aa.getBytes("utf-8");//  userwords.toString().getBytes("utf-8");

		dataUploader.setParameter(SpeechConstant.DATA_TYPE, "userword");
		dataUploader.uploadData(speechListener, "userwords", datas);
		System.out.println(dataUploader.getParameter("userwords")+"+++++++++++++++++++++++++");
	}
	/**
	 * 词表上传监听器
	 */
	SpeechListener speechListener = new SpeechListener() {

		@Override
		public void onEvent(int arg0, String arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onCompleted(SpeechError arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onBufferReceived(byte[] arg0) {
			// TODO Auto-generated method stub

		}
	};
}
