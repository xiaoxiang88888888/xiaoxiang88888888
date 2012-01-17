<%@ page import="com.xiaoxiang.Main" %>
<%@ page import="com.xiaoxiang.util.JsonUtil" %>

<%--
 * 测试类
 *
 * @author xiang.xiaox
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<body>Place your content here
<%=new Main().toString()%>
<br/>
<%=JsonUtil.getInstance().ObjectToJson(new Main().toString())%>
<br/>
<%=JsonUtil.getInstance().ObjectToJson(new Main().getList())%>
<br/>
<%=request.getParameter("name")%>
</body>
</html>