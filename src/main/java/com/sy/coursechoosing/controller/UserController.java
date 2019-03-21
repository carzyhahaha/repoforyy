package com.sy.coursechoosing.controller;

import com.sy.coursechoosing.entity.auth.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Integer id) {

        return "get id : " + id;
    }
}
