package root.report.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JsonUtil {


	public static SerializerFeature[] features = { SerializerFeature.WriteNullNumberAsZero,
			SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue,
			SerializerFeature.PrettyFormat, SerializerFeature.UseISO8601DateFormat,
			SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullListAsEmpty };
	
	public static String resultSetToJson(ResultSet rs) throws SQLException  
	{  
	   // json数组  
	   JSONArray array = new JSONArray();  
	    
	   // 获取列数  
	   ResultSetMetaData metaData = rs.getMetaData();  
	   int columnCount = metaData.getColumnCount();  
	    
	   // 遍历ResultSet中的每条数据  
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	         
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
	        }   
	        array.add(jsonObj);   
	    }  
	    
	   return array.toString();  
	}

	// 把查询出来的Map的key 变为大写的
	private static Map genChildParamMap(Object object) {
		if (object instanceof Map) {
			Map<String, Object> paramMap = (Map<String, Object>) JSONObject.toJSON(object);
			Map<String, Object> newMap = Maps.newHashMapWithExpectedSize(paramMap.size());
			paramMap.entrySet().stream().filter(me -> me.getValue() != null).forEach(me ->
					newMap.put(me.getKey().toUpperCase(), genChildParamMap(me.getValue())));
			return newMap;
		}else {
			return null;
		}
	}

	/**
	 *
	 * 功能描述:
	 *		把传递进来的List<Map> 结果集中的每一条Map的key变为大写的
	 * @param:  List<Map<String,Object>>
	 * @return:	List<Map<String,Object>>
	 * @date: 2018/11/13 14:12
	 */
	 public static List<Map> transfListMap(List<Map> sourceList){
		 List<Map> resultList = new ArrayList<>(Lists.newArrayListWithExpectedSize(sourceList.size()));
		 sourceList.forEach( tranObj -> resultList.add(genChildParamMap(tranObj)));
		 return resultList;
	 }
}
