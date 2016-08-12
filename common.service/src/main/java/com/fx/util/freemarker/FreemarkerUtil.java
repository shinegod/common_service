package com.fx.util.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public class FreemarkerUtil {

	private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
	
	private static File FTL_FILE = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/WEB-INF/ftl"));
	
	/**
	 * 导入枚举到ftl中
	 * @param enumClass
	 * @param model
	 */
    @SuppressWarnings({ "rawtypes" })
	public static void importEnum2Ftl(Class enumClass, ModelAndView mv) {
    	importEnum2Ftl(enumClass, mv.getModelMap());
    }

	public static void importEnum2Ftl(Class enumClass, Map<String, Object> modelMap) {
        try {
            String classFullName = enumClass.getName();
            Integer lastPoint = classFullName.lastIndexOf('.');
            String className = classFullName.substring(lastPoint + 1);
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel enumModels = wrapper.getEnumModels();
            TemplateHashModel enumS = (TemplateHashModel) enumModels.get(classFullName);
            modelMap.put(className, enumS);
        } catch (TemplateModelException templateModelException) {
            logger.error("import enum to ftl error, msg:{}", templateModelException);
        }
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
