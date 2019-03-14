package com.sy.coursechoosing.dao;

import com.sy.coursechoosing.entity.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission, Integer> {

}
