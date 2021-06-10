package com.FirstSpringBoot.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(Exception message){
        super(message);
    }
}
