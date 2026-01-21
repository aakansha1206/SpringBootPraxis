package com.ambermount.warehouse.order_service.exception;

public class NotFoundException extends RuntimeException {

     public NotFoundException(String message)
    {
        super(message);
    }
}
