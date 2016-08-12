package com.fx.crm.sys.datarule.service;

import java.util.List;

import com.fx.crm.sys.datarule.model.DataRuleDefine;

import mybatis.framework.core.service.IValueObjectService;

public interface IDataRuleDefineService extends IValueObjectService<DataRuleDefine> {
	
	/**
	 * 判断该菜单是否需要数据权限
	 * @param menuid
	 * @return
	 */
	public List<DataRuleDefine> findByMenuIdAndStatus(String menuid);
}