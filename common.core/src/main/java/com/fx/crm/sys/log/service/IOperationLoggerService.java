package com.fx.crm.sys.log.service;

import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.user.model.UserRegister;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOperationLoggerService extends IValueObjectService<OperationLogger> {
    List<OperationLogger> queryByEntity(Pagination<OperationLogger> pagination);

    /**
     * 重要操作日记记录--修改组，修改上级，修改佣金规则
     */
    public OperationLogger saveImportLog(UserRegister user, Integer mt4Account, String modifybefore, String modifyAtfer, HttpServletRequest request, Integer menuid, String operator_type);


    void doInsertTraderLog(OperationLogger log);
}