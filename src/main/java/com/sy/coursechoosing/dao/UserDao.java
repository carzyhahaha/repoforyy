package com.sy.coursechoosing.dao;

import com.sy.coursechoosing.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByUsernameEquals(String username);

}
