package com.fx.common.dao.impl;

import com.fx.common.dao.IProvinceDao;
import com.fx.common.model.Province;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProvinceDaoImpl extends BaseDao<Province> implements IProvinceDao {

    public ProvinceDaoImpl() {
        super(IProvinceDao.class.getName());
    }

    @Override
    public List<Province> findByCountryId(int countryId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("countryId", countryId);
        return findList("findListByCountryId", params);
    }
}