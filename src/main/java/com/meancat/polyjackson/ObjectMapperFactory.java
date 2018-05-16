package com.meancat.polyjackson;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.meancat.polyjackson.messages.CustomRequest;
import com.meancat.polyjackson.messages.CustomResponse;
import org.reflections.Reflections;

import java.util.Set;

public class ObjectMapperFactory {

    private ObjectMapper objectMapper;

    public ObjectMapperFactory(Reflections reflections) {
        objectMapper = new ObjectMapper();
        objectMapper.disableDefaultTyping();

        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        registerSubtypes(objectMapper, reflections.getTypesAnnotatedWith(CustomRequest.class));
        registerSubtypes(objectMapper, reflections.getTypesAnnotatedWith(CustomResponse.class));
    }

    public ObjectMapper create() {
        return objectMapper;
    }

    private void registerSubtypes(ObjectMapper objectMapper, Set<Class<?>> customTypes) {
        for(Class<?> clazz : customTypes) {
            JsonTypeName typeInfo = clazz.getAnnotation(JsonTypeName.class);
            if (typeInfo == null) {
                //logger.debug("Registering subtype {}", clazz);
                objectMapper.registerSubtypes(clazz);
            } else {
                //logger.debug("Registering subtype {}", typeInfo.value());
                objectMapper.registerSubtypes(new NamedType(clazz, typeInfo.value()));
            }
        }
    }
}
