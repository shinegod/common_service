package com.fx.common.service;

import com.fx.common.model.Dictionary;
import mybatis.framework.core.service.IValueObjectService;
import java.util.List;
import java.util.Map;


public interface IDictionaryService extends IValueObjectService<Dictionary> {

    //public List<Dictionary> selectValueByDatatype(String dataType);

    List<Dictionary> findByParentId(String parentId);

    public Dictionary findByCode(String dataCode);

    public List<Dictionary> queryByParentId(String parentId);

    public List<Dictionary> queryByParentCode(String dataCode);

    public List<Dictionary> selectByparentIdAndStatus(Map map);

    public List<Dictionary> selectByParentCodeAndStatusNoZero(String dataCode);

    public Integer selectCountCode(String dataCode);

    public List<Dictionary> selectByparentIdAndStatusBalance(Map map,double balance);
}
