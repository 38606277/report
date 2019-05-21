package root.myVoice;


import com.baidu.aip.speech.AipSpeech;
import com.iflytek.cloud.speech.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import root.report.common.RO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

;

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

		if(null==words || "".equals(words)){
			System.out.println("讯飞识别的语音结果：null");
			map.put("status","error");
			map.put("content","对不起，请您在描述一遍！");
			return ErrorMsg("语音识别失败","没有获取到语音信息");
		}
		String result = SR2Words.sr2words(words);
		System.out.println("讯飞识别的语音结果："+result);
		map.put("status","success");
		map.put("content",result);

		return SuccessMsg("语音识别", map);
	}

	@RequestMapping(value="/yuyin")
	public void getDataAuthList(@RequestBody String data, HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");//解决乱码
			//String data= URLDecoder.decode(request.getParameter("data"),"UTF-8");
			System.out.println(data);
			//换成你在讯飞申请的APPID
			SpeechUtility.createUtility("appid=5cac4812");
			//合成监听器
			SynthesizeToUriListener synthesizeToUriListener = XunfeiLib.getSynthesize();
			String fileName=XunfeiLib.getFileName("tts_test.pcm");
			XunfeiLib.delDone(fileName);
			//1.创建SpeechSynthesizer对象
			SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
			//2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
			mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
			mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
			mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
			mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
			//3.开始合成
			//设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
			mTts.synthesizeToUri(data,fileName ,synthesizeToUriListener);
			//设置最长时间
			int timeOut=60;
			int star=0;
			//校验文件是否生成
			while(!XunfeiLib.checkDone(fileName)){
				try {
					Thread.sleep(1000);
					star++;
					if(star>timeOut){
						throw new Exception("合成超过"+timeOut+"秒！");
					}
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					break;
				}
			}
			//输出 wav IO流
			try{
				response.setHeader("Content-Type", "audio/mpeg");
				File file = new File(fileName);
				int len_l = (int) file.length();
				byte[] buf = new byte[2048];
				FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream();
				//写入WAV文件头信息
				out.write(XunfeiLib.getWAVHeader(len_l,8000,2,16));
				len_l = fis.read(buf);
				while (len_l != -1) {
					out.write(buf, 0, len_l);
					len_l = fis.read(buf);
				}
				out.flush();
				out.close();
				fis.close();
				//删除文件和清除队列信息
				XunfeiLib.delDone(fileName);
				file.delete();
			}catch (Exception e){
				System.out.println(e);
			}
	}

	@RequestMapping(value = "/uploadmp3")
	public String uploadmp3(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		// 构建上传目录路径
		Map<String,Object> map=new HashMap<String, Object>();
		if (file != null) {
			try {
				UUID uuid=UUID.randomUUID();
				//String wavFile = System.getProperty("java.io.tmpdir")+ File.separator+uuid.toString()+".mp3";
				//String pcmFile = System.getProperty("java.io.tmpdir")+ File.separator+uuid.toString()+".pcm";
				byte[] pcmBytes = mp3Convertpcm(file.getInputStream());
				JSONObject resultJson = speechBdApi(pcmBytes);
				System.out.println(resultJson.toString());
				String result =null;
				if (null != resultJson && resultJson.getInt("err_no") == 0) {
					result= resultJson.getJSONArray("result").get(0).toString().split("，")[0];
				}
			//			File temfiles = new File(wavFile);
//				File temfiless = new File(pcmFile);
//				temfiless.createNewFile();
//				try {
//					file.transferTo(temfiles);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				AudioUtils utils  = AudioUtils.getInstance();
//				utils.convertMP32Pcm(wavFile, pcmFile);
				//utils.playMP3("D:/hb/155866.mp3");

				SRTool sr = new SRTool();
				String words = null;
////			try {
////				//words = sr.voice2words(temfilePath);
////				words = sr.voice2words(pcmBytes);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
//				System.out.println("讯飞识别的语音结果：" + words);
//				if (null == words || "".equals(words)) {
//					System.out.println("讯飞识别的语音结果：null");
//					map.put("status", "error");
//					map.put("content", "对不起，请您在描述一遍！");
//					return ErrorMsg("语音识别失败", "没有获取到语音信息");
//				}
//				String result = SR2Words.sr2words(words);
//				System.out.println("讯飞识别的语音结果：" + result);
//				map.put("status", "success");
//				map.put("content", result);

				return SuccessMsg("语音识别", result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
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

	/**
	 * @Description MP3转换pcm
	 * @param mp3Stream
	 *            原始文件流
	 * @return 转换后的二进制
	 * @throws Exception
	 * @author liuyang
	 * @blog http://www.pqsky.me
	 * @date 2018年1月30日
	 */
	public byte[] mp3Convertpcm(InputStream mp3Stream) throws Exception {
		// 原MP3文件转AudioInputStream
		AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(mp3Stream);
		// 将AudioInputStream MP3文件 转换为PCM AudioInputStream
		AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,
				mp3audioStream);
		byte[] pcmBytes = IOUtils.toByteArray(pcmaudioStream);
		pcmaudioStream.close();
		mp3audioStream.close();
		return pcmBytes;
	}

	/**
	 * @Description 调用百度语音识别API
	 * @param pcmBytes
	 * @return
	 * @author liuyang
	 * @blog http://www.pqsky.me
	 * @date 2018年1月30日
	 */
	public static final String APP_ID = "16309438";

	public static final String API_KEY = "KCWcpKpzoQ3tHHzdanWRH9cv";

	public static final String SECRET_KEY = "fA22Gjd0qUrUahUW6IODA1Ql3bBVeOuo";
	public static JSONObject speechBdApi(byte[] pcmBytes) {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		// 调用接口
		JSONObject res = client.asr(pcmBytes, "pcm", 16000, null);
		return res;
	}

}
