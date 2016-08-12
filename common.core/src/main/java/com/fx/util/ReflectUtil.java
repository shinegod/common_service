package com.fx.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.Map;

/**
 * 利用反射进行操作的一个工具类
 *
 */
public class ReflectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);


    /**
     * 通过构造函数实例化对象
     * @param className       类的全路径名称
     * @param parameterTypes  参数类型
     * @param initargs        参数值
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object constructorNewInstance(String className,Class [] parameterTypes,Object[] initargs) {
        try {
            Constructor<?> constructor = (Constructor<?>) Class
                    .forName(className).getDeclaredConstructor(parameterTypes);					    //暴力反射
            constructor.setAccessible(true);
            return constructor.newInstance(initargs);
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }


//    /**
//     * 暴力反射获取字段值
//     * @param propertyName 属性名
//     * @param obj       实例对象
//     * @return          属性值
//     */
//    public static Object getFieldValue(String propertyName, Object obj) {
//        try {
//            Field field = obj.getClass().getDeclaredField(propertyName);
//            field.setAccessible(true);
//            return field.get(obj);
//        } catch (Exception ex) {
//            throw new RuntimeException();
//        }
//    }

    /**
     * 暴力反射获取字段值
     * @param propertyName 属性名
     * @param object       实例对象
     * @return          字段值
     */
    public static Object getProperty(String propertyName, Object object) {
        try {

            PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(object);

            //其它方式
        /*BeanInfo beanInfo =  Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        Object retVal = null;
        for(PropertyDescriptor pd : pds){
            if(pd.getName().equals(propertyName))
            {
                Method methodGetX = pd.getReadMethod();
                retVal = methodGetX.invoke(object);
                break;
            }
        }
        return retVal;*/
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的,它支持属性连缀操作:如,.对象.属性
     * @param propertyName 属性名
     * @param object       实例对象
     * @return          字段值
     */
    public static Object getBeanInfoProperty(String propertyName, Object object) {
        try {
            return BeanUtils.getProperty(object, propertyName);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的
     * @param object       实例对象
     * @param propertyName 属性名
     * @param value        字段值
     * @return
     */
    public static void setBeanInfoProperty(Object object,String propertyName,String value) {
        try {
            BeanUtils.setProperty(object, propertyName,value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型
     * @param propertyName 属性名
     * @param object       实例对象
     * @return          字段值
     */
    public static Object getPropertyUtilByName(String propertyName, Object object) {
        try {
            return PropertyUtils.getProperty(object, propertyName);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型,这是PropertyUtils与BeanUtils的根本区别
     * @param object       实例对象
     * @param propertyName 属性名
     * @param value        字段值
     * @return
     */
    public static void setPropertyUtilByName(Object object,String propertyName,Object value) {
        try {
            PropertyUtils.setProperty(object, propertyName,value);
        } catch (Exception ex) {
            logger.error("设置{}的属性{}时出错，值为:{}", object.getClass(), propertyName, value);
            throw new RuntimeException();
        }
    }

    /**
     * 设置字段值
     * @param object          实例对象
     * @param propertyName 属性名
     * @param value        新的字段值
     * @return
     */
    public static void setProperties(Object object, String propertyName,Object value) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());
        Method methodSet = pd.getWriteMethod();
        logger.debug("-------------->>>{} ,setProperties,{},{}",object.getClass(),propertyName, value);
        methodSet.invoke(object,value);
    }


    /**
     * 设置字段值
     * @param propertyName 字段名
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    public static void setFieldValue(Object obj,String propertyName,Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 设置字段值
     * @param className        类的全路径名称
     * @param methodName       调用方法名
     * @param parameterTypes   参数类型
     * @param values           参数值
     * @param object           实例对象
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object methodInvoke(String className,String methodName,Class [] parameterTypes,Object [] values,Object object) {
        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName,parameterTypes);
            method.setAccessible(true);
            return method.invoke(object,values);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * @param <T> 具体对象
     * @param fileds  要进行比较Bean对象的属性值集合(以属性值为key,属性注释为value,集合从数据库中取出)
     * @param oldBean  源对象
     * @param newBean  新对象
     * @return 返回二个Bean对象属性值的异同
     */
    public static <T> String compareBeanValue(Map<String,String> fileds,T oldBean,T newBean){

        StringBuilder compares = new StringBuilder();
        String propertyName = null;
        Object oldPropertyValue = null;
        Object newPropertyValue = null;

        StringBuilder descrips = new StringBuilder();
        for(Map.Entry<String, String> entity : fileds.entrySet()){
            //获取新旧二个对象对应的值
            propertyName = entity.getKey().toLowerCase();
            oldPropertyValue = getProperty(propertyName, oldBean);
            newPropertyValue = getProperty(propertyName, newBean);

            if(null == oldPropertyValue && null == newPropertyValue){
                continue;
            }
            if("".equals(oldPropertyValue) && "".equals(newPropertyValue)){
                continue;
            }
            if(null == oldPropertyValue){
                oldPropertyValue = "";
            }
            if(null == newPropertyValue){
                newPropertyValue = "";
            }

            if(oldPropertyValue.equals(newPropertyValue)){
                continue;
            }
            compares.append("字段注释: ").append(entity.getValue()).append("】").append("原属性值\"");
            if(StringUtils.isEmpty(oldPropertyValue+"")){
                oldPropertyValue = " ";
            }
            compares.append(oldPropertyValue).append("\"现属性值\"");
            if(StringUtils.isEmpty(newPropertyValue+"")){
                newPropertyValue = " ";
            }
            compares.append(newPropertyValue).append("\";");
        }
        return compares.toString();
    }



    /**
     * 利用反射获取指定对象的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                if(field.getType() == Integer.class || field.getType() == int.class){
                    field.set(obj, Integer.valueOf(fieldValue));
                }else {
                    field.set(obj, fieldValue);
                }
            } catch (IllegalArgumentException e) {
                logger.debug("fieldName:{}, fieldValue:{} ,{}", fieldName, fieldValue, e);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                logger.debug("fieldName:{}, fieldValue:{} ,{}", fieldName, fieldValue, e);
                e.printStackTrace();
            }
        }
    }

//    /**
//     * 利用反射设置指定对象的指定属性为指定的值
//     * @param obj 目标对象
//     * @param fieldName 目标属性
//     * @param fieldValue 目标值
//     */
//    public static void setFieldValue(Object obj, String fieldName,
//                                     Object fieldValue) {
//        Field field = ReflectUtil.getField(obj, fieldName);
//        if (field != null) {
//            try {
//                field.setAccessible(true);
//                field.set(obj, fieldValue);
//            } catch (IllegalArgumentException e) {
//                logger.debug("fieldName:{}, fieldValue:{} ,{}", fieldName, fieldValue, e);
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                logger.debug("fieldName:{}, fieldValue:{} ,{}", fieldName, fieldValue, e);
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
     * 如无法找到, 返回Object.class.
     *
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class getClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    public static Class<?> getFieldType(Object obj, String fieldName) {
        Object objInvoke = obj;
        Field field = ReflectUtil.getField(objInvoke, fieldName);
        if (field == null) {
            logger.debug("对象{}无{}属性",obj, fieldName);
            return null;
        }
        return field.getType();
    }
}