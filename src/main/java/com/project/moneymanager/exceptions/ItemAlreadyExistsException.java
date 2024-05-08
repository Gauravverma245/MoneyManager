package com.project.moneymanager.exceptions;/*
 * @author gauravverma
 */

public class ItemAlreadyExistsException extends RuntimeException{
    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
