package com.wfm.servicesystem.common.xss;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * description: XssJacksonSerializer
 * date: 2019-11-29 16:25
 * author: wfm
 * version: 1.0
 */
public class XssJacksonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeString(StringEscapeUtils.escapeHtml4(s));
    }
}