package com.fx.trademsg.dao.impl;

import com.fx.trademsg.dao.IEconomyDataDao;
import com.fx.trademsg.model.EconomyData;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class EconomyDataDaoImpl extends BaseDao<EconomyData> implements IEconomyDataDao {

    public EconomyDataDaoImpl() {
        super(IEconomyDataDao.class.getName());
    }
}