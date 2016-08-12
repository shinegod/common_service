package com.fx.user.service.impl;

import com.fx.user.dao.IReturnedMoneyDao;
import com.fx.user.model.ReturnedMoney;
import com.fx.user.service.IReturnedMoneyService;
import com.fx.util.Pagination;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnedMoneyServiceImpl extends BaseVOService<ReturnedMoney> implements IReturnedMoneyService {
    @Autowired
    private IReturnedMoneyDao returnedMoneyDao;

    @Override
    public List<ReturnedMoney> findByUserId(Integer userId) {
        return returnedMoneyDao.findByUserId(userId);
    }

    @Override
    public PageIterator<ReturnedMoney> pageQueryByCondition(Pagination pagination) {
        Map<String, Object> params = new HashMap<String, Object>();
        String username = pagination.getSearch();

        if (StringUtils.isNotBlank(username)) {
            username = "%" + username + "%";
            params.put("cnName", username);
        }

        int totalCount = returnedMoneyDao.queryCountByCondition(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<ReturnedMoney> returnedMoneyList = returnedMoneyDao.queryByCondition(params);
        PageIterator<ReturnedMoney> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(returnedMoneyList);
        return page;
    }
}