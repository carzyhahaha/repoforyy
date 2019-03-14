package com.sy.coursechoosing.entity.test;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "test")
@Data
public class Test {

    @Id
    private Integer id;

    @Column(length = 255)
    private String key1;

    @Column(length = 255)
    private String key2;

    @Column
    private Date created;

    @Column
    private Date updated;

}
