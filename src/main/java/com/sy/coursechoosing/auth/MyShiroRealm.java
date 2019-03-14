package com.sy.coursechoosing.auth;

import com.sy.coursechoosing.dao.PermissionDao;
import com.sy.coursechoosing.dao.UserDao;
import com.sy.coursechoosing.entity.auth.Permission;
import com.sy.coursechoosing.entity.auth.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    UserDao userDao;

    @Autowired
    PermissionDao permissionDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String name = (String) principalCollection.getPrimaryPrincipal();

        User user = userDao.findUserByUsernameEquals(name);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (user != null) {
            Permission permission = new Permission();
            permission.setUserId(user.getId());
            Example<Permission> example = Example.of(permission);
            List<Permission> permissions = permissionDao.findAll(example);

            permissions.forEach(o -> {
                simpleAuthorizationInfo.addStringPermission(String.valueOf(o.getPermission()));
            });

        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String name = authenticationToken.getPrincipal().toString();
        User user = userDao.findUserByUsernameEquals(name);
        if (user == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
