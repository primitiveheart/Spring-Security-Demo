<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<body>
<h2>这是首页</h2> 欢迎
<sec:authentication property="name"/> !
<a href="admin.jsp">进入admin页面</a>
<a href="other.jsp">进入其他页面</a>
</body>
</html>
