package com.project.moneymanager.model;/*
 * @author gauravverma
 */

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer StatusCode;
    private String message;
    private Date timeStamp;
}
