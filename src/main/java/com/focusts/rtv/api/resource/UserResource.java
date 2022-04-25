package com.focusts.rtv.api.resource;

import java.util.List;

import com.focusts.rtv.api.model.User;
import com.focusts.rtv.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/{code}")
    public ResponseEntity<User> findById(@PathVariable Long code) {
        return this.userService.findById(code)
            .map(user -> ResponseEntity.ok(user))
            .orElse(ResponseEntity.notFound().build());
    }
        
}
