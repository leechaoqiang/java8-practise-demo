package com.vincent.demo.utils;


import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.*;

/**
 * 带缓存的高性能反射工具类
 * @author vincent.li
 * @since 2019-05-29
 */
@Slf4j
public class ReflectASMUtils {

    private static Map<Class, MethodAccess> methodMap = new HashMap<>();

    private static Map<String, Integer> methodIndexMap = new HashMap<>();

    private static Map<Class, List<String>> classFieldMap = new HashMap<>();

    private static Map<String, Field> fieldMap = new HashMap<>();

    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }
        MethodAccess targetMethodAccess = methodMap.get(target.getClass());
        if (targetMethodAccess == null) {
            targetMethodAccess = cache(target);
        }
        MethodAccess sourceMethodAccess = methodMap.get(source.getClass());
        if (sourceMethodAccess == null) {
            sourceMethodAccess = cache(source);
        }

        List<String> fieldList = classFieldMap.get(source.getClass());
        for (String fieldName : fieldList) {
            String sourceField = source.getClass().getName() + "." + fieldName;
            String targetField = target.getClass().getName() + "." + fieldName;
            Field sField = fieldMap.get(sourceField);

            String getter = getterName(sField.getType().getName(), fieldName);
            String setter = "set" + fieldName;

            String sourceGetKey = source.getClass().getName() + "." + getter;
            String targetSetKey = target.getClass().getName() + "." + setter;

            Integer sourceGetIndex = methodIndexMap.get(sourceGetKey);
            Integer targetSetIndex = methodIndexMap.get(targetSetKey);
            if (targetSetIndex == null) {
                continue;
            }

            Type sourceType = fieldMap.get(sourceField).getGenericType();
            Type targetType = fieldMap.get(targetField).getGenericType();
            //字段名相同类型不同，一般为自定义类或者list泛型不同
            if (sourceType != targetType) {
                Object sourceObj = sourceMethodAccess.invoke(source, sourceGetIndex);
                if (sourceObj != null) {
                    try {
                        //带有泛型的类
                        if (sourceType instanceof ParameterizedType) {
                            //List转换
                            if (List.class.isAssignableFrom(sField.getType())) {
                                Class listClz = sField.getType();
                                Method m = listClz.getDeclaredMethod("size");
                                int size = (Integer) m.invoke(sourceObj);
                                Method getM = listClz.getDeclaredMethod("get", int.class);
                                if(!getM.isAccessible()){
                                    getM.setAccessible(true);
                                }
                                List<Object> tList = new ArrayList<>(size);
                                Class tClz = (Class) ((ParameterizedType) targetType).getActualTypeArguments()[0];
                                for (int i = 0; i < size; i++) {
                                    Object sObj = getM.invoke(sourceObj, i);
                                    Object tObj = tClz.newInstance();
                                    copyProperties(sObj, tObj);
                                    tList.add(tObj);
                                }
                                targetMethodAccess.invoke(target, targetSetIndex, tList);
                            }
                        } else { //不同类的转换，例如Bo、Dto
                            String targetGetKey = target.getClass().getName() + "." + getter;
                            Integer targetGetIndex = methodIndexMap.get(targetGetKey);
                            Object targetObj = targetMethodAccess.invoke(target, targetGetIndex);
                            if (targetObj == null) {
                                targetObj = fieldMap.get(targetField).getType().newInstance();
                                targetMethodAccess.invoke(target, targetSetIndex, targetObj);
                            }
                            copyProperties(sourceObj, targetObj);
                        }
                    } catch (Exception e) {
                        log.info("copyProperties错误", e);
                    }
                }
            } else {
                targetMethodAccess.invoke(target, targetSetIndex,
                        sourceMethodAccess.invoke(source, sourceGetIndex));
            }
        }
    }

    // 单例模式
    private static MethodAccess cache(Object obj) {
        synchronized (ReflectASMUtils.class) { //todo synchronized on obj.getClass() is better
            MethodAccess methodAccess = methodMap.get(obj.getClass());
            if (methodAccess != null) {
                return methodAccess;
            }
            methodAccess = MethodAccess.get(obj.getClass());
            Class tmp = obj.getClass();

            List<Field> allFields = new ArrayList<>();
            while (tmp != Object.class) {
                Field[] fields = tmp.getDeclaredFields();
                allFields.addAll(Arrays.asList(fields));
                tmp = tmp.getSuperclass();
            }

            List<String> fieldList = new ArrayList<>();
            for (Field field : allFields) {
                if (Modifier.isPrivate(field.getModifiers())
                        && !Modifier.isStatic(field.getModifiers())) { // 是否是私有的，是否是静态的
                    // 非公共私有变量
                    String fieldName = StringUtils.capitalize(field.getName()); // 获取属性名称
                    String getter = getterName(field.getType().getName(), fieldName);
                    String setter = "set" + fieldName;

                    int getIndex = methodAccess.getIndex(getter); // 获取get方法的下标
                    int setIndex = methodAccess.getIndex(setter); // 获取set方法的下标
                    methodIndexMap.put(obj.getClass().getName() + "." + getter, getIndex); // 将类名get方法名，方法下标注册到map中
                    methodIndexMap.put(obj.getClass().getName() + "." + setter, setIndex); // 将类名set方法名，方法下标注册到map中
                    fieldMap.put(obj.getClass().getName() + "." + fieldName, field);
                    fieldList.add(fieldName); // 将属性名称放入集合里
                }
            }
            classFieldMap.put(obj.getClass(), fieldList); // 将类名，属性名称注册到map中
            methodMap.put(obj.getClass(), methodAccess);
            return methodAccess;
        }
    }

    private static String getterName(String fieldType, String fieldName) {
        String getter;
        if ("boolean".equalsIgnoreCase(fieldType)) {
            getter = "is" + fieldName;
        } else {
            getter = "get" + fieldName;
        }
        return getter;
    }

}