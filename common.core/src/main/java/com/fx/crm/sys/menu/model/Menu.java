package com.fx.crm.sys.menu.model;

import com.fx.crm.sys.permission.model.Permission;
import com.google.gson.annotations.Expose;
import mybatis.framework.core.model.BaseValueObject;

import java.util.List;

public class Menu extends BaseValueObject {

    /**
     * @Expose属性用于输出json字符串
     */
    @Expose
    private String menuid;

    @Expose
    private String menuname;

    @Expose
    private String menucode;

    @Expose
    private String menuuri;

    @Expose
    private String parentmenu;

    @Expose
    private Integer menulevel;

    @Expose
    private String menuorder;

    @Expose
    private List<Menu> childMenu;

    private Menu parentMenu;

    @Expose
    private List<Permission> permissions;

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode == null ? null : menucode.trim();
    }

    public String getMenuuri() {
        return menuuri;
    }

    public void setMenuuri(String menuuri) {
        this.menuuri = menuuri == null ? null : menuuri.trim();
    }

    public String getParentmenu() {
        return parentmenu;
    }

    public void setParentmenu(String parentmenu) {
        this.parentmenu = parentmenu == null ? null : parentmenu.trim();
    }

    public Integer getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(Integer menulevel) {
        this.menulevel = menulevel;
    }

    public String getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(String menuorder) {
        this.menuorder = menuorder == null ? null : menuorder.trim();
    }
}