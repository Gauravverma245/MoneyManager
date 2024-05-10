package com.project.moneymanager.dto;/*
 * @author gauravverma
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String jwtToken;
}
