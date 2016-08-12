package com.fx.user.service;

import com.fx.user.model.UserContacts;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.List;

public interface IUserContactsService extends IValueObjectService<UserContacts> {
    public List<UserContacts> findByUserId(Integer id);

    public PageIterator<UserContacts> pageQueryByCondition(Pagination pagination);

    public UserContacts findMainContactByUserId(Integer uid);
}