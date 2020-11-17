package com.apray.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {

    }
    public NotFoundException(String s){
        super(s);
    }
    public NotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
