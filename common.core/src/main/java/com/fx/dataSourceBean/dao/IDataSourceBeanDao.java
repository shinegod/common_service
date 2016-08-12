package com.fx.dataSourceBean.dao;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.dataSourceBean.model.DataSourceBean;

public interface IDataSourceBeanDao extends IValueObjectDao<DataSourceBean> {

    public List<DataSourceBean> findAll();

    public DataSourceBean findDataSourceBeanById(int dataSourceBeanId);

    public List<DataSourceBean> selectByMt4DatasourceTypeAndSourceWebSideType(Map map);
    
}