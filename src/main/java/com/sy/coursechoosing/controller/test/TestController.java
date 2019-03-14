package com.sy.coursechoosing.controller.test;


import com.sy.coursechoosing.dao.TestDao;
import com.sy.coursechoosing.entity.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    TestDao testDao;

    @GetMapping("method1")
    public String method1() {
        return "test method1 successful 12";
    }


    @GetMapping("testInsertDB")
    public String testDB() {
        Test t = new Test();
        t.setId(1);
        t.setKey1("hello");
        t.setKey2("world");
        t.setCreated(new Date());
        t.setUpdated(new Date());
        testDao.save(t);
        return "testDB successful";
    }
}
