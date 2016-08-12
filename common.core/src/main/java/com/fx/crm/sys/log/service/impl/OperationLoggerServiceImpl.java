package com.fx.crm.sys.log.service.impl;

import com.fx.crm.sys.log.dao.IOperationLoggerDao;
import com.fx.crm.sys.log.model.OperationLogger;
import com.fx.crm.sys.log.model.OperationReference;
import com.fx.crm.sys.log.service.IOperationLoggerService;
import com.fx.user.model.UserRegister;
import com.fx.util.*;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class OperationLoggerServiceImpl extends BaseVOService<OperationLogger>
		implements IOperationLoggerService {
	@Autowired
	private IOperationLoggerDao operationLoggerDao;

	@Override
	public List<OperationLogger> queryByEntity(
			Pagination<OperationLogger> pagination) {
		return operationLoggerDao.queryByEntity(pagination);
	}

	@Override
	public OperationLogger saveImportLog(UserRegister user, Integer mt4Account, String Modifybefore, String modifyAtfer, HttpServletRequest request, Integer menuid, String operator_type) {
		OperationLogger log = new OperationLogger();
		log.setId(UUID.randomUUID().toString());
		log.setOperator(String.valueOf(UserUtil.getCurrAdmin().getUserId()));
		log.setSystemId(Config.getSystemId());
		log.setLoginname(UserUtil.getCurrAdmin().getLoginName());
		log.setOperateTime(DateUtil.getCurrentTime());
		log.setOperationip(UserUtil.getCurrAdmin().getLoginIP());
		log.setLogType("OPERATOR");
		log.setUserAgent(request.getHeader("user-agent"));
		log.setOperationUrl(request.getRequestURI());
		log.setReqParams(StringUtils.toRequestParams(request.getParameterMap()));
		log.setReqMethod(request.getMethod());
		log.setOperationDetail(Modifybefore + "；"+modifyAtfer);
		if(menuid != null && menuid != -1) {
			log.setModule(String.valueOf(menuid));
			log.setOperationType(operator_type);
		}else {
			OperationReference or = CacheMgr.getOperationReference().get(request.getRequestURI());
			if (or != null) {
				log.setModule(or.getMenuid());
				log.setOperationType(or.getOperationType());
			}else{
				log.setOperationType(operator_type);
			}
		}

		// 异步保存日志
		new OperationLoggerThread(log, null, null).start();
		return log;
	}

	@Override
	public void doInsertTraderLog(OperationLogger log) {
		operationLoggerDao.doInsertTraderLog(log);
	}

}