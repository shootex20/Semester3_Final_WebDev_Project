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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User</title>
    </head>
    <body>
        <h1>Home nVentory</h1>
        <br>
        <h3>Menu</h3>
        <a href="inventory" name="inventory">Inventory</a>
        <br>
        <a href="admin" name="admin">Admin</a>
        <br>
        <a href="manageuser" name="manageuser">Manage Account</a>
        <br>
        <a href="login?logout" name="logout">Logout</a>
        <br>
        <input type="hidden" name="accountinfo" value="${username}">
        <h2>Manage Account</h2>
        <form method="post">
            <input type="hidden" name="accountinfo" value="${selectedUser}">
                Username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                Password: <input type="password" name="password" value="${selectedUser.password}"><br>
                Email: <input type="email" name="email" value="${selectedUser.email}"><br>
                First Name: <input type="text" name="firstname" value="${selectedUser.firstName}"><br>
                Last Name: <input type="text" name="lastname" value="${selectedUser.lastName}"><br>
                <br>
                <br>
                <h4>Deactivate User account (Warning, if you deactivate the account you must contact an admin. Un-check to continue to deactivate)</h4>
                <c:choose>
                     <c:when test="${selectedUser.active==true}">
                       <input type="checkbox" id="chk" name="useractive" 
                            value="true" checked="checked"/>
                     </c:when>
                     <c:otherwise>
                         <input type="checkbox" id="chk" name="useractive" 
                               value="false"/>
                     </c:otherwise>
                  </c:choose>
                       <br>
                       <br>
                <input type="submit" name="action" value="Save">
        </form>
                <br>
                <br>
                ${message}
    </body>
</html>
