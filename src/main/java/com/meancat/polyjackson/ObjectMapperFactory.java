package com.meancat.polyjackson;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.meancat.polyjackson.messages.CustomRequest;
import com.meancat.polyjackson.messages.CustomResponse;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Construct an ObjectMapper which is configured to understand
 * polymorphic message types that are discovered at run time.
 */
public class ObjectMapperFactory {

    private ObjectMapper objectMapper;

    public ObjectMapperFactory(Reflections reflections) {
        objectMapper = new ObjectMapper();
        objectMapper.disableDefaultTyping();

        // just some handy settings ...
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // find all the CustomRequests and register the subtypes
        registerSubtypes(objectMapper, reflections.getTypesAnnotatedWith(CustomRequest.class));
        // find all the CustomResponses and register the subtypes
        registerSubtypes(objectMapper, reflections.getTypesAnnotatedWith(CustomResponse.class));
    }

    public ObjectMapper create() {
        return objectMapper;
    }

    /**
     * Register the class information for each of the custom types with the
     * object mapper so that Jackson will know how to deserialize it.
     *
     * @param objectMapper to register with
     * @param customTypes to be registered
     */
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
