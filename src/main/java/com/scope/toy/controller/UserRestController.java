
package com.scope.toy.controller;


import com.scope.toy.domain.User;
import com.scope.toy.dto.ResponseDto;
import com.scope.toy.dto.UserRequestDto;
import com.scope.toy.dto.UserResponseDto;
import com.scope.toy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    ResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        boolean userJoinSuccess = userService.join(user);
        return new ResponseDto(new UserResponseDto(user), userJoinSuccess ? "Signup succeeded" : "Signup failed");
    }
}
