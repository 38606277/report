package root.report.quartz.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.report.db.DbFactory;
import root.report.quartz.Service.IEtlJobService;
import root.report.quartz.entity.EtlJob;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: pccw
 * @Date: 2018/11/27 14:59
 * @Description:
 *  quartz service 实现类
 *
 */
@Service
public class EtlServiceImpl  implements IEtlJobService {

    @Override
    public int getJobCount(SqlSession sqlSession) {
        return sqlSession.selectOne("etlJobMapper.getJobCount");
    }

    @Override
    public List<EtlJob> querySysJobList(SqlSession sqlSession,HashMap<String, String> map) {
        return sqlSession.selectList("etlJobMapper.querySysJobList",map);
    }

    @Override
    public int insertSelective(SqlSession sqlSession,EtlJob record) {
        return sqlSession.insert("etlJobMapper.insertSelective",record);
    }

    @Override
    public int deleteByPrimaryKey(SqlSession sqlSession,Integer id) {
        return sqlSession.delete("etlJobMapper.deleteByPrimaryKey",id);
    }

    @Override
    public EtlJob selectByPrimaryKey(SqlSession sqlSession,Integer id) {
        return sqlSession.selectOne("etlJobMapper.selectByPrimaryKey",id);
    }

    @Override
    public EtlJob selectByBean(SqlSession sqlSession,EtlJob bean) {
        return sqlSession.selectOne("etlJobMapper.selectByBean",bean);
    }

    @Override
    public int updateByPrimaryKeySelective(SqlSession sqlSession,EtlJob bean) {
        return sqlSession.update("etlJobMapper.updateByPrimaryKeySelective",bean);
    }
}
