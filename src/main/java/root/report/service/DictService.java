package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.report.db.DbFactory;
import root.report.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictService {

    private static Logger log = Logger.getLogger(DictService.class);


    public List<Map<String,String>> getAllDictName(){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            resultList=sqlSession.selectList("dict.getAllDictName");
            return resultList;

        }catch (Exception ex){

            throw  ex;
        }
    }



}
