package root.report.electronTable.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.form.user.UserModel;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.io.*;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectronTableService {

    private static Logger log = Logger.getLogger(ElectronTableService.class);

//    @Autowired
//    private DataStandardTableService modelTableService;

    public Map<String,Object> getListPage(Map<String,String> map) {
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
            List<Map<String, Object>> resultList = sqlSession.selectList("electronTable.getListPage", map, bounds);
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
    public Map saveOrUpdateElectronTable(SqlSession sqlSession,JSONObject jsonObject){
        UserModel user = SysContext.getRequestUser();
        //Session session = (Session) DbFactory.Open(DbFactory.FORM);
        Map<String,Object> resultMap  = new HashMap<>();
        resultMap.put("status",true);
        Map<String,Object> map  = new HashMap<>();
        String id="";
        map.put("name",jsonObject.getString("name").trim());
        map.put("id", jsonObject.getString("id").trim());
        Integer count = sqlSession.selectOne("electronTable.count",map);
        if(count==0) {
            byte[] blobBytes = objectToBytesArray(jsonObject.get("obj"));
            map.put("obj", blobBytes);
            map.put("status", jsonObject.getString("status"));
            map.put("create_by", user.getId());
            if (null == jsonObject.getString("id") || "".equals(jsonObject.getString("id"))) {
                Integer newId = sqlSession.selectOne("electronTable.getMaxId");
                newId = newId == null ? 1 : newId;
                map.put("id", newId);
                sqlSession.insert("electronTable.createElectronTable", map);
                id = String.valueOf(map.get("id"));
            } else {
                map.put("update_by", user.getId());
                sqlSession.update("electronTable.updateElectronTable", map);
                id = jsonObject.getString("id");
            }
            resultMap.put("info",id);
            return resultMap;
        }else{
            resultMap.put("status",false);
            resultMap.put("info","模型名称已存在");
            return resultMap;
        }
    }


    public Map<String,Object> getObjById(Map m) {
        Map<String,Object> resp= DbFactory.Open(DbFactory.FORM).selectOne("electronTable.getObjById",m);
        if(null!=resp){
            byte[] byte1 = (byte[])resp.get("obj");
            resp.put("obj", toObject(byte1));
        }
        return resp;
    }

    /**
     * 将byte数组转化为Object对象
     * @return
     */
    private Object toObject(byte[] bytes){
        Object object = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);// 创建ByteArrayInputStream对象
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);// 创建ObjectInputStream对象
            object = objectInputStream.readObject();// 从objectInputStream流中读取一个对象
            byteArrayInputStream.close();// 关闭输入流
            objectInputStream.close();// 关闭输入流
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;// 返回对象
    }

    public byte[] objectToBytesArray(Object o){//Object转换为byte数组
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.flush();
            bytes = bos.toByteArray();
            out.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] blobToByte(Blob blob) {//Blob转化为Byte数组类型
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }


    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteElectronTableById(SqlSession sqlSession,String id){
            Map<String,Object> map=new HashMap();
            map.put("id",id);
            sqlSession.delete("electronTable.deleteObjByID",map);
    }


}
