package com.fx.multidatasource;

import java.util.HashMap;
import java.util.Map;

import com.fx.dataSourceBean.model.DataSourceBean;

/**
 * 多数据源调用辅助类
 * 
 * @author jason.jiang
 */
public class DataSourceContextHolder {  
   
    private static final ThreadLocal<Integer> contextHolder = new ThreadLocal<Integer>();  
    
    private static Map<Integer, String> DATASOURCENAME_MAP = new HashMap<Integer, String>();  
    
	private static Map<Integer, DataSourceBean> DATASOURCEBEAN_MAP = new HashMap<Integer, DataSourceBean>();  
    
    public static Map<Integer, String> getDATASOURCENAME_MAP() {
		return DATASOURCENAME_MAP;
	}

	public static void setDATASOURCENAME_MAP(Map<Integer, String> dATASOURCENAME_MAP) {
		DATASOURCENAME_MAP = dATASOURCENAME_MAP;
	}

	public static Map<Integer, DataSourceBean> getDATASOURCEBEAN_MAP() {
		return DATASOURCEBEAN_MAP;
	}

	public static void setDATASOURCEBEAN_MAP(Map<Integer, DataSourceBean> dATASOURCEBEAN_MAP) {
		DATASOURCEBEAN_MAP = dATASOURCEBEAN_MAP;
	}

    public static Map<Integer, String> getDatasourcenameMap() {
		return DATASOURCENAME_MAP;
	}

	public static Map<Integer, DataSourceBean> getDatasourcebeanMap() {
		return DATASOURCEBEAN_MAP;
	}
    
    public static void setDataSourceId(Integer dataSourceId){  
        contextHolder.set(dataSourceId);  
    }  
       
    public static Integer getDataSourceId(){  
        return (Integer) contextHolder.get();  
    }  
       
    public static void clearDataSourceType(){  
        contextHolder.remove();  
    }  
     
   
}