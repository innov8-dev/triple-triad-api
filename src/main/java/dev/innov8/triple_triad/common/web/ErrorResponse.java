package dev.innov8.triple_triad.common.web;

import java.time.LocalDateTime;

public class ErrorResponse implements ResourceResponse {

    private int statusCode;
    private String message;
    private String responseTime;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.responseTime = LocalDateTime.now().toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", responseTime='" + responseTime + '\'' +
                '}';
    }

}
