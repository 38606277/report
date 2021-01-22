package root.mqtt.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;
import root.form.user.UserModel;
import root.mqtt.configure.MqttPushClient;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.util.*;


@Service
public class MqttTaskService {

	public List<Map<String,MqttClient>> clinetList = new ArrayList<>();
	private static Logger log = Logger.getLogger(MqttTaskService.class);

	public Map<String, Object> getListPage(Map<String, String> map) {
			Map<String,Object> map1=new HashMap<>();
			try {
				SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
				RowBounds bounds = null;
				if (map == null) {
					bounds = RowBounds.DEFAULT;
				} else {
					Integer startIndex = Integer.parseInt(map.get("startIndex").toString());
					Integer perPage = Integer.parseInt(map.get("perPage"));
					if (startIndex == 1 || startIndex == 0) {
						startIndex = 0;
					} else {
						startIndex = (startIndex - 1) * perPage;
					}
					bounds = new PageRowBounds(startIndex, perPage);
				}
				List<Map<String, Object>> resultList = sqlSession.selectList("mqtttask.getListPage", map, bounds);
				Long totalSize = 0L;
				if (map != null && map.size() != 0) {
					totalSize = ((PageRowBounds) bounds).getTotal();
				} else {
					totalSize = Long.valueOf(resultList.size());
				}

				map1.put("list", resultList);
				map1.put("total", totalSize);

			}catch (Exception e){
				e.printStackTrace();
			}
			return map1;
		}

	public Map getMqttTaskByID(Map m) {
		return DbFactory.Open(DbFactory.FORM).selectOne("mqtttask.getMqttTaskById",m);
	}

	public Map findMqttTaskById(String id) {
		return DbFactory.Open(DbFactory.FORM).selectOne("mqtttask.findMqttTaskById",id);
	}
	public void deleteMqttTaskById(Map map) {
		 DbFactory.Open(DbFactory.FORM).delete("mqtttask.deleteMqttTaskById",map);
	}

	public Map<String, Object> saveOrUpdate(SqlSession sqlSession, JSONObject jsonObject) {
		UserModel user = SysContext.getRequestUser();
		Map<String,Object> resultMap  = new HashMap<>();
		resultMap.put("status",true);
		Map<String,Object> map  = new HashMap<>();
		String id="";
		map.put("clientinid",jsonObject.getString("clientinid"));
		map.put("topic",jsonObject.getString("topic"));
		map.put("id", jsonObject.getString("id"));
		Integer count = sqlSession.selectOne("mqtttask.count",map);
		if(count==0) {
			map.put("topic_id",jsonObject.getString("topic_id"));
			map.put("host", jsonObject.getString("host"));
			map.put("username", jsonObject.getString("username"));
			map.put("password", jsonObject.getString("password"));
			map.put("timeout", jsonObject.getString("timeout"));
			map.put("keepalibe", jsonObject.getString("keepalibe"));
			map.put("targetDB", jsonObject.getString("targetDB"));
			map.put("targetTable", jsonObject.getString("targetTable"));
			map.put("sql_script", jsonObject.getString("sql_script"));
			map.put("state", jsonObject.getString("state"));
			if (null == jsonObject.getString("id") || "".equals(jsonObject.getString("id"))) {
				Integer newId = sqlSession.selectOne("mqtttask.getMaxId");
				newId = newId == null ? 1 : newId;
				map.put("id", newId);
				sqlSession.insert("mqtttask.createMqttTask", map);
				id = String.valueOf(map.get("id"));
			} else {

				sqlSession.update("mqtttask.updateMqttTask", map);
				id = jsonObject.getString("id");
			}
			resultMap.put("info",id);
			return resultMap;
		}else{
			resultMap.put("status",false);
			resultMap.put("info","名称已存在");
			return resultMap;
		}

	}

	/**
	 * 启用：1、停用：0
	 * */
	public Map<String, Object> updateState(SqlSession sqlSession, Map map) {
		Map<String,Object> resultMap  = new HashMap<>();
		resultMap.put("status",true);
		try {
			sqlSession.update("mqtttask.updateState", map);
			Map info = this.findMqttTaskById(map.get("id").toString());
			String clientinid=info.get("clientinid").toString();
			Boolean isConn=false;
			if(clinetList.size()>0) {
				for (int i = 0; i < clinetList.size(); i++) {
					if (null != clinetList.get(i).get(clientinid) && !"".equalsIgnoreCase(clinetList.get(i).get(clientinid).toString())) {
						isConn = true;
						if ("0".equalsIgnoreCase(map.get("state").toString())) {
							MqttClient client = clinetList.get(i).get(clientinid);
							try {
								if(null!=client) {
									client.disconnect();
									client.close();
									clinetList.remove(i);
									break;
								}
							} catch (MqttException e) {
								client.close();
								clinetList.remove(i);
								e.printStackTrace();
								break;
							}
						} else {
							MqttClient client = clinetList.get(i).get(clientinid);
							try {
								client.disconnect();
								client.close();
								clinetList.remove(i);
								MqttPushClient mqttPushClient = new MqttPushClient(clinetList,MqttTaskService.this);
								clinetList = mqttPushClient.start(info);
								mqttPushClient.subscribe(info.get("topic").toString());
								break;
							}catch (Exception e){
								clinetList.remove(i);
								MqttPushClient mqttPushClient = new MqttPushClient(clinetList,MqttTaskService.this);
								clinetList = mqttPushClient.start(info);
								mqttPushClient.subscribe(info.get("topic").toString());
								break;
							}

						}
					}
				}
				if(!isConn){
					if ("1".equalsIgnoreCase(map.get("state").toString())) {
						MqttPushClient mqttPushClient = new MqttPushClient(clinetList,MqttTaskService.this);
						clinetList = mqttPushClient.start(info);
						mqttPushClient.subscribe(info.get("topic").toString());
					}
				}
			}else{
				if ("1".equalsIgnoreCase(map.get("state").toString())) {
					MqttPushClient mqttPushClient = new MqttPushClient(clinetList,MqttTaskService.this);
					clinetList = mqttPushClient.start(info);
					mqttPushClient.subscribe(info.get("topic").toString());
				}
			}
			log.info(clinetList);
			resultMap.put("info","修改成功");
		}catch (Exception e) {
			resultMap.put("status",false);
			resultMap.put("info","修改失败");
			e.printStackTrace();
		}

		return  resultMap;
	}

	public void inserSql(String dbname,String insertSql){
		try {
			DbFactory.Open(dbname).update("mqtttask.insertSql", insertSql);
			System.out.println("插入成功");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
