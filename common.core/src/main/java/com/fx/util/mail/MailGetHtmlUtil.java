package com.fx.util.mail;

import com.fx.util.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barry on 2016/1/4.
 */
public class MailGetHtmlUtil {

    private static Logger logger = LoggerFactory.getLogger(MailGetHtmlUtil.class);

    public static String TEMPLATE_PATH = Config.getConfig("mail.template.path");

    private static File FTL_FILE = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/WEB-INF/ftl"));

    public static String getEmailContent(String templateName, Map<String,Object> data){
        return parseTemplate(TEMPLATE_PATH, templateName, data);
    }
    /**
     * 返回HTML片段[参数可注入]
     * @param templatePath FTL文件路径{/WEB-INF/ftl以后的部分}
     * @param templateFileName FTL文件名称
     * @param data 注入参数
     * @return
     */
    public static String parseTemplate(String templatePath,String templateFileName, Map<String,Object> data) {
        String parseResult = null;
        File file = new File(FTL_FILE,templatePath);
        try {
            Configuration config = new Configuration();
            config.setDirectoryForTemplateLoading(file);
            Template template = config.getTemplate(templateFileName + ".ftl",
                    "UTF-8");
            StringWriter out = new StringWriter();
            template.process(data, out);
            out.flush();
            parseResult = out.getBuffer().toString();
            out.close();
        } catch (IOException e) {
            logger.error(
                    (new StringBuilder()).append("Can't find the template:")
                            .append(file).toString(), e);
        } catch (TemplateException e) {
            logger.error(
                    (new StringBuilder()).append("The format of the template ")
                            .append(templateFileName).append(" is wrong.")
                            .toString(), e);
        } catch (Exception e) {
            logger.error("other exception", e);
        }
        return parseResult;
    }
}
