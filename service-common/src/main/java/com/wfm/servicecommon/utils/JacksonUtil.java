package com.wfm.servicecommon.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * description: Jackson 对象与json互转工具类
 * date: 2019-12-18 14:27
 *
 * @author: wfm
 * @version: 1.0
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String BeanToJson(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T JsonToBean(String jsonStr, Class<T> objClass) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }
}
