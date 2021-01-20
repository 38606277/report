package root.mqtt.configure;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.*;

import java.io.UnsupportedEncodingException;

public class PushCallback implements MqttCallback {
    private MqttClient client;
    private String clientinid;

    public PushCallback(MqttClient client) {
            this.client= client;
            this.clientinid = client.getClientId();
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，重连");
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
        System.out.println("接收消息clientinid : " + clientinid);
        System.out.println("接收消息message : " + message);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload(),"UTF-8"));
        String pJson = null;
        byte[] payload=message.getPayload();
        try {
            pJson = new String(payload,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonValue=JSONObject.parseObject(pJson);

        System.out.println("接收消息内容 jsonValue：" + jsonValue);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println(token);
        System.out.println("消息发送成功");
        try {
            System.out.println(token.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
