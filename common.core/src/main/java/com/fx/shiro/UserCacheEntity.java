package com.fx.shiro;

import com.fx.admin.model.Role;
import com.fx.crm.sys.menu.model.Menu;
import com.fx.crm.sys.org.model.Organization;
import com.fx.shiro.authz.SimpleAuthorizationInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 用户缓存对象
 * Created by bei2love@gmail.com on 15/9/9.
 */
public class UserCacheEntity<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String loginName = "unknown";// admin,unknown
    private String password;
    private String loginCaption = "Unknown";// Administrator,Unknown
    private String loginIP = "127.0.0.1";
    private Date loginDate = java.util.Calendar.getInstance().getTime();;// 2014-02-25
    private Date loginHeartbeatDate = java.util.Calendar.getInstance().getTime();// 1393392730873,间隔15分钟(loginHeartbeatDate>System.currentTimeMillis()在线)

    private T extend;// extend object


    private Locale locale;
    private List<String> roleIds;
    private List<Role> roles;
    private List<Menu> rightMenu;

    private Integer orgId;
    private boolean superUser;

    private Organization org;

    private Organization company;

    private Serializable sessionId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private SimpleAuthorizationInfo simpleAuthorizationInfo;

    public UserCacheEntity() {

    }

    public Organization getCompany() {
        return company;
    }

    public void setCompany(Organization company) {
        this.company = company;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public UserCacheEntity(int userId) {
        this.userId = userId;
    }

    public void setSimpleAuthorizationInfo(SimpleAuthorizationInfo simpleAuthorizationInfo) {
        this.simpleAuthorizationInfo = simpleAuthorizationInfo;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public SimpleAuthorizationInfo getSimpleAuthorizationInfo() {
        return simpleAuthorizationInfo;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginCaption(String loginCaption) {
        this.loginCaption = loginCaption;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setLoginHeartbeatDate(Date loginHeartbeatDate) {
        this.loginHeartbeatDate = loginHeartbeatDate;
    }

    public void setExtend(T extend) {
        this.extend = extend;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Serializable getSessionId() {
        return sessionId;
    }

    public void setSessionId(Serializable sessionId) {
        this.sessionId = sessionId;
    }

    public void setRightMenu(List<Menu> rightMenu) {
        this.rightMenu = rightMenu;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginCaption() {
        return loginCaption;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public Date getLoginHeartbeatDate() {
        return loginHeartbeatDate;
    }

    public T getExtend() {
        return extend;
    }

    public Locale getLocale() {
        return locale;
    }

    public List<Menu> getRightMenu() {
        return rightMenu;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    @Override
    public String toString() {
        return "UserCacheEntity{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", loginCaption='" + loginCaption + '\'' +
                ", loginIP='" + loginIP + '\'' +
                ", loginDate=" + loginDate +
                ", loginHeartbeatDate=" + loginHeartbeatDate +
                ", locale=" + locale +
                ", roleIds=" + roleIds +
                ", roles=" + roles +
                ", rightMenu=" + rightMenu +
                ", orgId=" + orgId +
                ", superUser=" + superUser +
                ", org=" + org +
                ", company=" + company +
                ", sesssionId=" + sessionId +
                ", simpleAuthorizationInfo=" + simpleAuthorizationInfo +
                ", extend=" + extend +
                '}';
    }
}
