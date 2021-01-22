package root.mqtt.configure;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import root.mqtt.service.MqttTaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MqttPushClient {
    private static Logger log = Logger.getLogger(MqttPushClient.class);

    private MqttTaskService mqttTaskService;
    public List<Map<String,MqttClient>> clinetList = new ArrayList<>();

    public MqttPushClient(List<Map<String,MqttClient>> clinetList,MqttTaskService mqttTaskService) {
        this.clinetList= clinetList;
        this.mqttTaskService=mqttTaskService;
    }

    private static final byte[] WILL_DATA;
    static {
        WILL_DATA = "offline".getBytes();
    }
    private MqttClient client;
    public List<Map<String,MqttClient>> start(Map paramMap) throws MqttException {
        try {
            String url=paramMap.get("host").toString();
            String clientId=paramMap.get("clientinid").toString();
            String topic = paramMap.get("topic").toString();
            client = new MqttClient(url, clientId, new MemoryPersistence());
            MqttConnectOptions options = getOptions(paramMap);
            client.setCallback(new PushCallback(client,options,0,paramMap,mqttTaskService,this));
            client.connect(options);
            Map<String,MqttClient> mapClient = new HashMap<String,MqttClient>();
            mapClient.put(clientId, client);
            for (int i = 0; i < clinetList.size(); i++) {
                if (null != clinetList.get(i).get(clientId) && !"".equalsIgnoreCase(clinetList.get(i).get(clientId).toString())) {
                    clinetList.remove(i);
                    break;
                }
            }
            clinetList.add(mapClient);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return clinetList;
    }

    public MqttConnectOptions getOptions(Map paramMap) {
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
        // 这里设置为true表示每次连接到服务器都以新的身份连接
        //options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(paramMap.get("username").toString());
        // 设置连接的密码
        options.setPassword(paramMap.get("password").toString().toCharArray());
        options.setServerURIs(StringUtils.split(paramMap.get("host").toString(), ","));
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(Integer.parseInt(paramMap.get("timeout").toString()));
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(Integer.parseInt(paramMap.get("keepalibe").toString()));
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
        options.setWill("willTopic", WILL_DATA, 2, false);
        return options;
    }

    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic, String pushMessage) {
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     *
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        log.info("进入消息");
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if (null == mTopic) {
            log.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            log.error("开始发送");
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
