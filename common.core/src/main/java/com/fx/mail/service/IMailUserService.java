package com.fx.mail.service;

import java.util.List;

import com.fx.mail.model.MailUser;

import mybatis.framework.core.service.IValueObjectService;

public interface IMailUserService extends IValueObjectService<MailUser> {
	
	/**
	 * 查询出对应服务商的用户
	 * @param mailUserQuery
	 * @return
	 */
	public List<MailUser> queryListByCondition(MailUser mailUserQuery);

	public MailUser findBySidAndisDefault(Integer id);
	
	/**
	 * 所有用户都跟新为非默认
	 * @param integer 
	 */
	public void updateAllNoDefault(Integer integer);
}