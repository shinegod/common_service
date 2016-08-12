package mybatis.framework.core.support;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * 
 */
public interface ISqlSessionWrapper {
    
	public Object selectOne(String statement, Object parameter);

    public List selectList(String statement, Object parameter, RowBounds rowBounds);

    public int insert(String statement, Object parameter);

    public int update(String statement, Object parameter);

    public int delete(String statement, Object parameter);

    public SqlSession getBatchSqlSession(boolean isReadOnly);
}
