package com.fx.util;

import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.crm.sys.log.service.IOperationLoggerService;

/**
 * Created by bei2love@gmail.com on 15/9/6.
 */
public class OperationLoggerThread extends Thread{

    private OperationLogger log;
    private Object handler;
    private Exception ex;

    private IOperationLoggerService loggerService = SpringUtils.getBean(IOperationLoggerService.class);

    public OperationLoggerThread(OperationLogger log, Object handler, Exception ex) {
        super(OperationLoggerThread.class.getSimpleName());
        this.log = log;
        this.handler = handler;
        this.ex = ex;
    }

    @Override
    public void run() {
        // 保存日志信息
        //根据Config.properties中配置的系统标识,将访问日志存入不同的日志表
        if (Constants.SYSTEM_CRM.equals(Config.getSystemId())) {
            loggerService.doInsert(log);
        } else if (Constants.SYSTEM_TRADER.equals(Config.getSystemId())) {
            loggerService.doInsertTraderLog(log);
        }
    }
}
