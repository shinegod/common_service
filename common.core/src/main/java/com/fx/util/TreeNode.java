package com.fx.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点的定义
 * Created by bei2love@gmail.com on 15/9/10.
 */
public class TreeNode<T> implements Serializable {

    public enum Type{
        ORG("o","部门"),
        ROLE("r","角色"),
        USER("u", "用户"),
        IB_USER("i", "IB用户"),
        CUST_USER("c", "终端用户");

        String value;
        String text;
        Type(String value, String text){
            this.value = value;
            this.text = text;
        }

        public String getValue(){
            return this.value;
        }

        public String getText(){
            return this.text;
        }

        protected static Type valueOfType(String value){
            for(Type eValue : Type.values()){
                if(eValue.getValue().equals(value)){
                    return eValue;
                }
            }
            return null;
        }
    }


    private List<TreeNode<?>> children;

    private String icon;

    private String iconClose;

    private String iconOpen;

    private String iconSkin;

    private boolean isParent;

    private String id;

    private String sid;

    private String name;

    private String parentId;

    private String target;

    private String url;

    private String click;

    private String type;

    private T extend;

    private TreeNode<?> parentNode;

    private boolean leaf;

    private boolean checked;

    private boolean nocheck;

    private int orgId;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public TreeNode<?> getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode<?> parentNode) {
        this.parentNode = parentNode;
    }

    public List<TreeNode<?>> getChildren() {
        return children;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconClose() {
        return iconClose;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    @JsonProperty("isParent")
    public boolean isParent() {
        return isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTarget() {
        return target;
    }

    public String getUrl() {
        return url;
    }

    public String getClick() {
        return click;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public T getExtend() {
        return extend;
    }

    public void setChildren(List<TreeNode<?>> children) {
        this.children = children;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public void setExtend(T extend) {
        this.extend = extend;
    }
}
