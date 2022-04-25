package com.focusts.rtv.api.service;

import com.focusts.rtv.api.model.User;
import com.focusts.rtv.api.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list(){
        return this.userRepository.findAll();
    }

    public Optional<User> findById(Long code) {
        return this.userRepository.findById(code);
    }

}
