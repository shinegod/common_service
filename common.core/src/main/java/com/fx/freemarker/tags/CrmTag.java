package com.fx.freemarker.tags;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.*;

import java.io.IOException;
import java.util.Map;

/**
 * crm自定义标签的抽象实现
 * Created by bei2love@gmail.com on 15/9/11.
 */
public abstract class CrmTag implements BaseTag{

    protected static final String TAG_HOME = "/template";

    public CrmTag() {
        Configuration cfg = new Configuration();
    }

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        this.verifyParameters(params);
        this.processTemplate(env, params, body);
    }

    private void processTemplate(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        Template templ = env.getConfiguration().getTemplate(getTemplatePath());
        this.renderTemplate(env, params, body);
        templ.process(params, env.getOut());
    }

    public abstract String getTemplatePath();

    protected void verifyParameters(Map params) throws TemplateModelException {
    }

    /**
     * 输出标签体内容
     * @param env
     * @param body
     * @throws IOException
     * @throws TemplateException
     */
    protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
        if(body != null) {
            body.render(env.getOut());
        }
    }

    /**
     * 设置模板参数
     * @param var1
     * @param var2
     * @param var3
     * @throws IOException
     * @throws TemplateException
     */
    public abstract void renderTemplate(Environment var1, Map var2, TemplateDirectiveBody var3) throws IOException, TemplateException;

    public abstract void render(Environment var1, Map var2, TemplateDirectiveBody var3) throws IOException, TemplateException;

    protected String getParam(Map params, String name) {
        Object value = params.get(name);
        return value instanceof SimpleScalar ?((SimpleScalar)value).getAsString():null;
    }

    /**
     * 获取标签传递的字符串类型变量
     * @param param
     * @return
     */
    protected String getStringParam(Object param) {
        if (param == null) {
            return "";
        }
        if(param instanceof StringModel){
            return ((StringModel)param).getAsString();
        }
        SimpleScalar ss = (SimpleScalar)param;
        return  ss.getAsString();
    }

    /**
     * 获取参数Map中的key值
     * @param params
     * @param key
     * @return
     */
    protected String getParamValue(Map params, String key) {
        if(params.keySet().contains(key)) {
            Object obj = params.get(key);
            return getStringParam(obj);
        }
        return  null;
    }
}
