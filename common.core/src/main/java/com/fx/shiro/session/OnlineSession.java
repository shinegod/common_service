package com.fx.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * Created by bei2love@gmail.com on 15/9/22.
 */
public class OnlineSession extends SimpleSession {
    //当前登录的用户Id
    private Long userId = 0L;

    private String username;

    /**
     * 用户浏览器类型
     */
    private String userAgent;

    /**
     * 用户登录时系统IP
     */
    private String systemHost;


    public static enum OnlineStatus {
        on_line("在线"), hidden("隐身"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


    private OnlineStatus status;


    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSystemHost() {
        return systemHost;
    }

    public void setSystemHost(String systemHost) {
        this.systemHost = systemHost;
    }
}
