package com.focusts.rtv.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.focusts.rtv.api.event.CreatedResourceEvent;
import com.focusts.rtv.api.model.UserType;
import com.focusts.rtv.api.repository.UserTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usertypes")
public class UserTypeResource {
    
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<UserType> list(){
        return userTypeRepository.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<UserType> findById(@PathVariable Long code) {
        return this.userTypeRepository.findById(code)
            .map(userType -> ResponseEntity.ok(userType))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserType> create(@Valid @RequestBody UserType userType, HttpServletResponse response){
        UserType userTypeGet = this.userTypeRepository.save(userType);
        publisher.publishEvent(new CreatedResourceEvent(this, response, userTypeGet.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userTypeGet);
    }

}
