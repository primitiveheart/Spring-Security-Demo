package com.springsecurity.tool;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by admin on 2018/1/15.
 */
public class AntUrlPathMatcher implements UrlMatcher {
    private boolean requiresLowerCaseUrl;
    private PathMatcher pathMatcher;

    public AntUrlPathMatcher(){
        this(true);
    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl){
        this.requiresLowerCaseUrl = true;
        this.pathMatcher = new AntPathMatcher();
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public Object compile(String path) {
        if(this.requiresLowerCaseUrl){
            return path.toLowerCase();
        }
        return path;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public boolean pathMatchesUrl(Object path, String url) {
        if("/**".equals(path) || "**".equals(path)){
            return true;
        }
        return this.pathMatcher.match((String) path,url);
    }

    public String getUniversaMatchPattern() {
        return "/**";
    }

    public boolean requiresLowerCaseUrl() {
        return this.requiresLowerCaseUrl;
    }

    public String toString(){
        return super.getClass().getName() + "[requiresLowerCase="+this.requiresLowerCaseUrl+"]";
    }
}
