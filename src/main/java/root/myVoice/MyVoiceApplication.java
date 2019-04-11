package root.myVoice;


import com.iflytek.cloud.speech.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import root.report.common.RO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/reportServer/MyVoiceApplication")
public class MyVoiceApplication extends RO {
	@RequestMapping(value = "/uploadai", produces = "text/plain;charset=UTF-8")
	public String uploadai(@RequestParam("file") MultipartFile file) throws Exception {
		String aa = "单,继,坤";
		//uploadUserWords(aa);
		Map<String,String> map =new HashMap();
//		UUID uuid = UUID.randomUUID();
//		String path = System.getProperty("java.io.tmpdir")+ File.separator;
//		String fileName = uuid.toString()+".wav";
//		//可识别的wav文件
//		String wavFile = System.getProperty("java.io.tmpdir")+ File.separator+"/"+uuid.toString()+".wav";
//		File temfiles = new File(path,fileName);
//		try {
//			file.transferTo(temfiles);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		SRTool sr = new SRTool();
		String words = null;
		try {
			words = sr.voice2words(file);
			//words = sr.voice2words(wavFile);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("讯飞识别的语音结果："+words);

		if(null==words && "".equals(words)){
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
