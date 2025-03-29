package com.ilisi.Ecommerce.utils;

import org.springframework.http.HttpStatus;

public record Response(boolean flag, HttpStatus status , String message, Object object) {
}
