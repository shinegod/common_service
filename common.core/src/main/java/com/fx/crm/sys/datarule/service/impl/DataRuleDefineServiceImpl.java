package com.fx.crm.sys.datarule.service.impl;

import java.util.List;

import com.fx.crm.sys.datarule.dao.IDataRuleDefineDao;
import com.fx.crm.sys.datarule.model.DataRuleDefine;
import com.fx.crm.sys.datarule.service.IDataRuleDefineService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRuleDefineServiceImpl extends BaseVOService<DataRuleDefine> implements IDataRuleDefineService {
    @Autowired
    private IDataRuleDefineDao dataRuleDefineDao;

	@Override
	public List<DataRuleDefine> findByMenuIdAndStatus(String menuid) {
		return dataRuleDefineDao.findList("selectDataRuleDefinesByMenuIdAndStatus", menuid);
	}
}