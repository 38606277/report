package root.mqtt.configure;
import org.eclipse.paho.client.mqttv3.*;

public class PushCallback implements MqttCallback {
    private MqttClient client;

    public PushCallback(MqttClient client) {
            this.client= client;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，可以做重连");
        try {
            client.reconnect();
            System.out.println("再次连接成功");
        } catch (MqttException e) {
            System.out.println("再次连接失败");
            e.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload(),"UTF-8"));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println(token);
        try {
            System.out.println(token.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
