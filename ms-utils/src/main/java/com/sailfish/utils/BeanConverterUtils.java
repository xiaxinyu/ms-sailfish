package com.sailfish.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean Converter Utils
 *
 * @author XIAXINYU3
 * @date 2019.8.19
 */
public class BeanConverterUtils {

    public static <T> T convert(final Object obj, Class<T> targetClass) throws InstantiationException, IllegalAccessException {
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(obj, t);
            return t;
        } catch (InstantiationException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        }
    }

    public static <T> List convertList(List list, Class<T> targetClass) throws IllegalAccessException, InstantiationException {
        List<T> newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            T t = convert(list.get(i), targetClass);
            newList.add(t);
        }
        return newList;
    }
}
