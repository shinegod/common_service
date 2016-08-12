package com.fx.common.dao;

import com.fx.common.model.Dictionary;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IDictionaryDao extends IValueObjectDao<Dictionary> {
    List<Dictionary> findByParentId(String parentId);

    public List<Dictionary> selectValueByDatatype(String dataType);

    public Dictionary findByCode(String dataCode);

    public List<Dictionary> queryByParentCode(String dataCode);

    public List<Dictionary> selectByparentIdAndStatus(Map map);

    public List<Dictionary> selectByParentCodeAndStatusNoZero(String dataCode);

    public Integer selectCountCode(String dataCode);

}