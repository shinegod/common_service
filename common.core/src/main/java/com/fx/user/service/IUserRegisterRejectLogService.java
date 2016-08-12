package com.fx.user.service;

import com.fx.user.model.UserRegisterRejectLog;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IUserRegisterRejectLogService extends IValueObjectService<UserRegisterRejectLog> {
    public List<UserRegisterRejectLog> getByUid(Integer uid);

}