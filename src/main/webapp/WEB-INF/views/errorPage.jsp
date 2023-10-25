<%--
  Created by IntelliJ IDEA.
  User: wassim2
  Date: 10/11/2023
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Oops! Something went wrong.</h1>
<p>${errorMessage}</p>

<p><a href="${pageContext.request.contextPath}/article.jsp">Go back</a></p>

</body>
</html>
