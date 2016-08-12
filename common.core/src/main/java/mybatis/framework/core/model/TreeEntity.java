package mybatis.framework.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fx.util.ReflectUtil;
import com.fx.util.StringUtils;

/**
 * Created by bei2love@gmail.com on 15/9/10.
 */
public abstract class TreeEntity<T> extends BaseValueObject<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 如果当前表主键不是id，需要手动设置
     */
    protected Integer id;
    protected T parent;	// 父级编号
    protected String parentIds; // 所有父级编号
    protected String parentId;
    protected String name; 	// 机构名称
    protected Integer sort;		// 排序

//    public TreeEntity() {
//        super();
//        this.sort = 30;
//    }
//
//    public TreeEntity(String id) {
//        super(id);
//    }


    /**
     * 是否是新记录（默认:false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    protected boolean isNewRecord = false;

    public TreeEntity() {

    }

    public TreeEntity(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isNewRecord() {
        return isNewRecord || id == 0;
    }

    public void setNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     * @return
     */
    @JsonBackReference
//    @NotNull
    public abstract T getParent();

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     * @return
     */
    public abstract void setParent(T parent);

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        String id = null;
        if (parent != null){
            id = (String) ReflectUtil.getFieldValue(parent, "id");
        }
        return StringUtils.isNotBlank(id) ? id : "0";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
