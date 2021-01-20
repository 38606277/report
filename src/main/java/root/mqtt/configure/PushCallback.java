package root.mqtt.configure;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.paho.client.mqttv3.*;
import root.mqtt.service.MqttTaskService;
import root.report.db.DbFactory;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.log4j.Logger;

public class PushCallback implements MqttCallback,MqttCallbackExtended {

    private static Logger log = Logger.getLogger(PushCallback.class);

    private MqttClient client;
    private String clientinid;
    private MqttConnectOptions options;
    private String topic;
    private int qos;
    private Map paramMap;
    private MqttTaskService mqttTaskService;

    public PushCallback(MqttClient client,MqttConnectOptions options, String topic, int qos,Map paramMap,MqttTaskService mqttTaskService) {
            this.client= client;
            this.clientinid = client.getClientId();
            this.options=options;
            this.topic=topic;
            this.qos=qos;
            this.paramMap=paramMap;
            this.mqttTaskService=mqttTaskService;
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.info("连接断开，重连");
        try {
            client.reconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void connectComplete(boolean b, String s) {
        // 客户端连接成功
        log.info("再次连接成功，重新订阅主题...");
        log.info(s);
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("接收消息主题 : " + topic);
        log.info("接收消息clientinid : " + clientinid);
        log.info("接收消息message : " + message);
        log.info("接收消息Qos : " + message.getQos());
        log.info("接收消息内容 : " + new String(message.getPayload(),"UTF-8"));
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
        String insertSql="";
        String dbname = paramMap.get("targetDB").toString().trim();
        SqlSession sqlSession=DbFactory.Open(dbname);
        if(null!=jsonObject) {
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                log.info(entry.getKey() + "=" + entry.getValue());
                sb.append("`"+entry.getKey() + "`,");
                if(entry.getKey().equals("id")){
                    Integer newId = sqlSession.selectOne("mqtttask.getMaxId");
                    newId = newId == null ? 1 : newId;
                    sv.append("'" + newId + "',");
                }else{
                    sv.append("'" + entry.getValue() + "',");
                }
            }
            String targetTable=paramMap.get("targetTable").toString().trim();

            insertSql = "insert into `"+targetTable+"` (" + sb.deleteCharAt(sb.length() - 1) + ")values(" + sv.deleteCharAt(sv.length() - 1) + ")";
            log.info("接收消息内容 :" + insertSql);
            //mqttTaskService.inserSql(dbname,insertString);
            sqlSession.update("mqtttask.insertSql", insertSql);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info(token);
        log.info("消息发送成功");
        try {
            log.info(token.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
