package com.fx.ip.service.impl;

import com.fx.ip.dao.IIPWhiteListDao;
import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IIPWhiteListService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 8/15/2016.
 */
@Service
public class IPWhiteListServiceImpl extends BaseVOService<IPWhiteList> implements IIPWhiteListService {

    @Autowired
    private IIPWhiteListDao ipWhiteListDao;

    @Override
    public IPWhiteList findBySysTypeAndUserIdAndIp(Map<String, Object> params) {
        return ipWhiteListDao.findBySysTypeAndUserIdAndIp(params);
    }

    @Override
    public int delAllByUserId(Map<String, Object> params) {
        return ipWhiteListDao.delAllByUserId(params);
    }

    @Override
    public int delById(Map<String, Object> params) {
        return ipWhiteListDao.delById(params);
    }

    @Override
    public IPWhiteList findBySysAndUserIdAndPermission(Map<String, Object> params) {
        return ipWhiteListDao.findBySysAndUserIdAndPermission(params);
    }

    @Override
    public List<IPWhiteList> queryByUserId(Map<String, Object> params) {
        return ipWhiteListDao.queryByUserId(params);
    }

    @Override
    public IPWhiteList queryTop1ByUserId(Map<String, Object> params) {
        return ipWhiteListDao.queryTop1ByUserId(params);
    }

}
