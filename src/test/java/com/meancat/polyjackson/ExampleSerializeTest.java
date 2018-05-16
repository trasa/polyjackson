package com.meancat.polyjackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meancat.polyjackson.messages.ExampleRequest;
import com.meancat.polyjackson.messages.GameMessage;
import com.meancat.polyjackson.messages.GameMessageHeader;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class ExampleSerializeTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        Reflections reflections = new ReflectionsFactory().create();
        objectMapper = new ObjectMapperFactory(reflections).create();
    }

    @Test
    public void serializeExampleRequest() throws JsonProcessingException {
        ExampleRequest request = new ExampleRequest();
        request.setName("foo");
        request.setNumber(12345L);
        GameMessage msg = new GameMessage();
        msg.setHeader(new GameMessageHeader("headerId"));
        msg.setBody(request);

        String s = objectMapper.writeValueAsString(msg);
        System.out.println(s);
    }

    @Test
    public void deserializeExampleRequest() throws IOException {
        GameMessage msg = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("exampleRequest.json"), GameMessage.class);

        assertEquals("headerId", msg.getHeader().getMessageId());
        ExampleRequest request = (ExampleRequest) msg.getBody();
        assertEquals("foo", request.getName());
        assertEquals(12345L, request.getNumber());
    }
}
