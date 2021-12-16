package com.scope.toy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class PostRequestDto {
    String title;
    String content;
    String nickname;// or Long id;
    String postImgUrl;
    String postVideoUrl;
}
