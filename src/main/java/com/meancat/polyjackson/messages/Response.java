package com.meancat.polyjackson.messages;

public abstract class Response {
    private boolean successful = true;
    private String description = "";
    private boolean transientFailure = false;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTransientFailure() {
        return transientFailure;
    }

    public void setTransientFailure(boolean transientFailure) {
        this.transientFailure = transientFailure;
    }
}
