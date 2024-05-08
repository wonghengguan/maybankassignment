package com.maybank.wonghengguan.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.wonghengguan.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findByUsername(String username);

}
