package com.springsecurity.manager;

import com.springsecurity.tool.AntUrlPathMatcher;
import com.springsecurity.tool.UrlMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

/**
 * Created by admin on 2018/1/15.
 */
public class MyInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource{
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String ,Collection<ConfigAttribute>> resourceMap = null;

    //tomcat启动时实例化一次
    public MyInvocationSecurityMetaDataSource(){
        loadResourceDefine();
    }

    //tomcat开启时加载一次，加载所有的url和权限（或角色）的对应关系
    private void loadResourceDefine(){
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        attrs.add(ca);
        resourceMap.put("index.jsp", attrs);
        Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        attsno.add(cano);
        resourceMap.put("other.jsp", attsno);
    }

    //参数是要访问的url，返回这个url对应的所有权限
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation)o).getRequestUrl();
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while(iterator.hasNext()){
            String resURL = iterator.next();
            if(urlMatcher.pathMatchesUrl(resURL, url)){
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> aClass) {
        return false;
    }
}
