package com.scope.toy.controller.test;


import com.scope.toy.dto.JwtTokenDto;
import com.scope.toy.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class JwtTestController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/token")
    public JwtTokenDto createToken() {
        String tokenValue = jwtTokenProvider.createJwtToken(1L);
        JwtTokenDto tokenDto = new JwtTokenDto(tokenValue);
        return tokenDto;
    }

}
