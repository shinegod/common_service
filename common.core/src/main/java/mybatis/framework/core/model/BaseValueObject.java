/**
 * 
 */
package mybatis.framework.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fx.util.Pagination;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Map;

/**

 */
public abstract class BaseValueObject<T> implements IValueObject,Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;

	protected Pagination<T> pagination;

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public Pagination<T> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<T> pagination) {
		this.pagination = pagination;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
}
