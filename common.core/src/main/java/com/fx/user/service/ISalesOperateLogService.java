package com.fx.user.service;

import com.fx.admin.model.Admin;
import com.fx.user.model.SalesOperateLog;
import com.fx.user.model.UserRegister;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ISalesOperateLogService extends IValueObjectService<SalesOperateLog> {
    /**
     * 插入销售日志
     * @param admin
     * @param userRegister
     * @param pic
     * @param fromModel
     * @param operating
     */
    public void insertLog(Admin admin, UserRegister userRegister, UserRegister pic, String pic_re, String fromModel, String operating,String operating_key,String type);

    public List<SalesOperateLog> findByUserId(int userId);
}