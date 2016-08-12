package com.fx.crm.sys.log.service.impl;

import com.fx.crm.sys.log.dao.IOperationReferenceDao;
import com.fx.crm.sys.log.model.OperationReference;
import com.fx.crm.sys.log.service.IOperationReferenceService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationReferenceServiceImpl extends BaseVOService<OperationReference> implements IOperationReferenceService {
    @Autowired
    private IOperationReferenceDao operationReferenceDao;
}