package com.scope.toy.dto;


import com.scope.toy.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserResponseDto {
    private String accessToken;
    private String userId;
    private String nickname;
    private String phoneNumber;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
    }
}
