package com.fx.shiro.session.mgt;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * Created by bei2love@gmail.com on 15/9/21.
 */
public class EnterpriseCacheSessionDAO extends org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO {


    @Override
    protected void doUpdate(Session session) {
        if (session != null) {
            getCacheManager().getCache(getActiveSessionsCacheName()).put(session.getId(), session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        if (session != null) {
            getCacheManager().getCache(getActiveSessionsCacheName()).remove(session.getId());
        }
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId != null) {
            return (Session) getCacheManager().getCache(getActiveSessionsCacheName()).get(sessionId);
        }
        return null;
    }

}
