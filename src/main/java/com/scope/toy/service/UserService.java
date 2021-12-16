package com.scope.toy.service;


import com.scope.toy.domain.User;
import com.scope.toy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public boolean join(User user) {
        if(duplicateUserExist(user)) return false;
        userRepository.save(user);
        return true;
    }

    private boolean duplicateUserExist(User user) {
        return userRepository.findByUserId(user.getUserId()).isPresent();
    }
}
