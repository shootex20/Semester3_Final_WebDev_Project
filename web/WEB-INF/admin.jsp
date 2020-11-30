<%-- 
    Document   : admin
    Created on : Oct 10, 2020, 11:24:21 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
        <style>
        table, th, td {
         border: 1px solid black;
        }
    </style>
    <head>
        <link rel="shortcut icon" href="#">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <br>
        <h3>Menu</h3>
        <a href="inventory" name="inventory">Inventory</a>
        <br>
        <a href="admin" name="admin">Admin</a>
        <br>
        <a href="login?logout" name="logout">Logout</a>
        <br>
        <h2>Manage Users</h2>
        <br>            
                <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <form method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedUser == null}">
            <h2>Add User</h2>
            <form method="POST">
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                Email: <input type="email" name="email"><br>
                First Name: <input type="text" name="firstname"><br>
                Last Name: <input type="text" name="lastname"><br>

                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedUser != null}">
            <h2>Edit User</h2>
            <form action="admin" method="POST">
                Username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                Password: <input type="password" name="password" value="${selectedUser.password}"><br>
                Email: <input type="email" name="email" value="${selectedUser.email}"><br>
                First Name: <input type="text" name="firstname" value="${selectedUser.firstName}"><br>
                Last Name: <input type="text" name="lastname" value="${selectedUser.lastName}"><br>
                User is Active(Check for yes, uncheck for no): 
                <c:choose>
                 <c:when test="${selectedUser.active==true}">
                   <input type="checkbox" id="chk" name="useractive" 
                        value="${selectedUser.active}" checked="checked"/>
                 </c:when>
                 <c:otherwise>
                     <input type="checkbox" id="chk" name="useractive" 
                           value="${selectedUser.active}"/>
                 </c:otherwise>
              </c:choose>
                <br>
                User is a Admin(Check for yes, uncheck for no): 
                <c:choose>
                 <c:when test="${selectedUser.isAdmin==true}">
                   <input type="checkbox" id="chk" name="useradmin" 
                        value="${selectedUser.isAdmin}" checked="checked"/>
                 </c:when>
                 <c:otherwise>
                     <input type="checkbox" id="chk" name="useradmin" 
                           value="${selectedUser.isAdmin}"/>
                 </c:otherwise>
              </c:choose>
                     <br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
            <br>
           ${displayMessage}
        <br>
    </body>
</html>
