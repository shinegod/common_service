package com.fx.freemarker.tags;

import com.fx.common.model.Dictionary;
import com.fx.util.CacheMgr;
import com.fx.util.I18N;
import com.fx.util.StringUtils;
import com.fx.util.UserUtil;
import com.google.common.collect.Lists;
import freemarker.core.Environment;
import freemarker.ext.beans.CollectionModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/9/11.
 */
public class SelectButton extends CrmTag {

    private static final Logger logger = LoggerFactory.getLogger(SelectButton.class);

    /**
     * 组件模板路径
     */
    private static final String TAG_FILE = "/form/selectButton.ftl";

    /**
     * 组件字典表字段的名称
     */
    private static final String DICT_KEY = "dictKey";

    private static final String DATA_KEY = "data";

    /**
     * 组件value字段的名称
     */
    private static final String DEFAULT_VALUE = "value";

    /**
     *  默认选中的值
     */
    private static final String DEFAUTL_SELECT_VALUE = "0";

    private String selectLabel;

    private String selectValue;

    private List<SelectOption> valueList = Lists.newArrayList();

    @Override
    public String getTemplatePath() {
        return TAG_HOME + TAG_FILE;
    }

    @Override
    public void renderTemplate(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        clear();
        logger.debug("组件选中值: {}", selectValue);
        selectValue = getParamValue(params, DEFAULT_VALUE) == null ? "0" : getParamValue(params, DEFAULT_VALUE);
        logger.debug("label:{},value:{}", selectLabel, selectValue);
        if (params.keySet().contains(DICT_KEY)) {
            logger.debug("------->dict config ");
            String dataCode = getParamValue(params, DICT_KEY);
            Map<String, Dictionary> dictMap = CacheMgr.getDictCache(dataCode);
            logger.debug("缓存获取{}对应的字典数据，size : {}", dataCode, dictMap.size());
            setSelectOption(dictMap);
        } else if (params.keySet().contains(DATA_KEY)) {
            logger.debug("------->data config ");
            Object obj = params.get(DATA_KEY);
            if (obj != null) {
                CollectionModel list = (CollectionModel) obj;
                if (list.size() > 0) {
                    valueList = (ArrayList) list.getAdaptedObject(SelectOption.class);
                }
            }
            if (valueList.size() > 0) {
                for (SelectOption so : valueList) {
                    if (StringUtils.equals(selectValue, so.getValue())) {
                        selectLabel = so.getLabel();
                        break;
                    }
                }
            }
        }
        params.put("selectLabel", selectLabel);
        params.put("selectValue", selectValue);
        params.put("optionList", valueList);
        logger.debug("------->{}valueList : ", valueList.size());
        logger.debug("selectButton options size: {}", valueList.size());
//        setDefault();
    }

    private void setDefault() {

        if (StringUtils.isBlank(selectLabel)) {
            selectValue = "0";
            for(SelectOption sp : valueList) {
                if (selectValue.equals(sp.getValue())) {
                    selectLabel = sp.getLabel();
                }
            }
        }
    }

    private void clear() {
        selectValue = "0";
        selectLabel = "";
        valueList = Lists.newArrayList();

    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {



    }



    private void setSelectOption(Map<String, Dictionary> dictMap) {

        //转换option同时设置默认选中内容
        if (StringUtils.isBlank(selectValue)) {
            selectValue = "0";
        }
        for (Map.Entry<String, Dictionary> entry : dictMap.entrySet()) {
            SelectOption option = dictToSelectOption(entry.getValue());
            if (StringUtils.endsWithIgnoreCase(entry.getValue().getSort(), selectValue)) {
                selectLabel = option.getLabel();
            }
            valueList.add(option);
        }
        Collections.sort(valueList, new Comparator<SelectOption>() {
            @Override
            public int compare(SelectOption o1, SelectOption o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    private SelectOption dictToSelectOption(Dictionary dict) {
        SelectOption option = new SelectOption();
        option.setLabel(I18N.getLangValue(UserUtil.getCurrentLocale(), dict.getDataKey(), dict.getDescription()));
        option.setValue(dict.getDataCode());
        return option;
    }


}
