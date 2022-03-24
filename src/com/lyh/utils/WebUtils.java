package com.lyh.utils;

import com.lyh.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
/**
 * @Description 把map的值注入到javaBean属性中
 * @Param 
 * @return 
 **/
public class WebUtils {
    /*
     * <T>声明是泛型方法
     * T表示返回的是泛型
     */
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            //通过populate方法给bean对象注入获取的map参数值
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
