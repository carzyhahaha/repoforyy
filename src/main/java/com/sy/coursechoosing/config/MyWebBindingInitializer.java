package com.sy.coursechoosing.config;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

// 自定义数据绑定
public class MyWebBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {

        // string转boolean
        webDataBinder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
    }
}
