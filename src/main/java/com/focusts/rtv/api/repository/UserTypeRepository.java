package com.focusts.rtv.api.repository;

import com.focusts.rtv.api.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    
}
