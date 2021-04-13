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
        <meta http-equiv="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
        <title>Home nVentory Registration</title>
    </head>
    <body>
        
                <nav class="navbar is-link" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <a class="navbar-item" href="inventory">
                    Home nVentory
                </a>

            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>

            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <a class="button is-primary" href="login">
                                <strong>Login</strong>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <br>
            <h2 class="title is-2" style="text-align: center">Register</h2>
            <div class="container">
            <div class="card">
        <div class="card-content">
          <div class="content">
            <form method="POST">
                Username: <input class="input is-medium" type="text" name="username"><br>
                Password: <input class="input is-medium" type="password" name="password"><br>
                Confirm Password <input class="input is-medium" type="password" name="passwordConfirm"> <br>
                Email: <input class="input is-medium" type="email" name="email"><br>
                Confirm Email: <input class="input is-medium" type="email" name="emailConfirm"><br>
                First Name: <input class="input is-medium" type="text" name="firstname"><br>
                Last Name: <input class="input is-medium" type="text" name="lastname"><br>
                <br>
                <input type="hidden" name="action" value="add">
                <input class="button is-success" type="submit" value="Register">
            </form>
            <h3> ${message} </h3>
                </div>
        </div>
      </div>
            </div>
    </body>
</html>
