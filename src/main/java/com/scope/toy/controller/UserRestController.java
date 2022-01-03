package com.scope.toy.controller;

import com.scope.toy.domain.User;
import com.scope.toy.dto.*;
import com.scope.toy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;
    //회원가입
    @PostMapping("/api/user/signup")
    ResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        //중복검사
        userService.duplicateUser(userRequestDto);
        //저장
        User user = userService.saveUser(userRequestDto);
        return new ResponseDto(user, "회원가입 완료");
    }

    //로그인
    @PostMapping("/api/user/signin")
    ResponseDto signin(@RequestBody UserSigninDto userSigninDto){
        userService.userSignin(userSigninDto);
        JwtTokenDto token = new JwtTokenDto(userService.getJwtToken(userSigninDto.getUserId()));
        return new ResponseDto(token,"로그인 성공");
    }

//    @GetMapping("/api/user/self")
//    ResponseDto getUser(@)
}
