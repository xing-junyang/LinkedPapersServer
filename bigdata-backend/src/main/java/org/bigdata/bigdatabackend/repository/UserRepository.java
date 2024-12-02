package org.bigdata.bigdatabackend.repository;

import org.bigdata.bigdatabackend.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserId(Integer userId);
}

