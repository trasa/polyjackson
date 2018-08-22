package com.meancat.polyjackson.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The Holder for polymorphic Body objects.
 *
 * GameMessage consists of a (plain, nonpolymorphic) header
 * and a body which can be of a type registered by scanning
 * for CustomRequests and CustomResponses.
 *
 */
public class GameMessage {

    private GameMessageHeader header;
    private Object body;

    public GameMessageHeader getHeader() {
        return header;
    }

    public void setHeader(GameMessageHeader header) {
        this.header = header;
    }


    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@messageType")
    // this declares all the sub types in code, which is nice but...
    // its more fun to do this at runtime at app-startup
//    @JsonSubTypes(value = {
//            @JsonSubTypes.Type(value = ExampleRequest.class)
//    })
    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
