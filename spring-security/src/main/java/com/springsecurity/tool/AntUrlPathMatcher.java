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

    public Object compile(String paramString) {
        return null;
    }

    public boolean pathMatchesUrl(Object paramObject, String paramString) {
        return false;
    }

    public String getUniversaMatchPattern() {
        return null;
    }

    public boolean requiresLowerCaseUrl() {
        return false;
    }
}
