package com.fx.util;

import com.fx.common.model.Dictionary;
import com.fx.freemarker.tags.SelectOption;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/9/11.
 */
public class DictUtil {

    /**
     * 获取指定key的字典设置，并转换成SelectOption数组
     * @param key
     * @return
     */
    public static List<SelectOption> getSelectOptionByDictKey(String key) {
        Map<String, Dictionary> dataScopeMap = CacheMgr.getDictCache(key);
        List<SelectOption> optionList = Lists.newArrayList();
        for(Map.Entry<String, Dictionary> entry : dataScopeMap.entrySet()){
            Dictionary dict = entry.getValue();
            SelectOption option = new SelectOption();
            option.setValue(dict.getDataCode());
            option.setLabel(I18N.getLangValue(UserUtil.getCurrentLocale(), dict.getDataKey(), dict.getDescription()));
            optionList.add(option);
        }
        return optionList;
    }
}
