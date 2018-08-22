package com.meancat.polyjackson.messages;

/**
 * An Example Request.
 */
@CustomRequest
public class ExampleRequest extends Request {
    private String name;
    private long number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
