package mybatis.framework.core.service;


import com.fx.admin.model.Role;
import com.fx.multidatasource.MultiDataSourceService;
import com.fx.shiro.UserCacheEntity;
import com.fx.util.Config;
import com.fx.util.Constants;
import com.fx.util.UserUtil;
import com.google.common.collect.Lists;
import mybatis.framework.core.dao.IValueObjectDao;
import mybatis.framework.core.model.IValueObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Service接口抽象实现,主要用来获得Dao对象

 *
 * @param <V>
 */
public abstract class BaseService<V extends IValueObject> extends MultiDataSourceService implements IValueObjectService<V>
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public BaseService()
	{
	}
	
	
	/**
	 * 返回dao
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected IValueObjectDao<V> getDao()
	{
		if (dao != null)
			return dao;
		/**
		 * 通过解析field获得DAO
		 */
		Class targetC = getGenericClass(getClass(), 0);
		if (targetC == Object.class)
			return dao;

		try
		{
			Field[] fields = getClass().getDeclaredFields();
			if (fields != null)
			{
				int len = fields.length;
				for (int i = 0; i < len; i++)
				{
					Field field = fields[i];

					Object o = null;
					try
					{
						field.setAccessible(true);
						o = field.get(this);
					}
					catch (Exception e)
					{
						continue;
					}
					if (!useObject(o, targetC))
						continue;
					dao = (IValueObjectDao) o;
					break;
				}
			}
		}
		catch (Exception e)
		{
			logger.info("Can not get bean info", e);
			return dao;
		}
		
		return dao;
	}

	private boolean useObject(Object obj, Class targetC)
	{
		if (obj instanceof IValueObjectDao)
		{
			Class clazz[] = obj.getClass().getInterfaces();
			Class arr[] = clazz;
			int len = arr.length;
			for (int i = 0; i < len; i++)
			{
				Class interClazz = arr[i];
				java.lang.reflect.Type genType[] = interClazz.getGenericInterfaces();
				for (int j = 0; j < genType.length; j++)
				{
					java.lang.reflect.Type interGenType = genType[j];
					if ((interGenType instanceof ParameterizedType)
							&& ((ParameterizedType) interGenType).getActualTypeArguments()[0] == targetC)
						return true;
				}

			}

		}
		return false;
	}
	
	private IValueObjectDao<V> dao;

	private Class getGenericClass(Class clazz, int index)
        throws IndexOutOfBoundsException
    {
        java.lang.reflect.Type genType;
        for(genType = clazz.getGenericSuperclass(); !(genType instanceof ParameterizedType); genType = clazz.getGenericSuperclass())
        {
            if(clazz == java.lang.Object.class)
                return clazz;
            clazz = clazz.getSuperclass();
        }

        java.lang.reflect.Type params[] = ((ParameterizedType)genType).getActualTypeArguments();
        if(index >= params.length || index < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Index: ").append(index).append(", Size of Parameterized Type: ").append(params.length).toString());
        else
            return (Class)params[index];
    }

	/**
	 * 数据范围过滤
	 * @param officeAlias 机构表别名，多个用“,”逗号隔开。
	 * @param userAlias 用户表别名，多个用“,”逗号隔开，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	public static String dataScopeFilter(String officeAlias, String userAlias) {
		if(!Boolean.valueOf(Config.getConfig("system.dataScope.enable"))){
			return "";
		}
		UserCacheEntity<?> user = UserUtil.getCurrAdmin();
		StringBuilder sqlString = new StringBuilder();

		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();

		// 超级管理员，跳过权限过滤
		if (!UserUtil.isSuperUser(user)){
			boolean isDataScopeAll = false;
			//当前系统仅支持用户有单一角色
//			for (Role r : user.getRoleList()){
//			IRoleService roleService = SpringUtils.getBean(IRoleService.class);

//			IRoleDatascopeService roleDatascopeService = SpringUtils.getBean(IRoleDatascopeService.class);

			List<Role> roles = user.getRoles();
			//当前用户只有一个角色，默认取第一个
			if (roles.size() == -1) {
				throw new RuntimeException("user not auth");
			}
			Role r = roles.get(0);
			String roleDataScope = String.valueOf(r.getDataScope());
//			DATA_SCOPE_ALL = "1";
//			DATA_SCOPE_COMPANY_AND_CHILD = "2";
//			DATA_SCOPE_COMPANY = "3";
//			DATA_SCOPE_OFFICE_AND_CHILD = "4";
//			DATA_SCOPE_OFFICE = "5";
//			DATA_SCOPE_SELF = "8";
//			DATA_SCOPE_CUSTOM = "9";
//			List<String> roleDatascopeList = roleDatascopeService.findByRoleId(r.getId());
			for (String oa : StringUtils.split(officeAlias, ",")){
				if (!dataScope.contains(roleDataScope) && StringUtils.isNotBlank(oa)){
					if (Constants.ROLE.DATA_SCOPE_ALL.equals(roleDataScope)){
						isDataScopeAll = true;
					}
					else if (Constants.ROLE.DATA_SCOPE_COMPANY_AND_CHILD.equals(roleDataScope)){
						sqlString.append(" OR " + oa + ".id = '" + user.getCompany().getId() + "'");
						sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getCompany().getParentIds() + user.getCompany().getId() + ",%'");
					}
					else if (Constants.ROLE.DATA_SCOPE_COMPANY.equals(roleDataScope)){
						sqlString.append(" OR " + oa + ".id = '" + user.getOrgId() + "'");
						// 包括本公司下的部门 （type=1:公司；type=2:部门）
						sqlString.append(" OR (" + oa + ".id = '" + user.getCompany().getId() + "' AND " + oa + ".org_type = '"+Constants.ORG_TYPE_COMPANY+"')");
					}
					else if (Constants.ROLE.DATA_SCOPE_OFFICE_AND_CHILD.equals(roleDataScope)){
						sqlString.append(" OR " + oa + ".id = '" + user.getOrgId() + "'");
						sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getOrg().getParentIds() + user.getOrg().getId() + ",%'");
					}
					else if (Constants.ROLE.DATA_SCOPE_OFFICE.equals(roleDataScope)){
						sqlString.append(" OR " + oa + ".id = '" + user.getOrg().getId() + "'");
					}
					//TODO:明细设置暂不支持
					else if (Constants.ROLE.DATA_SCOPE_CUSTOM.equals(roleDataScope)){
//						String officeIds =  StringUtils.join(roleDatascopeList, "','");
//						if (StringUtils.isNotEmpty(officeIds)){
//							sqlString.append(" OR " + oa + ".id IN ('" + officeIds + "')");
//						}
						throw new RuntimeException("datascope not support");
					}
					//else if (Role.DATA_SCOPE_SELF.equals(roleDataScope)){
					dataScope.add("" + roleDataScope);
				}
			}
//			}
			// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
			if (!isDataScopeAll){
				if (StringUtils.isNotBlank(userAlias)){
					for (String ua : StringUtils.split(userAlias, ",")){
						sqlString.append(" OR " + ua + ".id = '" + user.getUserId() + "'");
					}
				}else {
					for (String oa : StringUtils.split(officeAlias, ",")){
						sqlString.append(" OR " + oa + ".id  = " + user.getOrgId());
//						sqlString.append(" OR " + oa + ".id IS NULL");
					}
				}
			}else{
				// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
				sqlString = new StringBuilder();
			}
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}

}
