package com.project.moneymanager.dto;/*
 * @author gauravverma
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {
    private String name;

    private String email;

    private String password;

    private Long age = 0L;
}
