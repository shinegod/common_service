package com.fx.activiti.service.ext;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Activiti Group工厂
 * Created by bei2love@gmail.com on 15/9/1.
 */
public class ActGroupEntityServiceFactory {
    @Autowired
    private ActGroupEntityService actGroupEntityService;

    public Class<?> getSessionType() {
        // 返回原始的GroupIdentityManager类型
        return GroupIdentityManager.class;
    }

    public Session openSession() {
        // 返回自定义的GroupEntityManager实例
        return actGroupEntityService;
    }
}
