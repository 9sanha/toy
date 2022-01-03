package com.scope.toy.service;


import com.scope.toy.domain.User;
import com.scope.toy.dto.UserRequestDto;
import com.scope.toy.dto.UserSigninDto;
import com.scope.toy.repository.UserRepository;
import com.scope.toy.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void duplicateUser(UserRequestDto userRequestDto) {
        duplicateId(userRequestDto.getUserId());
        duplicateNickname(userRequestDto.getNickname());
        duplicateNumber(userRequestDto.getPhoneNumber());
    }

    public User saveUser(UserRequestDto userRequestDto){
        User user = new User(userRequestDto);
        return userRepository.save(user);
    }

    private void duplicateId(String id){
        if (userRepository.findByUserId(id).isPresent()){
            throw new IllegalArgumentException("아이디 중복");
        }
    }

    private void duplicateNickname(String nickname){
        if(userRepository.findByNickname(nickname).isPresent())
                throw new IllegalArgumentException("닉네임 중복");
    }

    private void duplicateNumber(String number){
        if (userRepository.findByPhoneNumber(number).isPresent())
                throw new IllegalArgumentException("전화번호 중복");
    }

    public void userSignin(UserSigninDto userSigninDto) {
        userRepository.findByUserId(userSigninDto.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 아이디")
        );
    }

    public String getJwtToken(String userId) {
        return jwtTokenProvider.createJwtToken(userId);
    }
}

