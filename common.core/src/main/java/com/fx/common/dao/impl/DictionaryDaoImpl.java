package com.fx.common.dao.impl;

import com.fx.common.dao.IDictionaryDao;
import com.fx.common.model.Dictionary;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DictionaryDaoImpl extends BaseDao<Dictionary> implements IDictionaryDao {

    public DictionaryDaoImpl() {
        super(IDictionaryDao.class.getName());
    }

    @Override
    public List<Dictionary> findByParentId(String parentId) {
        return findList("selectByParentId", parentId);
    }

    @Override
    public List<Dictionary> selectValueByDatatype(String dataType) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataType",dataType);
        return findList("selectValueByDatatype",param);
    }

    @Override
    public Dictionary findByCode(String dataCode) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataCode",dataCode);
        return (Dictionary)findOne("selectByCode",param);
    }

    @Override
    public List<Dictionary> queryByParentCode(String dataCode) {
        return findList("selectByParentCode",dataCode);
    }

    @Override
    public List<Dictionary> selectByparentIdAndStatus(Map map) {
        return findList("selectByparentIdAndStatus",map);
    }

    @Override
    public List<Dictionary> selectByParentCodeAndStatusNoZero(String dataCode) {
        return findList("selectByParentCodeAndStatusNoZero",dataCode);
    }

    @Override
    public Integer selectCountCode(String dataCode) {
        return (Integer)findOne("selectCountCode",dataCode);
    }
}