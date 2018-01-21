package com.springsecurity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by admin on 2018/1/15.
 */
public class MyUserDetailService implements UserDetailsService {
    //登录验证，通过username获取所有的权限信息，并返回user放到spring的全局缓存SecurityContextHolder,以供授权器使用
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        GrantedAuthorityImpl auth1 = new GrantedAuthorityImpl("ROLE_USER");
        GrantedAuthorityImpl auth2 = new GrantedAuthorityImpl("ROLE_ADMIN");
        if(username.equals("xiaoming")){
            auths.add(auth1);
            auths.add(auth2);
        }

        User user = new User(username, "xiaoming", true, true, true,true, auths);
        return user;
    }
}
