package root.mqtt.old.service;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Service;
import root.mqtt.old.configure.MyMqttPahoMessageDrivenChannelAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TopicService {

	public List<Map<String,MqttClient>> clinetList = new ArrayList<>();
	private static Logger log = Logger.getLogger(TopicService.class);
	@Resource
	private MyMqttPahoMessageDrivenChannelAdapter myMqttPahoMessageDrivenChannelAdapter;
	
	/**
	 * 按网关添加订阅
	 * @param gateway
	 */
	public void addTopicByGateway(String gateway) {
		myMqttPahoMessageDrivenChannelAdapter.addTopicByGateway(gateway);
	}

	/**
	 * 按网关移除订阅
	 * @param gateway
	 */
	public void removeTopicByGateway(String gateway) {
		myMqttPahoMessageDrivenChannelAdapter.removeTopicByGateway(gateway);
	}


}
