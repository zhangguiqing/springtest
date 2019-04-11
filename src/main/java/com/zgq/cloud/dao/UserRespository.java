package com.zgq.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zgq.cloud.entity.User;

public interface UserRespository extends JpaRepository<User, Long> {

}
