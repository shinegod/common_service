package com.fx.ip.service.impl;

import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IIPWhiteListService;
import mybatis.framework.core.support.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 8/15/2016.
 */
@Service
public class IPWhiteListServiceImpl implements IIPWhiteListService {

    @Override
    public IPWhiteList findById(Serializable id) {
        return null;
    }

    @Override
    public List<IPWhiteList> findAll() {
        return null;
    }

    @Override
    public int doDeleteById(Serializable id) {
        return 0;
    }

    @Override
    public int doInsert(IPWhiteList vo) {
        return 0;
    }

    @Override
    public int doUpdateById(IPWhiteList vo) {
        return 0;
    }

    @Override
    public Page pagedQuery(int pageNo, int pageSize, Map<String, Object> parameter) {
        return null;
    }

    @Override
    public int doInsertList(List<IPWhiteList> list) {
        return 0;
    }
}
