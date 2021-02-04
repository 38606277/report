package root.mqtt.configure;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.paho.client.mqttv3.*;
import root.mqtt.service.MqttTaskService;
import root.report.db.DbFactory;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class PushCallback implements MqttCallback,MqttCallbackExtended {

    private static Logger log = Logger.getLogger(PushCallback.class);

    private MqttClient client;
    private MqttConnectOptions options;
    private String clientinid;
    private String topic;
    private int qos;
    private Map paramMap;
    private MqttTaskService mqttTaskService;
    private MqttPushClient mqttPushClient;

    public PushCallback(MqttClient client,MqttConnectOptions options,  int qos,Map paramMap,MqttTaskService mqttTaskService,MqttPushClient mqttPushClient ) {
            this.client= client;
            this.options=options;
            this.clientinid=client.getClientId();
            this.qos=qos;
            this.paramMap=paramMap;
            this.mqttTaskService=mqttTaskService;
            this.topic=paramMap.get("topic").toString();
            this.mqttPushClient =mqttPushClient;
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.info(clientinid+"连接断开，重连");
        this.stop();
        this.start();
        log.info("重新开始连接");
    }
    public void start() {
        try {
            while (true) {
                try {
                    //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
                    if (!client.isConnected()) {
                        System.out.println("*****"+clientinid+" client to connect *****");
                        List<Map<String,MqttClient>> clinetList= mqttPushClient.start(paramMap);
                        for (int i = 0; i < clinetList.size(); i++) {
                            if (null != clinetList.get(i).get(clientinid) && !"".equalsIgnoreCase(clinetList.get(i).get(clientinid).toString())) {
                                client=clinetList.get(i).get(clientinid);

                            }
                        }

                    }
                    if (client.isConnected()) {//连接成功，跳出连接
                        //订阅消息
                        Thread.sleep(1000);
                        System.out.println("***** "+clientinid+" connect success *****");
                        client.subscribe(topic, 1);
                        break;
                    }
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            // 断开连接
            client.disconnect();
            // 关闭客户端
            client.close();
            log.info("先关停");
        } catch (MqttException e) {
            try {
                client.close();
            } catch (MqttException ex) {
                ex.printStackTrace();
            }
            //e.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        // 客户端连接成功
        log.info("再次连接成功，重新订阅主题...");
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("接收消息主题 : " + topic);
        log.info("接收消息message : " + message);
        String pJson = null;
        byte[] payload=message.getPayload();
        try {
            pJson = new String(payload,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=JSONObject.parseObject(pJson);
        if(null!=jsonObject) {
            try {
                StringBuilder sb = new StringBuilder();
                StringBuilder sv = new StringBuilder();
                String insertSql="";
                String dbname = paramMap.get("targetDB").toString().trim();
                SqlSession sqlSession=DbFactory.Open(dbname);
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
                sb.append("`messagetext`,`message_create_date`,");
                sv.append("'" + message + "',current_timestamp(6),");
                String targetTable=paramMap.get("targetTable").toString().trim();

                insertSql = "insert into `"+targetTable+"` (" + sb.deleteCharAt(sb.length() - 1) + ")values(" + sv.deleteCharAt(sv.length() - 1) + ")";
                log.info("接收消息内容组装插入SQL :" + insertSql);
                // mqttTaskService.inserSql(dbname,insertSql);
                sqlSession.update("mqtttask.insertSql", insertSql);
                //this.publish("TEST_PPP",insertSql);
                /*MqttMessage messagess = new MqttMessage();
                messagess.setQos(0);
                messagess.setRetained(false);
                messagess.setPayload(insertSql.getBytes());
                client.publish("NEWTEST",messagess);*/
            }catch (Exception e){
                e.printStackTrace();
            }
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
