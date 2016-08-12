package com.fx.activiti.service.ext;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Activiti User 工厂
 * Created by bei2love@gmail.com on 15/9/1.
 */
public class ActUserEntityServiceFactory {

    @Autowired
    private ActUserEntityService actUserEntityService;

    public Class<?> getSessionType() {
        // 返回原始的UserIdentityManager类型
        return UserIdentityManager.class;
    }

    public Session openSession() {
        // 返回自定义的GroupEntityManager实例
        return actUserEntityService;
    }
}
