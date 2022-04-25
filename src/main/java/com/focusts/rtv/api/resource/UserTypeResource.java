package com.focusts.rtv.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.focusts.rtv.api.event.CreatedResourceEvent;
import com.focusts.rtv.api.model.UserType;
import com.focusts.rtv.api.service.UserTypeService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usertypes")
public class UserTypeResource {
    
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    public List<UserType> list(){
        return userTypeService.list();
    }

    @GetMapping("/{code}")
    public ResponseEntity<UserType> findById(@PathVariable Long code) {
        return this.userTypeService.findById(code)
            .map(userType -> ResponseEntity.ok(userType))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserType> create(@Valid @RequestBody UserType userType, HttpServletResponse response){
        UserType userTypeGet = this.userTypeService.create(userType);
        publisher.publishEvent(new CreatedResourceEvent(this, response, userTypeGet.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userTypeGet);
    }

    @PutMapping("/{code}")
    public ResponseEntity<UserType> update(@PathVariable Long code, @Valid @RequestBody UserType userType){
        UserType userTypeGet = this.userTypeService.update(code, userType);
        return ResponseEntity.status(HttpStatus.OK).body(userTypeGet);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long code) {
		this.userTypeService.delete(code);
	}

}
