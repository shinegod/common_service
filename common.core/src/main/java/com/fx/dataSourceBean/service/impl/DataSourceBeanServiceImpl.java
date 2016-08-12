package com.fx.dataSourceBean.service.impl;

import com.fx.dataSourceBean.dao.IDataSourceBeanDao;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSourceBeanServiceImpl extends BaseVOService<DataSourceBean> implements IDataSourceBeanService {
    @Autowired
    private IDataSourceBeanDao dataSourceBeanDao;

    @Override
    public List<DataSourceBean> findAll() {
        return dataSourceBeanDao.findAll();
    }

    @Override
    public DataSourceBean findDataSourceBeanById(int dataSourceBeanId) {
        return dataSourceBeanDao.findDataSourceBeanById(dataSourceBeanId);
    }

	@Override
	public Map<Integer, DataSourceBean> getDataSourceBeanMap(Map<String, Object> params) {
		Map<Integer, DataSourceBean> map = null;
		try {
			List<DataSourceBean> DataSourceBeanList = dataSourceBeanDao.findAll();
			if (DataSourceBeanList != null && DataSourceBeanList.size()>0){
				map = new HashMap<Integer, DataSourceBean>();
				for (DataSourceBean dataSourceBean : DataSourceBeanList){
					map.put(dataSourceBean.getId(), dataSourceBean);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<Integer, String> getDataSourceNameMap(Map<String, Object> params) {
		Map<Integer, String> map = null;
		try {
			List<DataSourceBean> dataSourceBeanList = dataSourceBeanDao.findAll();
			if (dataSourceBeanList !=null && dataSourceBeanList.size() > 0) {
				map = new HashMap<Integer, String>();
				for(DataSourceBean dataSourceBean : dataSourceBeanList){
					map.put(dataSourceBean.getId(), dataSourceBean.getDataName());
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<DataSourceBean> selectByMt4DatasourceTypeAndSourceWebSideType(int mt4DatasourceType, int sourceWebSideType) {
		Map map = new HashMap();
		map.put("mt4DatasourceType",mt4DatasourceType);
		map.put("sourceWebSideType",sourceWebSideType);
		return dataSourceBeanDao.selectByMt4DatasourceTypeAndSourceWebSideType(map);
	}

	@Override
	public List<DataSourceBean> getDataSourceBeanByParams(
			HashMap<String, Object> params) {
		return dataSourceBeanDao.findList("getDataSourceBeanByParams", params);
	}
}