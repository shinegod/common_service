package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IPipSettingDao;
import com.fx.bonus.model.PipSetting;

@Repository
public class PipSettingDaoImpl extends BaseDao<PipSetting> implements IPipSettingDao {

    public PipSettingDaoImpl() {
        super(IPipSettingDao.class.getName());
    }
}