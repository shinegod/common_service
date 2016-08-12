package com.fx.trademsg.dao.impl;

import com.fx.trademsg.dao.ITraderClendarDao;
import com.fx.trademsg.model.TraderClendar;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class TraderClendarDaoImpl extends BaseDao<TraderClendar> implements ITraderClendarDao {

    public TraderClendarDaoImpl() {
        super(ITraderClendarDao.class.getName());
    }
}