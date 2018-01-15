package com.springsecurity.tool;

/**
 * Created by admin on 2018/1/15.
 */
public interface UrlMatcher {
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversaMatchPattern();
    boolean requiresLowerCaseUrl();
}
