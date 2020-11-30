<%-- 
    Document   : login
    Created on : Oct 10, 2020, 11:24:48 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    
    <body>
        <h1>HOME nVentory</h1>
        <br>
        <h3>Login</h3>
        <br>
        <form method="post">
        <label for="title">Username: </label>
        <input type="text" name="username" value="${username}">
        <br>
        <label for="title">Password: </label>
        <input type="password" name="password" value="${password}">
        <br>
        <input type="submit" name="login" value="Login">
        <br>
        <br>
        ${displayMessage}
</form>
    </body>
</html>
