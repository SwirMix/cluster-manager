package org.cluster.dao;

public class Result {
    private Status status;
    private String message;

    public Result(){

    }

    public Result(Status status, String msg) {
        this.message = msg;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
