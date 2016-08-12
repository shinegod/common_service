package com.fx.common.dao.impl;

import com.fx.common.dao.IDistrictDao;
import com.fx.common.model.District;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictDaoImpl extends BaseDao<District> implements IDistrictDao {

    public DistrictDaoImpl() {
        super(IDistrictDao.class.getName());
    }
}