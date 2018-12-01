package com.pivaiot.example.mybatis.service.impl.repository;

import com.pivaiot.example.mybatis.service.impl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
