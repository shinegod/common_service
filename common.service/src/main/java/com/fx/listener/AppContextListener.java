package com.fx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fx.config.AppConfigs;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AppConfigs.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
