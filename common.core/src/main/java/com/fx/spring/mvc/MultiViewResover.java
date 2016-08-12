package com.fx.spring.mvc;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import java.util.Map;
import java.util.Locale;

/**
 * Created by bei2love@gmail.com on 15/9/10.
 */
public class MultiViewResover implements ViewResolver {
    private Map<String, ViewResolver> resolvers;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        int n = viewName.lastIndexOf(".");
        String suffix = "";
        if (n == (-1) || !viewName.substring(n + 1).equals("ftl")) {
            suffix = "ftl";
        }else {
            suffix = viewName.substring(n + 1);
            viewName = viewName.substring(0, n);
        }

        ViewResolver resolver = resolvers.get(suffix);
        if (resolver != null) {
            return resolver.resolveViewName(viewName, locale);
        }
        else {
            return null;
        }
    }

    public Map<String, ViewResolver> getResolvers() {
        return resolvers;
    }

    public void setResolvers(Map<String, ViewResolver> resolvers) {
        this.resolvers = resolvers;
    }


}
