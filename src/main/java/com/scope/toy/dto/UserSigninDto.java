package com.scope.toy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class UserSigninDto {
    @NotNull
    private String userId;
    @NotNull
    private String password;

}
