package com.tribeofspirit.common.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tribeofspirit.common.enums.TitleEnum;

import java.io.IOException;

/**
 * User: Lewis Wang
 * Date: 2/22/12
 * Time: 1:04 PM
 */
public class TitleEnumJsonSerializer extends JsonSerializer<Enum> {

    @Override
        public void serialize(Enum value, JsonGenerator generator,
                              SerializerProvider provider) throws IOException {

            TitleEnum titleEnum = (TitleEnum) value;
            generator.writeStartObject();
            generator.writeFieldName("title");
            generator.writeString(titleEnum.getTitle());
            generator.writeFieldName("_name");
            generator.writeString(value.name());
            generator.writeEndObject();
        }
    }