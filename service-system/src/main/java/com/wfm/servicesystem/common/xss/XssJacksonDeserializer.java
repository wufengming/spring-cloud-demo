package com.wfm.servicesystem.common.xss;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

/**
 * description: XssJacksonDeserializer
 * date: 2019-11-29 16:36
 * author: wfm
 * version: 1.0
 */
public class XssJacksonDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return StringEscapeUtils.escapeHtml4(jsonParser.getText());
    }
}
