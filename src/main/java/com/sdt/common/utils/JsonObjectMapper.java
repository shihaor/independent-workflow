package com.sdt.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * json序列化将null转化为空字符串
 *
 * @author wqs
 * @date 2019-10-31 14:23.
 */
public class JsonObjectMapper extends ObjectMapper {

    public JsonObjectMapper() {
        super();
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
                arg1.writeString("");
            }
        });
    }
}
