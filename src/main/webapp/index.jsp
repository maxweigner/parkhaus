<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<form method="GET" action="/hello_web_war_exploded/welcome-servlet">
    <input type="text", id="name", name="name", placeholder="name">
    <input type="submit">
</form>
<br/>
<a href="welcome-servlet">Hello Servlet</a>
</body>
</html>