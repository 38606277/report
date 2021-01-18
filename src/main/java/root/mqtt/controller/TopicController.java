package root.mqtt.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import root.mqtt.configure.MqttSendMessage;
import root.mqtt.service.TopicService;
import root.report.common.RO;
import root.report.db.DbFactory;


/**
 * 测试网关 1319100003
 * @author cannon
 */
@RestController
@RequestMapping(value = "/Topic")
public class TopicController extends RO {
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private MqttSendMessage mqttSendMessage;
	
	/**
	 * 添加网关
	 * @return
	 */
    @RequestMapping(value="/addTopicByGateway",produces = "text/plain;charset=UTF-8")
    public String addTopicByGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.addTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("添加成功", "");
    }
    
    /**
	 * 删除网关
	 * @return
	 */
    @RequestMapping(value="/deleteTopicByGateway",produces = "text/plain;charset=UTF-8")
    public String deleteTopicByGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.removeTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("删除成功", "");
    }
    
//    /**
//	 * 修改网关配置
//	 * @return
//	 */
//    @RequestMapping(value="/updateGatewayConfig",produces = "text/plain;charset=UTF-8")
//    public String updateGatewayConfig(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
//    	String gatewayNumber = pJson.getString("gatewayNumber");
//    	String topic = "/001/"+ gatewayNumber + "/set";
//    	pJson.remove("gatewayNumber");
//    	mqttSendMessage.sendMessage(pJson.toJSONString(), topic);
//        return SuccessMsg("修改成功", "");
//    }


	@RequestMapping(value = "/getMqttTaskList", produces = "text/plain;charset=UTF-8")
	public String getModelList(@RequestBody JSONObject pJson)  {
		try {
			Map<String,String> map=new HashMap();
			map.put("startIndex",pJson.getString("startIndex"));
			map.put("perPage",pJson.getString("perPage"));
			map.put("model_name",pJson.get("model_name")==null?"":pJson.get("model_name").toString());
			map.put("db_source",pJson.get("db_source")==null?"":pJson.get("db_source").toString());
			map.put("db_type",pJson.get("db_type")==null?"":pJson.get("db_type").toString());
			Map<String,Object> resultMap = topicService.getListPage(map);
			return SuccessMsg("", resultMap);
		} catch (Exception ex){
			return ExceptionMsg(ex.getMessage());
		}
	}

	@RequestMapping(value = "/getMqttTaskById", produces = "text/plain;charset=UTF-8")
	public String getModelById(@RequestBody JSONObject pJson)  {
		try{
			Map resultObject =topicService.getMqttTaskByID(pJson);
			return SuccessMsg("",resultObject);
		}catch (Exception ex){
			ex.printStackTrace();
			return ExceptionMsg(ex.getMessage());
		}

	}

	@RequestMapping(value = "/createMqttTask", produces = "text/plain;charset=UTF-8")
	public String getTablesBySource(@RequestBody JSONObject pJson) throws SQLException {
		SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
		try{
			sqlSession.getConnection().setAutoCommit(false);
			Map<String,Object> map  = this.topicService.saveOrUpdate(sqlSession,pJson);
			if(Boolean.valueOf(map.get("status").toString())){
				sqlSession.getConnection().commit();
				return SuccessMsg("保存成功",map.get("info"));
			}else{
				return ErrorMsg("保存失败",map.get("info").toString());
			}

		}catch (Exception ex){
			sqlSession.getConnection().rollback();
			ex.printStackTrace();
			return ExceptionMsg(ex.getMessage());
		}
	}
}
