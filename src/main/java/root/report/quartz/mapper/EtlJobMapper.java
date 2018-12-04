package root.report.quartz.mapper;

import org.springframework.stereotype.Repository;
import root.report.quartz.entity.EtlJob;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: pccw
 * @Date: 2018/11/27 14:52
 * @Description:
 */
@Repository
public interface EtlJobMapper {

    /**
     * 获取任务数量
     * @param
     * @return
     */
    int getJobCount();

    /**
     * 查询定时任务列表
     * @param map
     * @return
     */
    List<EtlJob> querySysJobList(HashMap<String, String> map);

    /**
     * 新增定时任务
     * @param pccwJob
     * @return
     */
    int insertSelective(EtlJob etlJob);

    /**
     * 删除定时任务
	 * @param id
	 * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键查询定时任务详情
     * @param id
     * @return
     */
    EtlJob selectByPrimaryKey(Integer id);

    /**
     * 根据bean查询定时任务详情
     * @param
     * @return
     */
    EtlJob selectByBean(EtlJob bean);

    /**
     * 更新定时任务详情
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(EtlJob bean);

}
