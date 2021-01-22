package root.report.menu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultElement;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.configure.MybatisCacheConfiguration;
import root.report.db.DbFactory;
import root.report.service.DictInsertTask;
import root.report.util.ThreadPoolExecutorUtil;
import root.report.util.XmlUtil;

import javax.websocket.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

@Service
public class MenuService {

    private static Logger log = Logger.getLogger(MenuService.class);


    public Map<String,Object> getAllPage(Map<String,String> map) {
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
            List<Map<String, Object>> resultList = sqlSession.selectList("menu.getAllPage", map, bounds);
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
    /**
     * 功能描述: 根据JSON数据解析 对应数据，生成func_dict记录
     */
    public String saveOrUpdateMenu(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        String id="";

        map.put("func_name",jsonObject.getString("func_name"));
        map.put("func_type",jsonObject.getString("func_type"));
        map.put("func_pid",jsonObject.getString("func_pid"));
        map.put("valid",jsonObject.getString("valid"));
        map.put("func_desc",jsonObject.getString("func_desc"));
        map.put("func_icon",jsonObject.getString("func_icon"));
        map.put("func_url",jsonObject.getString("func_url"));
        map.put("order",jsonObject.getString("order"));
        map.put("target",jsonObject.getString("target"));
        if(null==jsonObject.getString("func_id")|| "".equals(jsonObject.getString("func_id"))){
            Integer newId= sqlSession.selectOne("menu.getMaxId");
            newId = newId==null?1:newId;
            map.put("func_id",newId);
            sqlSession.insert("menu.createMenu",map);
            id=String.valueOf(map.get("id"));
        }else{
            map.put("func_id",jsonObject.getString("func_id"));
            sqlSession.update("menu.updateMenu",map);
            id=jsonObject.getString("func_id");
        }

        return id;
    }


    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteMenuById(SqlSession sqlSession,int func_id){
            Map<String,Object> map=new HashMap();
            map.put("func_pid",func_id);
            sqlSession.delete("menu.deleteMenuByID",map);
    }

    public Map getMenuByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("menu.getMenuById",m);
    }


    public List<Map> getAllList(String parmap) {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        List<Map> list= sqlSession.selectList("menu.getMenuByPId",parmap);
        for(int i=0;i<list.size();i++){
            Map map=new HashMap();
            List<Map> chilerenlist= sqlSession.selectList("menu.getMenuByPId",list.get(i).get("func_id").toString());
            if(null!=chilerenlist && chilerenlist.size()>0){
                list.get(i).put("children",childrenListItem(sqlSession,chilerenlist));
            }
        }
        return list;
    }

    public List<Map> childrenListItem (SqlSession sqlSession,List<Map> list) {
        if (list.isEmpty()) {
            return list;
        }
        for(int i=0;i<list.size();i++){
            Map map=new HashMap();
            List<Map> chilerenlist= sqlSession.selectList("menu.getMenuByPId",list.get(i).get("func_id").toString());
            if(null!=chilerenlist && chilerenlist.size()>0){
                list.get(i).put("children",childrenListItem(sqlSession,chilerenlist));
            }
        }
        return list;
    }
}
