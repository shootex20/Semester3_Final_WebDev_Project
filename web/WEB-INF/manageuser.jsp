<%-- 
    Document   : manageuser
    Created on : Nov 29, 2020, 10:19:16 PM
    Author     : Chels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
        <title>Manage User</title>
    </head>
    <body>
  <input type="hidden" name="hiddenUser" value="${hiddenUser}">
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
                <div class="navbar-start">
                    <a class="navbar-item" href="inventory" name="inventory">
                        Home
                    </a>

                    <a class="navbar-item" href="manageuser">
                        Manage Account
                    </a>
                    <c:if test="${hiddenUser eq true}">
                        <a class="navbar-item" href="admin">
                            Admin Panel
                        </a>
                    </c:if>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <a class="button is-primary" href="login?logout" name="logout">
                                <strong>Logout</strong>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <br>
        <input type="hidden" name="accountinfo" value="${username}">
        <h2 class="title is-2" style="text-align: center">Manage Account</h2>
        <br>
        <div class="container">
            <div class="card">
  <div class="card-content">
    <div class="content">
        <form method="post">
            <input type="hidden" name="accountinfo" value="${selectedUser}">
                Username: <input class="input is-medium" type="text" name="username" value="${selectedUser.username}" readonly><br>
                Password: <input class="input is-medium" type="password" name="password" value="${selectedUser.password}"><br>
                Email: <input class="input is-medium" type="email" name="email" value="${selectedUser.email}"><br>
                First Name: <input class="input is-medium" type="text" name="firstname" value="${selectedUser.firstName}"><br>
                Last Name: <input class="input is-medium" type="text" name="lastname" value="${selectedUser.lastName}"><br>
                <br>
                <br>
                <c:choose>
                     <c:when test="${selectedUser.active==true}">
                         
                                                  <label class="checkbox">
                       <input type="checkbox" id="chk" name="useractive" 
                            value="true" checked="checked"/>
                    Deactivate User account (Warning, if you deactivate the account you must contact an admin. Un-check to continue to deactivate)
                      </label>
                     </c:when>
                     <c:otherwise>
                         <label class="checkbox">
                         <input type="checkbox" id="chk" name="useractive" value="false"/>
                    Deactivate User account (Warning, if you deactivate the account you must contact an admin. Un-check to continue to deactivate)
                      </label>
                     </c:otherwise>
                  </c:choose>
                       <br>
                       <br>
                <input class="button is-success" type="submit" name="action" value="Save">
        </form>
                <br>
                <br>
        </div>
  </div>
            </div>
        </div>
                ${message}
    </body>
</html>
