package com.fx.shiro.realm;


import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.crm.sys.datarule.service.IDataRuleDefineService;
import com.fx.crm.sys.permission.model.Permission;
import com.fx.crm.sys.permission.service.IPermissionService;
import com.fx.shiro.Constants;
import com.fx.shiro.UserCacheEntity;
import com.fx.shiro.authz.SimpleAuthorizationInfo;
import com.fx.shiro.exception.ExistUserLoginException;
import com.fx.shiro.exception.UnknownAccountException;
import com.fx.shiro.token.UsernamePasswordCaptchaToken;
import com.fx.user.util.UserUtils;
import com.fx.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bei2love@gmail.com on 15/4/27.
 */
public class CustomizeAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(CustomizeAuthorizingRealm.class);

    @Resource
    private IAdminService adminService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IDataRuleDefineService dataRuleDefineService;

    @Resource
    private IPermissionService permissionService;

    public static final String SHIRO_USER_KEY = "_USERID_";


    /**
     * 授权查询回调，在缓存中查询不到鉴权信息时调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CustomizePrincipal principal = (CustomizePrincipal) getAvailablePrincipal(principals);
        //当前只按照Permission进行验证
        UserCacheEntity<?> user = CacheMgr.getUserOnLine("" + principal.getUserId());
        SimpleAuthorizationInfo info = user.getSimpleAuthorizationInfo();
        if(info == null){
            info = UserUtil.packageSimpleAuthorizationInfo(principal.getId());
            CacheMgr.put(Constants.SHIRO_AUTHORIZATIONINFO_KEY+SHIRO_USER_KEY+principal.getId(), info);
        }
        return info;
    }

    @Deprecated
    private SimpleAuthorizationInfo getSimpleAuthorizationInfo(Integer id) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin admin = adminService.findById(id);
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
     * 身份认证回调，在用户登录时调用
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException, UnknownAccountException {
        //对token类型进行判断
        if(!(authcToken instanceof UsernamePasswordCaptchaToken)){
            return null;
        }
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
        Session session = SecurityUtils.getSubject().getSession();
        /***
        if (JCaptcha.isValidateCodeLogin(token.getUsername(), false, false)) {


            //重启浏览器，获取验证码机制存在，但验证码控件已经不显示，获取不到验证码，并且用户已经锁定，直接抛出锁定异常
            if(token.getCaptcha() == null && validateIsLocked(token.getUsername(),true)){
                throw new LockedAccountException();
            }else{
                if (!JCaptcha.hasCaptcha(session.getId().toString(),token.getCaptcha())) {
                    if(validateIsLocked(token.getUsername(),true))
                        throw new LockedAccountException();
                    else
                        throw new CaptchaException();
                }
            }

        }***/
        /**
        String language = "";
        if (StringUtils.isBlank(token.getLanguage()))language = Config.getLangCodeDefault();
        else language = token.getLanguage();
        String[] locales = language.split("_");
        if (locales.length != 2) {
            language = Config.getLangCodeDefault();
            locales = language.split("_");
        }
        Locale locale = new Locale(locales[0], locales[1]);
        */
        String passwd = "";
        if (token.getPassword() != null) {
            passwd = new String(token.getPassword());
        }
        logger.debug("shiro 对{{},{}}进行身份认证. ",new Object[] { token.getUsername(), passwd });
        Admin admin = adminService.findByNameOrEmail(token.getUsername());
        if(null!=admin){
            logger.debug("shiro获取到的用户信息{{},{}}",new Object[] { admin.getName(),admin.getPassword() });
        }else{
            //用户名错误，抛出账户异常
            throw new UnknownAccountException();
        }

        logger.info("the administrator logined by name:{}",admin.getName());
        char[] cs = token.getPassword();
        StringBuffer passwordBuffer = new StringBuffer();
        for (char c : cs) {
        	passwordBuffer.append(c);
		}
        String MD5Password = DigestUtils.sha512Hex(admin.getSalt()+passwordBuffer.toString()+admin.getName());
        if (StringUtils.isBlank(admin.getPassword()) || !admin.getPassword().equals(MD5Password)) {
            logger.info("验证账户{ {} 失败，提交密码{}，{}}",new Object[]{token.getUsername(),token.getPassword(),admin.getPassword()});
            throw new UnknownAccountException();
        }
        if(isLoginUser(admin.getUserId())){
            logger.info("当前用户{}已经登录，并正在操作系统。", admin.getName());
            throw new ExistUserLoginException();
        }
        String lastLoginTime = admin.getUpdateTime();
        admin.setLastLoginTime(lastLoginTime);
        //TODO 添加SESSION 记录
        session.setAttribute("utc", token.getUtc());
        int loginNum = (null == admin.getLoginNum()? 0: admin.getLoginNum());
        admin.setLoginNum(loginNum + 1);
        admin.setUpdateUser(String.valueOf(admin.getId()));
        admin.setUpdateTime(DateUtil.getCurrentTime());
        admin.setUpdateIp(session.getHost());
        adminService.updateAdmin(admin);
        session.setAttribute(Constants.KEY_LOGIN_ADMIN_4_SESSION, admin);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new CustomizePrincipal(admin), token.getPassword(),getName());
        UserUtil.addOnlineUser(admin.getId(), admin.getUserId());
//        CacheMgr.put(Constants.SHIRO_AUTHORIZATIONINFO_KEY+SHIRO_USER_KEY+admin.getId(), getSimpleAuthorizationInfo(admin.getId()));
        return info;
    }

    /**
     * 判断当前用户是否登录
     * @param userId
     * @return
     */
    private boolean isLoginUser(int userId) {
        Map<String, String> onlineMap = CacheMgr.getOnlineUser();
        if (onlineMap == null) {
            return false;
        }
        if (onlineMap.keySet().contains("" + userId)) {
            //判断用户心跳
            UserCacheEntity user = CacheMgr.getUserOnLine("" + userId);
//            if(UserUtil.changeUserHeartbeatAble(user)){
                UserUtil.kickUser(user);
//                return false;
//            }else{
//                return true;
//            }
        }
        return false;
    }

    /**
     * 授权用户信息
     */
    public static class CustomizePrincipal implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private Integer userId;
        private String loginName;
        private String name;

        public CustomizePrincipal(Admin user) {
            this.id = user.getId();
            this.userId = user.getUserId();
            this.loginName = user.getName();
            this.name = user.getName();
        }

        public Integer getId() {
            return id;
        }

        public Integer getUserId() {
            return userId;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
