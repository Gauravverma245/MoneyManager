package com.project.moneymanager.dto;/*
 * @author gauravverma
 */

import lombok.Data;


@Data
public class UserDto {
    private String name;

    private String email;

    private String password;

    private Long age = 0L;
}
