package root.mqtt.old.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import root.mqtt.old.configure.MqttSendMessage;
import root.mqtt.old.service.TopicService;
import root.report.common.RO;


/**
 * 测试网关 1319100003
 * @author cannon
 */
@RestController
@RequestMapping(value = "/Topic")
public class TopicController extends RO {
	
	@Resource
	private TopicService topicService;

	@Resource
	private MqttSendMessage mqttSendMessage;
	
	/**
	 * 添加网关
	 * @return
	 */
    @RequestMapping(value="/addTopicByGateway",produces = "text/plain;charset=UTF-8")
    public String addTopicByGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.addTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("添加成功", "");
    }
    
    /**
	 * 删除网关
	 * @return
	 */
    @RequestMapping(value="/deleteTopicByGateway",produces = "text/plain;charset=UTF-8")
    public String deleteTopicByGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.removeTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("删除成功", "");
    }
    
//    /**
//	 * 修改网关配置
//	 * @return
//	 */
//    @RequestMapping(value="/updateGatewayConfig",produces = "text/plain;charset=UTF-8")
//    public String updateGatewayConfig(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
//    	String gatewayNumber = pJson.getString("gatewayNumber");
//    	String topic = "/001/"+ gatewayNumber + "/set";
//    	pJson.remove("gatewayNumber");
//    	mqttSendMessage.sendMessage(pJson.toJSONString(), topic);
//        return SuccessMsg("修改成功", "");
//    }
	/*public static void main(String [] args){
		List<String> ss=new ArrayList<>();
		Map info = new HashMap();
		info.put("host","tcp://132.232.111.110:1883");
		info.put("topic","/001/1320040017/bt");
		info.put("username","eam");
		info.put("password","Eam#2020");
		info.put("clientinid","gao");
		info.put("timeout","10000");
		info.put("keepalibe","20");
		info.put("state","1");
		//this.addTopicByGateway();
		MqttPushClient mqttPushClient = new MqttPushClient();
		try {
			//System.out.println(mqttPushClient.isConnected());
			mqttPushClient.start(info);

		} catch (MqttException e) {
			e.printStackTrace();
		}
		mqttPushClient.subscribe(info.get("topic").toString());
		mqttPushClient.subscribe("/001/1320040014/bt");
		mqttPushClient.subscribe("/001/1320040003/bt");
		mqttPushClient.subscribe("/001/1320040005/bt");


	}*/
}
