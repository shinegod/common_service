package mybatis.framework.core.dao;

import mybatis.framework.core.model.IValueObject;

import java.util.List;

/**
 * Created by bei2love@gmail.com on 15/9/10.
 */
public interface TreeDao<T extends IValueObject> extends IValueObjectDao<T> {
    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    public List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(T entity);

}
