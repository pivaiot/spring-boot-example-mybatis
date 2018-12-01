package com.pivaiot.example.jpa.service.impl.repository;

import com.pivaiot.example.jpa.service.impl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
