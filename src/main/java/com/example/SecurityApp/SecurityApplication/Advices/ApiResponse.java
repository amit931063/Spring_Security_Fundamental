package com.example.SecurityApp.SecurityApplication.Advices;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse <T>{
    private LocalDateTime timestamp;
    private  T data;
    private  ApiError error;

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
