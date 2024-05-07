package com.project.moneymanager.exceptions;/*
 * @author gauravverma
 */

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
