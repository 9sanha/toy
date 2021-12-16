package com.scope.toy.dto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String nickname;

    private String userImgUrl;
}
