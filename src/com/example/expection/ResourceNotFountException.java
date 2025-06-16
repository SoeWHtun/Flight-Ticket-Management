package com.example.expection;

public class ResourceNotFountException extends RuntimeException {
    public ResourceNotFountException(String msg){
        super(msg);
    }
}
