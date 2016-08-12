package mybatis.framework.core.dao;


import java.util.List;

import mybatis.framework.core.support.ISqlSessionWrapper;
import mybatis.framework.core.support.SqlSessionWrapper;
import mybatis.framework.core.support.SqlSessionWrapperGlobal;
import mybatis.framework.core.support.SqlSessionWrapperMt4TradeRecord;
import mybatis.framework.util.DBContants;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fx.util.SpringUtils;

/**
 * User: hui.ouyang
 * Date: 2010-4-8
 * Time: 13:53:43
 */
public abstract class Dao {
//	private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config/applicationContext.xml"});
	
    private ISqlSessionWrapper sessionWrapper;
    
	public Dao(){
		
	}
	
	public Dao(String dataSource){
//		    BeanFactory factory = (BeanFactory) context;
		if(dataSource.equals(DBContants.DB_GLOBAL)){
			sessionWrapper = (SqlSessionWrapperGlobal)SpringUtils.getBean("globalSqlSessionWrapper");
		}else if(dataSource.equals(DBContants.DB_MT4TRADE)){
			sessionWrapper = (SqlSessionWrapperMt4TradeRecord)SpringUtils.getBean("mt4TradeRecordSqlSessionWrapper");
		}else {
			sessionWrapper = (SqlSessionWrapper)SpringUtils.getBean("sqlSessionWrapper");
		}
	}

    protected Object selectOne(String statement) {
        return selectOne(statement, null);
    }

    protected Object selectOne(String statement, Object parameter) {
        String operationId = statement.substring(statement.lastIndexOf(".") + 1);
        if (!operationId.equals("get")) {
            List list = selectList(statement, parameter, RowBounds.DEFAULT);
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        } else {
            return sessionWrapper.selectOne(statement, parameter);
        }
    }

    protected List selectList(String statement) {
        return selectList(statement, null);
    }

    protected List selectList(String statement, Object parameter) {
        return selectList(statement, parameter, RowBounds.DEFAULT);
    }

    protected List selectList(String statement, Object parameter, RowBounds rowBounds) {
        return sessionWrapper.selectList(statement, parameter, rowBounds);
    }

    protected int insert(String statement) {
        return insert(statement, null);
    }

    protected int insert(String statement, Object parameter) {
        return sessionWrapper.insert(statement, parameter);
    }

    protected int update(String statement) {
        return update(statement, null);
    }

    protected int update(String statement, Object parameter) {
        return sessionWrapper.update(statement, parameter);
    }

    protected int delete(String statement) {
        return delete(statement, null);
    }

    protected int delete(String statement, Object parameter) {
        return sessionWrapper.delete(statement, parameter);
    }

}