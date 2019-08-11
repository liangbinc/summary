package com.lbc.mo.repository;

import com.lbc.mo.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JpaUser, Integer> {
    JpaUser findByName(String name);

}
