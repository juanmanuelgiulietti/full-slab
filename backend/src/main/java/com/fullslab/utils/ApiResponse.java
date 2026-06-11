package com.fullslab.utils;

public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;
    private long timestamp;

    public ApiResponse(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public long getTimestamp() { return timestamp; }
}