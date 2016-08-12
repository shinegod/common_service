package com.fx.user.service.impl;

import com.fx.user.dao.IUserContactsDao;
import com.fx.user.model.UserContacts;
import com.fx.user.service.IUserContactsService;
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
public class UserContactsServiceImpl extends BaseVOService<UserContacts> implements IUserContactsService {

    @Autowired
    private IUserContactsDao userContactsDao;

    @Override
    public List<UserContacts> findByUserId(Integer id) {
        return userContactsDao.findByUserId(id);
    }

    @Override
    public PageIterator<UserContacts> pageQueryByCondition(Pagination pagination) {
        Map<String, Object> params = new HashMap<String, Object>();
        String username = pagination.getSearch();
        if (StringUtils.isNotBlank(username)) {
            username = "%" + username + "%";
            params.put("cnName", username);
        }
        int totalCount = userContactsDao.queryCountByCondition(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserContacts> contactsList = userContactsDao.queryByCondition(params);
        PageIterator<UserContacts> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(contactsList);
        return page;
    }

    @Override
    public UserContacts findMainContactByUserId(Integer uid) {
        return userContactsDao.findMainContactByUserId(uid);
    }
}
