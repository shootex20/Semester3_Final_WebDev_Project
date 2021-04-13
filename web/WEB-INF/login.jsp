<%-- 
    Document   : login
    Created on : Oct 10, 2020, 11:24:48 AM
    Author     : 813017
--%>

<%@page contentType="width=device-width, initial-scale=1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script>document.addEventListener('DOMContentLoaded', () => {
            (document.querySelectorAll('.notification .delete') || []).forEach(($delete) => {
                const $notification = $delete.parentNode;

                $delete.addEventListener('click', () => {
                    $notification.parentNode.removeChild($notification);
                });
            });
        });</script>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
        <title>Login Page</title>
    </head>

    <body>
        <h1 class="title" style="text-align: center">Home nVentory</h1>
        <br>
        <div class="container">
            <c:if test="${not empty displayMessage}">
                <div class="notification is-link is-light">
                    <button class="delete"></button>
                    <p>${displayMessage}</p>
                </div>
            </c:if>
        </div>
        <div class="card container">

            <div class="card-content">
                <div class="content">
                    <h3>Login</h3>
                    <br>
                    <form method="post">
                        <div class="container">
                            <div class="field">

                                <label for="title">Username: </label>
                                <input class="input" type="text" name="username" placeholder="Username" value="${username}">

                            </div>
                            <div class="field">

                                <label for="title">Password: </label>
                                <input class="input" type="password" name="password" value="${password}" placeholder="Password">

                            </div>
                            <br>
                            <input type="submit" class="button is-info is-light" name="login" value="Login">
                            <br>
                            <br>
                            <p>Don't have an account? <a href="register" name="register">Create one!</a></p>
                            <br>
                            <br>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
