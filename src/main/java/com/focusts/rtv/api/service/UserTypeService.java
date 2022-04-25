package com.focusts.rtv.api.service;

import java.util.List;
import java.util.Optional;

import com.focusts.rtv.api.model.UserType;
import com.focusts.rtv.api.repository.UserTypeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {
    
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<UserType> list(){
        return this.userTypeRepository.findAll();
    }

    public Optional<UserType> findById(Long code) {
        return this.userTypeRepository.findById(code);
    }

    public UserType create(UserType userType){
        return this.userTypeRepository.save(userType);
    }

    public UserType update(Long code, UserType userType){
        UserType userTypeGet = this.findById(code).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(userType, userTypeGet, "id");
        return this.userTypeRepository.save(userTypeGet);
    }

    public void delete(Long code){
        UserType userTypeGet = this.findById(code).orElseThrow(() -> new EmptyResultDataAccessException(1));
        this.userTypeRepository.deleteById(code);
    }

}
