package mybatis.framework.core.service;

import com.fx.util.ReflectUtil;
import com.fx.util.StringUtils;
import mybatis.framework.core.dao.TreeDao;
import mybatis.framework.core.model.TreeEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 树形结构Sercice抽象
 * Created by bei2love@gmail.com on 15/9/10.
 */
public class BaseTreeService<D extends TreeDao<T>,T extends TreeEntity> extends BaseVOService<T> implements ITreeObjectService<T>{

    @Autowired
    protected D dao;

    @Override
    protected D getDao() {
        return dao;
    }

    public void saveTreeEntity(T entity) {

        @SuppressWarnings("unchecked")
        Class<T> entityClass = ReflectUtil.getClassGenricType(getClass(), 1);

        // 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
        if (StringUtils.isBlank(entity.getParentId())
                || "0".equals(entity.getParentId())){
            entity.setParent(null);
        }else{
            entity.setParent(super.findById(entity.getParentId()));
        }
        T parentEntity = null;
        if (entity.getParent() == null){
            try {
                parentEntity = entityClass.getConstructor(Integer.class).newInstance(0);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
            parentEntity.setParentIds(StringUtils.EMPTY);
            entity.setParent(parentEntity);
        }

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = entity.getParentIds();

        // 设置新的父节点串
        parentEntity = (T) entity.getParent();

        entity.setParentIds(parentEntity.getParentIds() + parentEntity.getId()+",");

        // 保存或更新实体
        insertOrUpdate(entity);

        // 更新子节点 parentIds
        T o = null;
        try {
            o = entityClass.newInstance();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        o.setParentIds("%,"+entity.getId()+",%");
        List<T> list = dao.findByParentIdsLike(o);
        for (T e : list){
            if (e.getParentIds() != null && oldParentIds != null){
                e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
                preUpdateChild(entity, e);
                dao.updateParentIds(e);
            }
        }

    }

    private void insertOrUpdate(T entity) {
        if (entity.isNewRecord()) {
            doInsert(entity);
        }else {
            doUpdateById(entity);
        }
    }

    /**
     * 预留接口，用户更新子节前调用
     * @param childEntity
     */
    protected void preUpdateChild(T entity, T childEntity) {

    }


}
