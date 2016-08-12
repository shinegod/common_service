package com.fx.user.service.impl;

import com.fx.admin.model.Admin;
import com.fx.common.dao.IDictionaryDao;
import com.fx.common.model.Dictionary;
import com.fx.user.dao.ISalesOperateLogDao;
import com.fx.user.model.SalesOperateLog;
import com.fx.user.model.UserRegister;
import com.fx.user.service.ISalesOperateLogService;
import com.fx.util.Config;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesOperateLogServiceImpl extends BaseVOService<SalesOperateLog> implements ISalesOperateLogService {
    @Autowired
    private ISalesOperateLogDao salesOperateLogDao;

    @Autowired
    private IDictionaryDao dictionaryDao;

    @Override
    public void insertLog(Admin admin, UserRegister userRegister, UserRegister pic, String pic_re, String fromModel, String operating,String operating_key,String type) {
        SalesOperateLog salesOperateLog = new SalesOperateLog();
        salesOperateLog.setCreateTime(new Date());
        salesOperateLog.setOperating(Config.getConfig(operating_key,operating));
        salesOperateLog.setOperationTime(new Date());
        salesOperateLog.setOperator(admin.getName());
        salesOperateLog.setOperatorId(admin.getUserId());
        if(pic != null ) {
            salesOperateLog.setPic(pic.getEnName());
            salesOperateLog.setPicId(pic.getId());
        } else {
            salesOperateLog.setPic(pic_re);
        }
        salesOperateLog.setFromModel(fromModel);
        salesOperateLog.setUid(userRegister.getId());
        salesOperateLog.setLifecycleType(type);
        if(pic != null) {
            salesOperateLog.setDescription(salesOperateLog.getOperator() + salesOperateLog.getOperating() + salesOperateLog.getPicId()+ " " + salesOperateLog.getPic());
        }
        else {
            salesOperateLog.setDescription(salesOperateLog.getOperator() + salesOperateLog.getOperating() + salesOperateLog.getPic() );
        }


        doInsert(salesOperateLog);
    }

    @Override
    public List<SalesOperateLog> findByUserId(int userId) {
        return salesOperateLogDao.findList("findByUserId",userId);
    }
}