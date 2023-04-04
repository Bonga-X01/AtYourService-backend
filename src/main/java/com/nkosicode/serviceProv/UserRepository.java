package com.nkosicode.serviceProv;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByPhoneNumber(String phoneNumber);
}
