<%-- 
    Document   : register
    Created on : Nov 29, 2020, 11:30:08 PM
    Author     : Chels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home nVentory Registration</title>
    </head>
    <body>
        <h1>Home nVentory Registration</h1>
        <br>
            <h2>Register</h2>
            <form method="POST">
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                Confirm Password <input type="password" name="passwordConfirm"> <br>
                Email: <input type="email" name="email"><br>
                Confirm Email: <input type="email" name="emailConfirm"><br>
                First Name: <input type="text" name="firstname"><br>
                Last Name: <input type="text" name="lastname"><br>
                <br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Register">
            </form>
            <h3> ${message} </h3>
    </body>
</html>
