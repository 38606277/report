package root.report.quartz.Service;

import org.apache.ibatis.session.SqlSession;
import root.report.quartz.entity.EtlJob;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: pccw
 * @Date: 2018/11/27 14:56
 * @Description:
 */

public interface IEtlJobService {

    /**
     * 获取任务数量
     * @param
     * @return
     */
    public int getJobCount(SqlSession sqlSession);

    /**
     * 查询定时任务列表
     * @param map
     * @return
     */
    List<EtlJob> querySysJobList(SqlSession sqlSession,HashMap<String, String> map);

    /**
     * 新增定时任务
     * @param record
     * @return
     */
    int insertSelective(SqlSession sqlSession,EtlJob record);

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    int deleteByPrimaryKey(SqlSession sqlSession,Integer id);

    /**
     * 根据主键查询定时任务详情
     * @param id
     * @return
     */
    EtlJob selectByPrimaryKey(SqlSession sqlSession,Integer id);

    /**
     * 根据bean查询定时任务详情
     * @param
     * @return
     */
    EtlJob selectByBean(SqlSession sqlSession,EtlJob bean);

    /**
     * 更新定时任务详情
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(SqlSession sqlSession,EtlJob bean);
}
