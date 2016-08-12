package mybatis.framework.core.service;

import mybatis.framework.core.model.IValueObject;

/**
 * Created by bei2love@gmail.com on 15/9/10.
 */
public interface ITreeObjectService<T extends IValueObject> extends IValueObjectService<T> {

    public void saveTreeEntity(T entity);


}
