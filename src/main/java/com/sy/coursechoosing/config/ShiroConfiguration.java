package com.sy.coursechoosing.config;

import com.sy.coursechoosing.auth.MyShiroRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {


    // 把自己得验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm();
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
}
