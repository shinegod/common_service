package com.fx.freemarker.tags;

import java.util.List;

/**
 * select组件和selectButton组件的选项
 * Created by bei2love@gmail.com on 15/9/12.
 */
public class SelectOption {

    private String value;

    private String label;

    private List<SelectOption> children;

    public List<SelectOption> getChildren() {
        return children;
    }

    public void setChildren(List<SelectOption> children) {
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
