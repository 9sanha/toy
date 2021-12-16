package com.scope.toy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto {
    Object object;
    String msg;
}
