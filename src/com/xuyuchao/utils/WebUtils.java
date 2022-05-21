package com.xuyuchao.utils;

import com.xuyuchao.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 此处也可将参数Map value换成HttpServletRequest req
 * 继而通过req.etParameterMap()得到参数map，并注入到user对象中
 * @auther xuyuchao
 * @create 2022-03-31-18:37
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            //法一：由工具类BeanUtils的populate(),通过req.getParameterMap()得到参数map，并注入到user对象中
            //BeanUtils.populate(bean,req.etParameterMap());

            //法二：map在dao，service，web三层均适用，改善耦合度
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将信息注入之后返回给servlet
        return bean;
    }
}
