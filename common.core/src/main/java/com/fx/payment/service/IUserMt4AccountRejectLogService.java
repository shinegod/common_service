package com.fx.payment.service;

import com.fx.payment.model.UserMt4AccountRejectLog;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IUserMt4AccountRejectLogService extends IValueObjectService<UserMt4AccountRejectLog> {
    public List<UserMt4AccountRejectLog> getByMid(int mid);
}