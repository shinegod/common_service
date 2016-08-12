package com.fx.bonus.service.impl;

import com.fx.bonus.dao.IPipSettingDao;
import com.fx.bonus.model.PipSetting;
import com.fx.bonus.service.IPipSettingService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PipSettingServiceImpl extends BaseVOService<PipSetting> implements IPipSettingService {
    @Autowired
    private IPipSettingDao pipSettingDao;
}