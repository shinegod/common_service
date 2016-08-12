package com.fx.configuration.service.impl;

import com.fx.configuration.dao.IEaCommissionConfDao;
import com.fx.configuration.model.EaCommissionConf;
import com.fx.configuration.service.IEaCommissionConfService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaCommissionConfServiceImpl extends BaseVOService<EaCommissionConf> implements IEaCommissionConfService {
    @Autowired
    private IEaCommissionConfDao eaCommissionConfDao;
}