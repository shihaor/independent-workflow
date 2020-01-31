package com.sdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 类功能描述：jackson工具类
 *
 * @author zyl
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static SimpleFilterProvider filterProvider = new SimpleFilterProvider();

    private static SimpleBeanPropertyFilter beanPropertyFilter;

    private JsonUtil() {

    }

    /**
     * 将json数据反序列化带泛型的对象
     *
     * @param json         json数据
     * @param valueTypeRef TypeReference
     * @param <T>          反序列化bean泛型
     * @return 反序列化Bean
     * @throws IOException 当json数据反序列化带泛型的对象失败时抛出异常
     */
    public static <T> T genObjectFromJson(String json, TypeReference<T> valueTypeRef) throws IOException {
        return objectMapper.readValue(json, valueTypeRef);

    }

    /**
     * 将数据转为json格式String
     *
     * @param results 数据Bean
     * @return json格式String
     * @throws Exception 当转换json失败时抛出异常
     */
    public static String genJsonSuccess(Object results) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", true);
        map.put("msg", "ok");
        map.put("data", results);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 将数据转为json格式String
     *
     * @return json格式String
     * @throws Exception 当转换json失败时抛出异常
     */
    public static String genJsonSuccess(String msg) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", true);
        map.put("msg", msg);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 将数据转为json格式String
     *
     * @param results  数据Bean
     * @param excludes 转换json需忽略的字段列表
     * @return json格式String
     * @throws Exception 当转换json失败时抛出异常
     */
    public static String genJsonSuccess(Object results, String[] excludes) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", true);
        map.put("msg", "成功!");
        map.put("data", results);
        beanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(excludes);
        filterProvider.setDefaultFilter(beanPropertyFilter);
        objectMapper.addMixIn(LinkedHashMap.class, EimsFilter.class);
        String json = objectMapper.writer(filterProvider).writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 返回通用的分页list数据
     *
     * @param list
     * @param toalNumber
     * @param startPage
     * @param limit
     * @param excludes
     * @return
     * @throws Exception
     */
    public static String genJsonFromList(List list, long toalNumber, int startPage, int limit, String[] excludes) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> dataObject = new LinkedHashMap<>();
        dataObject.put("list", list);
        dataObject.put("totalNumber", toalNumber);
        dataObject.put("rowCount", limit);
        dataObject.put("nowPage", startPage);
        map.put("success", true);
        map.put("msg", "成功!");
        map.put("data", dataObject);
        beanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(excludes);
        filterProvider.setDefaultFilter(beanPropertyFilter);
        objectMapper.addMixIn(LinkedHashMap.class, EimsFilter.class);
        String json = objectMapper.writer(filterProvider).writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 返回通用的分页list数据 不带exclude
     *
     * @param list        待转换的List
     * @param totalNumber 查询出的总数
     * @param startPage   开始页数
     * @param limit       页面显示数量
     * @return 返回转换后的JSON串
     * @throws Exception JSON转换异常
     */
    public static String genJsonFromList(List list, long totalNumber, int startPage, int limit) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> dataObject = new LinkedHashMap<>();
        dataObject.put("list", list);
        dataObject.put("totalNumber", totalNumber);
        dataObject.put("rowCount", limit);
        dataObject.put("nowPage", startPage);
        map.put("success", true);
        map.put("msg", "成功!");
        map.put("data", dataObject);
        beanPropertyFilter = SimpleBeanPropertyFilter.serializeAll();
        filterProvider.setDefaultFilter(beanPropertyFilter);
        objectMapper.addMixIn(LinkedHashMap.class, EimsFilter.class);
        String json = objectMapper.writer(filterProvider).writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 返回通用的分页list数据
     *
     * @param list
     * @param totalNumber
     * @return
     * @throws Exception
     */
    public static String genJsonFromList(List list, long totalNumber) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> dataObject = new LinkedHashMap<>();
        dataObject.put("list", list);
        dataObject.put("totalNumber", totalNumber);
        map.put("success", true);
        map.put("msg", "成功!");
        map.put("data", dataObject);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 将数据转为json格式String
     *
     * @param errNo  错误码
     * @param errMsg 错误信息
     * @return json格式String
     * @throws Exception 当转换json失败时抛出异常
     */
    public static String genJsonFail(int errNo, String errMsg) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", false);
        map.put("msg", "错误码为：" + errNo + "错误信息：" + errMsg);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 将数据转为json格式String
     *
     * @param baseResults 数据Bean
     * @return json格式String
     * @throws Exception 当转换json失败时抛出异常
     */
    public static String genJsonFromObject(Object baseResults) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String json = "";
        if (null != baseResults) {
            map.put("success", true);
            map.put("msg", "成功!");
            map.put("data", baseResults);
        } else {
            map.put("success", false);
            map.put("msg", "失败!");
            map.put("data", baseResults);
        }
        json = objectMapper.writeValueAsString(map);
        return json;
    }

    /**
     * 返回通用的list数据
     *
     * @param list
     * @return 字符串
     * @throws Exception
     */
    public static String genJsonFromList(List list) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", true);
        map.put("msg", "成功!");
        map.put("data", list);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    /**
     * 将一个list直接转为JSON串
     *
     * @param list 待转的list集合
     * @return 返回转换成功的JSON串
     * @throws JsonProcessingException JSON转换异常
     */
    public static String genJsonList(List list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }

    public static String genJsonFail(String msg) throws Exception {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("success", false);
        map.put("msg", msg);
        String json = objectMapper.writeValueAsString(map);
        map.clear();
        return json;
    }

    //@JsonFilter("eimsFilter")
    private static class EimsFilter {

    }
}
