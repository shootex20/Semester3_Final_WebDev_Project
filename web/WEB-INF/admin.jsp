<%-- 
    Document   : admin
    Created on : Oct 10, 2020, 11:24:21 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="shortcut icon" href="#">
        <meta http-equiv="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
        <title>Administrator Page</title>
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
                <div class="navbar-start">
                    <a class="navbar-item" href="inventory" name="inventory">
                        Home
                    </a>

                    <a class="navbar-item" href="manageuser">
                        Manage Account
                    </a>
                    <a class="navbar-item" href="admin">
                        Admin Panel
                    </a>
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
        <c:if test="${not empty displayMessage}">
            <div class="notification is-link is-light">
                <button class="delete"></button>
                <p>${displayMessage}</p>
            </div>
        </c:if>
        <br>
        <br>
        <h2 class="title is-2" style="text-align: center">Manage Users</h2>
        <br> 
        <div class="container">
            <div class="card">
                <div class="card-content">
                    <div class="content">
                        <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
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
                                            <input class="button is-danger" type="submit" value="Delete">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="selectedUsername" value="${user.username}">
                                        </form>
                                    </td>
                                    <td>
                                        <form action="admin" method="get">
                                            <input class="button is-info" type="submit" value="Edit">
                                            <input type="hidden" name="action" value="view">
                                            <input type="hidden" name="selectedUsername" value="${user.username}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:if test="${selectedUser == null}">
                            <h2 class="title is-5">Add User</h2>
                            <form method="POST">
                                Username: <input  class="input is-medium"  type="text" name="username"><br>
                                Password: <input class="input is-medium"  type="password" name="password"><br>
                                Email: <input class="input is-medium"  type="email" name="email"><br>
                                First Name: <input class="input is-medium"  type="text" name="firstname"><br>
                                Last Name: <input class="input is-medium"  type="text" name="lastname"><br>
                                <br>
                                <input type="hidden" name="action" value="add">
                                <input class="button is-info is-light" type="submit" value="Save">
                            </form>
                        </c:if>
                        <c:if test="${selectedUser != null}">
                            <h2>Edit User</h2>
                            <form action="admin" method="POST">
                                Username: <input class="input is-medium" type="text" name="username" value="${selectedUser.username}" readonly><br>
                                Password: <input class="input is-medium" type="password" name="password" value="${selectedUser.password}"><br>
                                Email: <input class="input is-medium" type="email" name="email" value="${selectedUser.email}"><br>
                                First Name: <input class="input is-medium" type="text" name="firstname" value="${selectedUser.firstName}"><br>
                                Last Name: <input class="input is-medium" type="text" name="lastname" value="${selectedUser.lastName}"><br>
                                User is Active (Check for yes, uncheck for no): 
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
                                User is a Administrator (Check for yes, un-check for no): 
                                <c:choose>
                                    <c:when test="${selectedUser.isAdmin==true}">
                                        <input type="checkbox" id="chk" name="useradmin" 
                                               value="true" checked="checked"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" id="chk" name="useradmin" 
                                               value="false"/>
                                    </c:otherwise>
                                </c:choose>
                                <br>
                                <br>
                                <input type="hidden" name="action" value="edit">
                                <input class="button is-info is-light" type="submit" value="Save">
                            </form>
                        </c:if>
                        <br>
                        ${displayMessage}
                        <br>
                    </div>
                </div>
            </div>
            <br>
            <h2 class="title is-2" style="text-align: center">Manage Categories</h2>
            <br>
            <div class="card">
                <div class="card-content">
                    <div class="content">
                        <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                            <tr>
                                <th>Category</th>
                                <!--<th>Delete</th>-->
                                <th>Edit</th>
                            </tr>
                            <c:forEach var="categoryDB" items="${categories}">
                                <tr>
                                    <td>${categoryDB.categoryName}</td>
                                    <!--- Works but would not advise using due to DB errors. 
                                    If you want to test be sure to uncomment the 'delete' on the bottom of the AdminServlet Page.
                                    <td>
                                        <form method="post" >
                                            <input type="submit" value="Delete">
                                            <input type="hidden" name="action" value="deleteCat">
                                            <input type="hidden" name="selectedCat" value="${categoryDB.categoryID}">
                                        </form>
                                    </td>
                                    --->
                                    <td>
                                        <form action="admin" method="get">
                                            <input  class="button is-info" type="submit" value="Edit">
                                            <br>
                                            <input type="hidden" name="action" value="viewCat">
                                            <input type="hidden" name="selectedCat" value="${categoryDB.categoryID}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        <c:if test="${selectedCat == null}">
                            <h2 class="title is-5">Add Category</h2>
                            <form method="POST">
                                Category Name: <input class="input is-medium" type="text" name="nameofCat" value="${selectedCat.categoryName}"><br>
                                <br>
                                <input type="hidden" name="action" value="addCat">
                                <input class="button is-info is-light" type="submit" value="Save">
                            </form>
                        </c:if>
                        <c:if test="${selectedCat != null}">
                            <h2 class="title is-5">Edit Category</h2>
                            <form action="admin" method="POST">
                                Category Name: <input type="text" name="nameOfCat" value="${selectedCat.categoryName}">
                                <input type="hidden" name="catID" value="${selectedCat.categoryID}"><br>
                                <br>
                                <input type="hidden" name="action" value="editCat">
                                <input class="button is-info is-light" type="submit" value="Save">
                            </form>
                        </c:if>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
