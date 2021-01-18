package root.mqtt.service;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import root.form.user.UserModel;
import root.mqtt.configure.MyMqttPahoMessageDrivenChannelAdapter;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TopicService {
	
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

	public Map<String, Object> saveOrUpdate(SqlSession sqlSession, JSONObject jsonObject) {
		UserModel user = SysContext.getRequestUser();
		Map<String,Object> resultMap  = new HashMap<>();
		resultMap.put("status",true);
		Map<String,Object> map  = new HashMap<>();
		String id="";
		map.put("clientinid",jsonObject.getString("clientinid"));
		map.put("topic",jsonObject.getString("topic"));
		Integer count = sqlSession.selectOne("mqtttask.count",map);
		if(count==0) {
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
}
