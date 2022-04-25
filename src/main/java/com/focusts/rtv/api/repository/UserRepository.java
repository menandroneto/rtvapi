package com.focusts.rtv.api.repository;

import com.focusts.rtv.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
