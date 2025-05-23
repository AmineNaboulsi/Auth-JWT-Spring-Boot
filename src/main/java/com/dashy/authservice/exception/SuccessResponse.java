package com.dashy.authservice.exception;

public record SuccessResponse<T>(String message, T data) {
}
