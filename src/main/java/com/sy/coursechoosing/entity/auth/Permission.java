package com.sy.coursechoosing.entity.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Permission {

    @Id
    private Integer id;

    private Integer permission;

    private Integer userId;

}
