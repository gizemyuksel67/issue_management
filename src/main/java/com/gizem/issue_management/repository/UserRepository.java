package com.gizem.issue_management.repository;

import com.gizem.issue_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

User findByUsername(String username);


}
