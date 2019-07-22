package com.fehead.initialize.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Json和Object转化的工具类
 *
 * @author lmwis
 */
public class JsonUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     * @param obj 要转化的对象
     * @return 转化后的字符串
     * @throws JsonProcessingException json异常
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        String jsonstr = "";
        jsonstr = mapper.writeValueAsString(obj);
        return jsonstr;
    }

    /**
     * 将json字符串转化为原有对象
     * @param jsonstr 要转化的字符串
     * @param valueType 要转化成的对象的class对象
     * @param <T> 类型
     * @return 转化后的对象
     * @throws IOException io异常
     */
    public static <T> T jsonToObject(String jsonstr,Class<T> valueType) throws IOException {
        Object obj = mapper.readValue(jsonstr,valueType);
        return (T) obj;
    }

    /**
     * 将json对象转化为list集合
     * @param jsonstr 要转化的字符串
     * @param valueType 要转化成的对象的class对象
     * @param <T> 类型
     * @return 转化后的范形集合
     * @throws IOException io异常
     */
    public static <T>  List<T> jsonToList(String jsonstr,Class<T> valueType) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,valueType);
        List<T> objList = mapper.readValue(jsonstr,javaType);
        return  objList;
    }
}
