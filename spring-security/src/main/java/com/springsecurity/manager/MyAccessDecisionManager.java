package com.springsecurity.manager;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by admin on 2018/1/15.
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 检查用户是否足够的权限访问资源
     * @param authentication 该参数是从spring全局缓存SecurityContextHolder中拿到的，里面是用户的权限
     * @param o 该参数url
     * @param collection 所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(collection == null){
            return;
        }

        Iterator<ConfigAttribute> iterator = collection.iterator();
        while(iterator.hasNext()){
            ConfigAttribute ca = iterator.next();
            String needRole = ((SecurityConfig)ca).getAttribute();

            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }

        //执行这里,后台会抛出异常，但是界面会跳转到所配的access-denied-page页面
        throw  new AccessDeniedException("no right");
    }

    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    public boolean supports(Class<?> aClass) {
        return false;
    }
}
