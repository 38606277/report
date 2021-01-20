package root.mqtt.configure;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import root.report.db.DbFactory;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class PushCallback implements MqttCallback,MqttCallbackExtended {
    private MqttClient client;
    private String clientinid;
    private MqttConnectOptions options;
    private String topic;
    private int qos;

    public PushCallback(MqttClient client,MqttConnectOptions options, String topic, int qos) {
            this.client= client;
            this.clientinid = client.getClientId();
            this.options=options;
            this.topic=topic;
            this.qos=qos;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，重连");
        try {
            client.reconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void connectComplete(boolean b, String s) {
        // 客户端连接成功
        System.out.println("再次连接成功，重新订阅主题...");
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
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
        JSONObject jsonObject=JSONObject.parseObject(pJson);
        StringBuilder sb = new StringBuilder();
        StringBuilder sv = new StringBuilder();
        String insertString="";
        if(null!=jsonObject) {
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
                sb.append("`"+entry.getKey() + "`,");
                sv.append("'" + entry.getValue() + "',");
            }
            insertString = "insert into mqtt_task (" + sb.deleteCharAt(sb.length() - 1) + ")values(" + sv.deleteCharAt(sv.length() - 1) + ")";
            System.out.println("接收消息内容 :" + insertString);
            System.out.println("接收消息内容 :" + jsonObject);
            //DbFactory.Open(jsonObject.getString("targetDB")).insert("mqtttask.insertSql", insertString);
        }
        String[] data  = topic.substring(1).split("/");

        if(data.length !=3) {
            System.out.println("非物联网订阅信息：" + topic);
            return ;
        }

        String prefix = data[0];//固定前缀
        long gatewayId = Long.parseLong(data[1]);//网关编号
        String type = data[2];//订阅类型

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //String receiveTime = df.format(new Date(timestamp));
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
