package com.pivaiot.starter.project.service.impl.repository;

import com.pivaiot.starter.project.service.impl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
