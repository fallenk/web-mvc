<%--
  Created by IntelliJ IDEA.
  User: liulifeng
  Date: 2020/7/26
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World - JSP</title>
</head>
<body>
<%-- JSP Comment --%>
    <h1>Hello World!</h1>
    <p>
        <%
            out.println("Your IP address is ");
        %>
        <span>
        <%= request.getRemoteAddr()%>
        </span>
    </p>

</body>
</html>
