package com.fx.dataSourceBean.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.dataSourceBean.dao.IDataSourceBeanDao;
import com.fx.dataSourceBean.model.DataSourceBean;

@Repository
public class DataSourceBeanDaoImpl extends BaseDao<DataSourceBean> implements IDataSourceBeanDao {

    public DataSourceBeanDaoImpl() {
        super(IDataSourceBeanDao.class.getName());
    }

    @Override
    public List<DataSourceBean> findAll() {
        return findList("selectAll","");
    }

    
    @Override
    public DataSourceBean findDataSourceBeanById(int dataSourceBeanId) {
        return (DataSourceBean)findOne("selectByPrimaryKey",dataSourceBeanId);
    }

    @Override
    public List<DataSourceBean> selectByMt4DatasourceTypeAndSourceWebSideType(Map map) {
        return findList("selectByMt4DatasourceTypeAndSourceWebSideType",map);
    }

}