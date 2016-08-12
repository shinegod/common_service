package com.fx.util;

import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.crm.sys.menu.model.Menu;
import com.fx.crm.sys.menu.service.IMenuService;
import com.fx.crm.sys.org.model.Organization;
import com.fx.crm.sys.org.service.IOrganizationService;
import com.fx.crm.sys.permission.model.Permission;
import com.fx.crm.sys.permission.service.IPermissionService;
import com.fx.shiro.UserCacheEntity;
import com.fx.shiro.authz.SimpleAuthorizationInfo;
import com.fx.shiro.realm.CustomizeAuthorizingRealm;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/5/21.
 */
public class UserUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);

    /**
     * 获取当前登录用户
     * @return
     */
    public static UserCacheEntity<?> getCurrAdmin(){
        Subject subject = SecurityUtils.getSubject();
        CustomizeAuthorizingRealm.CustomizePrincipal principal = (CustomizeAuthorizingRealm.CustomizePrincipal) subject.getPrincipal();

        if(principal == null) {
            return null;
        }
        Map<String,String> userMap = CacheMgr.getOnlineUser();
        if(userMap == null){
            return null;
        }
        return CacheMgr.getUserOnLine("" + principal.getUserId());
    }

    /**
     * 获取当前登录用户的功能权限
     */
    public static Set<String> getCurrPermissions(){
        UserCacheEntity admin = getCurrAdmin();
        if(admin == null){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = admin.getSimpleAuthorizationInfo();
        return authorizationInfo.getStringPermissions();
    }

    /**
     * 获取当前登录用户的数据权限
     * @return
     */
    public static Map<String, DataRuleLevel> getCurrDataRules(){
        UserCacheEntity admin = getCurrAdmin();
        if(admin == null){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = admin.getSimpleAuthorizationInfo();
        return authorizationInfo.getDataRuleDefines();
    }

    /**
     * 判断当前用户是否为超级用户
     * @param user
     * @return
     */
    public static boolean isSuperUser(UserRegister user) {
        if (user == null) {
            return false;
        }
        IAdminService adminService = SpringUtils.getBean(IAdminService.class);
        Admin admin = adminService.findByUserId(user.getId());
        return isSuperUser(admin);
    }

    /**
     * 判断当前用户是否为超级用户
     * @param user
     * @return
     */
    public static boolean isSuperUser(UserCacheEntity<?> user) {
        //当前传递用户为空，从缓存获取
        if(user == null){
            return false;
        }
        return user.isSuperUser();
    }

    /**
     * 判断当前用户是否为超级用户
     * @param admin
     * @return
     */
    public static boolean isSuperUser(Admin admin) {
        if(admin.getId() == 1){
            return true;
        }
        return false;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @Deprecated
    public static UserRegister getCurrUser() {
        return null;
    }

    public static void addOnlineUser(Integer adminId, Integer userId) {
        new UserCacheEntityInitThread(adminId, userId).run();
    }

    /**
     * 跟UserId初始化User缓存对象
     * @param adminId
     * @param userId
     * @return
     */
    public static UserCacheEntity<?> initUserCacheEntity(Integer adminId, Integer userId) {
        HttpServletRequest request = UserUtil.getHttpServletReqeust();
        IOrganizationService orgService = SpringUtils.getBean(IOrganizationService.class);
        IUserRegisterService userRegisterService = SpringUtils.getBean(IUserRegisterService.class);
        IAdminService adminService = SpringUtils.getBean(IAdminService.class);
        IRoleService roleService = SpringUtils.getBean(IRoleService.class);
        Admin admin = adminService.findById(adminId);
        UserRegister userRegister = userRegisterService.findById(userId);
        UserCacheEntity<UserRegister> userCache = new UserCacheEntity<>();
        userCache.setExtend(userRegister);
        userCache.setLocale(request.getLocale());
        userCache.setLoginName(userRegister.getEnName());
        userCache.setLoginCaption(userRegister.getCnName());
        userCache.setLoginDate(new Date());
        String[] roleId = { "" + admin.getRoleId()};
        userCache.setRoleIds(Arrays.asList(roleId));
        //TODO: 当前只支持一个用户一个角色
        List<Role> roleList = Lists.newArrayList();
        roleList.add(roleService.findById(admin.getRoleId()));
        userCache.setRoles(roleList);
        Organization org = orgService.findById(userRegister.getOrgId());
        userCache.setOrg(org);
        userCache.setCompany(getUserCompany(org));
//        userCache.setLoginHeartbeatDate();
        userCache.setLoginIP(StringUtils.getRemoteAddr(request));
        userCache.setOrgId(userRegister.getOrgId());
        userCache.setRightMenu(UserUtil.getRightMenu(admin));
        userCache.setUserId(userId);
        userCache.setSimpleAuthorizationInfo(UserUtil.packageSimpleAuthorizationInfo(admin));
        //设置当前用户是否为超级管理员
        userCache.setSuperUser(isSuperUser(admin));
        //设置当前用户会话id
        userCache.setSessionId(SecurityUtils.getSubject().getSession().getId());
        logger.debug("当前登录用户:{},http sessionId: {}, shiro SessionID:{}",userCache.getLoginName(),request.getSession().getId(),SecurityUtils.getSubject().getSession().getId() );
        return userCache;
    }

    /**
     * 获取用户所在公司
     * @param org
     * @return
     */
    public static Organization getUserCompany(Organization org) {
        IOrganizationService orgService = SpringUtils.getBean(IOrganizationService.class);
        if(Constants.ORG.COMPANY.equals(org.getOrgType())){
            return  org;
        }
        Organization o = orgService.findById(org.getParentId());
        return getUserCompany(o);
    }
    /**
     * 获取终端用户的utc信息
     * @return
     */
    public static String getUserUTC() {
    	return (String)SecurityUtils.getSubject().getSession().getAttribute("utc");
    }


    /**
     * 封装
     * @param adminId
     * @return
     */
    public static SimpleAuthorizationInfo packageSimpleAuthorizationInfo(Integer adminId) {
        IAdminService adminService = SpringUtils.getBean(IAdminService.class);
        Admin admin = adminService.findById(adminId);
        return packageSimpleAuthorizationInfo(admin);
    }

    /**
     * 封装
     * @param admin
     * @return
     */
    public static SimpleAuthorizationInfo packageSimpleAuthorizationInfo(Admin admin) {
        IAdminService adminService = SpringUtils.getBean(IAdminService.class);
        IRoleService roleService = SpringUtils.getBean(IRoleService.class);
        IPermissionService permissionService = SpringUtils.getBean(IPermissionService.class);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(admin!=null){
            Role role = roleService.findById(admin.getRoleId());
            //不采用级联查询
            Set<String> permissionSet = new HashSet<String>();
            List<Permission> permissions = roleService.getPermissionByRoleId(role.getId());
            if(Config.SUPPER_ADMIN.equals(admin.getName())){
                permissions = permissionService.findAll();
            }
            for(Permission permission : permissions){
                permissionSet.add(permission.getPermissionExp());
            }
            //这里从缓存获取所有数据权限定义，并设置到用户授权对象中
            //List<String> allDataRule = role.getDataRules();
            // 不采用级联
            List<String> allDataRule = roleService.getDataRulesByRoleId(role.getId());
            Map<String, DataRuleLevel> sqlAuth = DataRuleUtil.getAuthDataRuleLevel(allDataRule);

            info.setDataRuleDefines(sqlAuth);
            info.setStringPermissions(permissionSet);
        }
        return info;
    }

    /**
     * 获取用户菜单权限
     * @param admin
     * @return
     */
    private static List<Menu> getRightMenu(Admin admin) {
        IRoleService roleService = SpringUtils.getBean(IRoleService.class);
        IMenuService menuService = SpringUtils.getBean(IMenuService.class);

        List<Menu> menus = Lists.newArrayList();
        if(isSuperUser(admin)){
            menus = menuService.findAllByStatus();
        }else{
            //判断当前用户角色是否删除
            Role role = roleService.findById(admin.getRoleId());
            if (1 == role.getStatus() && 0 == role.getIsDel()) {
                menus = menuService.findMenuByPersonAndParentMenu(admin.getRoleId(), null);
            }
        }
        //菜单权限
//        List<String> rightList = Lists.newArrayList();
//        List<RolePower> rolePowers = roleService.getRolePowerByRoleId(admin.getRoleId());
//        for (RolePower mid : rolePowers) {
//            rightList.add("" + mid.getPowerId());
//        }
        //menuService.findMenuByPersonAndParentMenu(getCurrentUserInfo().getRoleId(), "0");
        //对menu做国际化

        return packageMenus(menus, "0");
    }

    /**
     * 将Menu菜单组装成上下级Menu
     * @param menus
     * @param parent
     * @return
     */
    private static List<Menu> packageMenus(List<Menu> menus, String parent) {
        List<Menu> child = Lists.newArrayList();
        for (Menu m : menus) {
            if(parent.equals(m.getParentmenu())) {
                m.setChildMenu(packageMenus(menus, m.getMenuid()));
                child.add(m);
            }
        }
        return child;
    }

    /**
     * 获取http请求对象
     * @return
     */
    public static HttpServletRequest getHttpServletReqeust(){
        if(RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Locale
     * @return
     */
    public static Locale getCurrentLocale() {
//        return getHttpServletReqeust().getLocale();
        if(getHttpServletReqeust() == null) return null;
        Locale locale = RequestContextUtils.getLocaleResolver(getHttpServletReqeust()).resolveLocale(getHttpServletReqeust());
        return locale;
    }

    public static void changeUserHeartbeat(String userId) {
        UserCacheEntity<?> user = CacheMgr.getUserOnLine(userId);
        if(changeUserHeartbeatAble(user)){
            user.setLoginHeartbeatDate(new Date(System.currentTimeMillis()));
            CacheMgr.refreshOnlineUserCache(user);
        }

    }

    public static boolean changeUserHeartbeatAble(UserCacheEntity<?> user) {
        return DateUtils.addMinutes(user.getLoginDate(), Integer.valueOf(Config.getConfig(Constants.USER_HEARTBEAT))).after(new Date(System.currentTimeMillis()));
    }

    /**
     * 踢出用户
     * @param user
     */
    public static void kickUser(UserCacheEntity user) {
        logger.debug("kick user : {} ,sessionId : {}", user.getLoginName(), user.getSessionId());
        //清空用户缓存
        CacheMgr.offlineUser("" + user.getUserId());
        //退出当前用户
//        DefaultWebSecurityManager securityManager = SpringUtils.getBean("securityManager");
//        DefaultSessionKey sessionKey = new DefaultSessionKey(user.getSessionId());
//        Session session = securityManager.getSession(sessionKey);
//        if (session instanceof SimpleSession) {
//            SimpleSession s = (SimpleSession) session;
//            s.setExpired(true);
//            s.validate();
//        }

    }

    public static void kickUser(String userId) {
        UserCacheEntity user =CacheMgr.getUserOnLine(userId);
        kickUser(user);
    }
}

/**
 * 初始化用户缓存的线程实现
 */
class UserCacheEntityInitThread extends Thread{

    private Integer userId;

    private Integer adminId;

    public UserCacheEntityInitThread(Integer adminId, Integer userId) {
        this.userId = userId;
        this.adminId = adminId;
    }
    @Override
    public void run() {
        UserCacheEntity<?> userCacheEntity = UserUtil.initUserCacheEntity(adminId, userId);
        CacheMgr.addUserOnline(userCacheEntity);
    }


    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (org.apache.commons.lang3.StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (org.apache.commons.lang3.StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }





}
