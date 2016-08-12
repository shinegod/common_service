package com.fx.shiro.filter;

import com.fx.shiro.realm.CustomizeAuthorizingRealm;
import com.fx.util.CacheMgr;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by bei2love@gmail.com on 15/4/30.
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //TODO:整理用户登录设置的缓存对象
        Subject subject = getSubject(request, response);
        CustomizeAuthorizingRealm.CustomizePrincipal principal = (CustomizeAuthorizingRealm.CustomizePrincipal) subject.getPrincipal();
        CacheMgr.remove("" + principal.getId());
        return super.preHandle(request, response);
    }
}
