package com.fx.ip.service.impl;

import com.fx.ip.dao.IIPWhiteListDao;
import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IIPWhiteListService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Michael on 8/15/2016.
 */
@Service
public class IPWhiteListServiceImpl extends BaseVOService<IPWhiteList> implements IIPWhiteListService {

    @Autowired
    private IIPWhiteListDao ipWhiteListDao;

    @Override
    public IPWhiteList findBySysTypeAndUserId(Map<String, Object> params) {
        return ipWhiteListDao.findBySysTypeAndUserId(params);
    }
}
