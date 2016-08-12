package com.fx.shiro.filter;

import com.fx.shiro.UserCacheEntity;
import com.fx.shiro.realm.CustomizeAuthorizingRealm;
import com.fx.util.CacheMgr;
import com.fx.util.SpringUtils;
import com.fx.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by bei2love@gmail.com on 15/4/27.
 */
public class UserFilter extends AccessControlFilter {


    private static final Logger logger = LoggerFactory.getLogger(UserFilter.class);



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	logger.debug(getPathWithinApplication(request));

        if (isLoginRequest(request, response)) {
            return true;
        }else{
            Subject subject = getSubject(request, response);
            if(subject.isAuthenticated()){
                Object principal = subject.getPrincipal();
                if(null==principal)
                    return false;
                else{
                    MemorySessionDAO sessionDAO = SpringUtils.getBean("sessionDAO");
                    CustomizeAuthorizingRealm.CustomizePrincipal custprincipal = (CustomizeAuthorizingRealm.CustomizePrincipal)principal;
                    if(!CacheMgr.isUserOnline("" + custprincipal.getUserId())){
                        SecurityUtils.getSubject().logout();
                        return false;
                    }else {
//                        UserCacheEntity<?> user =  UserUtil.getCurrAdmin();
//                        UserUtil.changeUserHeartbeat("" + custprincipal.getUserId());
//                        logger.debug("用户:{}, logindate: {}, hearDate: {}, uri : {}", user.getLoginName(), user.getLoginDate(), System.currentTimeMillis(), getPathWithinApplication(request));
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
