package root.report.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuestionService {

    private static Logger log = Logger.getLogger(QuestionService.class);

    /**
     * 功能描述: 根据JSON数据解析 对应数据，生成func_dict记录
     */
    public String createQuestion(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("ai_question",jsonObject.getString("ai_question"));
        map.put("fileDataBlob",jsonObject.get("fileDataBlob"));
        sqlSession.insert("question.createQuestion",map);
        return String.valueOf(map.get("id"));
    }


    // 功能描述 : 修改 问题 表的信息
    public void updateQuestion(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map=new HashMap();
        map.put("ai_question",jsonObject.getString("ai_question"));
        map.put("ai_question_id",jsonObject.getString("ai_question_id"));
        map.put("fileDataBlob",jsonObject.get("fileDataBlob"));
        sqlSession.insert("question.updateQuestion",map);
    }
    // 功能描述: 根据ai_question_id的信息
    public void deleteQuestion(SqlSession sqlSession,int ai_question_id){
        Map<String,Object> map=new HashMap();
        map.put("ai_question_id",ai_question_id);
        sqlSession.delete("question.deleteQuestion",map);
    }

    // 修改 func_dict_value 表当中的记录
    public void createAnswer(SqlSession sqlSession,JSONObject jsonObject){
        // 只更新  value_name 即可
        Map<String,Object> map = new HashMap<>();
        map.put("question_id",jsonObject.getIntValue("question_id"));
        map.put("current",jsonObject.getString("current"));
        map.put("answer",jsonObject.getString("answer"));
        map.put("creat_by",jsonObject.getString("creat_by"));
        map.put("fileDataBlob",jsonObject.get("fileDataBlob"));
        sqlSession.insert("question.createAnswer",map);
    }

    // 功能描述 : 修改 回答 表的信息
    public  void updateAnswer(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("answer_id",jsonObject.getIntValue("answer_id"));
        map.put("current",jsonObject.getIntValue("current"));
        map.put("answer",jsonObject.getString("answer"));
        map.put("creat_by",jsonObject.getString("creat_by"));
        map.put("fileDataBlob",jsonObject.get("fileDataBlob"));
        sqlSession.update("question.updateAnswer",map);
    }
    // 功能描述: 根据 answer_id 删除回答
    public void deleteAnswer(SqlSession sqlSession,int answer_id){
        sqlSession.delete("question.deleteAnswer",answer_id);
    }

    // 功能描述: 根据 answer_id 全量删除
    public void deleteAnswerByqID(SqlSession sqlSession,int question_id){
        sqlSession.delete("question.deleteAnswerByqID",question_id);
    }

}
