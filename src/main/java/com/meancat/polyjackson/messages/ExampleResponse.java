package com.meancat.polyjackson.messages;

/**
 * An Example Response
 */
@CustomResponse
public class ExampleResponse extends Response {

    private Result result;

    private long timestamp;


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public enum Result {
        OK,
        FAIL,
        TRY_LATER
    }
}
