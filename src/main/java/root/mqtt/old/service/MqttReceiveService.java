package root.mqtt.old.service;

public interface MqttReceiveService {
	
	void handlerMqttMessage(String topic, byte[] payload,long timestamp);
}
