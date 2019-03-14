package com.sy.coursechoosing.dao;

import com.sy.coursechoosing.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao extends JpaRepository<Test, Integer> {

}
