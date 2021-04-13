<%-- 
    Document   : inventory
    Created on : Oct 10, 2020, 11:24:35 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
        <title>Inventory Page</title>
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
        <div class="container">   
            <c:if test="${not empty message}">
                <div class="notification is-link is-light">
                    <button class="delete"></button>
                    <p>${message}</p>
                </div>
            </c:if>
            <br>

            <div class="card">
                <div class="card-content">
                    <div class="content">
                        <table class="table is-bordered is-striped is-narrow is-hoverable">
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <c:forEach items="${homeitems}" var="homeitem">
                                    <tr>
                                        <td>${homeitem.category.categoryName}</td>
                                        <td>${homeitem.itemName}</td>
                                        <td><fmt:formatNumber value="${homeitem.price}" type="currency"/></td>
                                        <td>
                                            <form method="post"> 
                                                <div class="buttons are-small">
                                                    <input type="submit" class="button is-danger" name="action" value="Delete">
                                                    <input type="hidden" name="itemID" value="${homeitem.itemID}">
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tfoot>
                        </table>


                        <br>
                        <br>
                        <h3 class="title is-3" >Add Item</h3>
                        <form method="post">
                            <br>
                            <label for="category">Category:     </label>
                            <br>
                            <div class="select">
                                <select name="category" id="category">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.categoryID}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br>

                            <div class="control">

                                <label for="title">Item Name: </label>
                                <input class="input" type="text" name="itemnames" value="${selectedItem.itemName}">
                                <br>
                                <label for="title">Price: </label>
                                <input class="input" type="text" name="itemprice" value="${selectedItem.price}">
                                <br>
                            </div>
                            <br>
                            <input class="button is-info is-light" type="submit" name="action" value="Add">
                            <br>
                            <br>
                        </form>
                    </div>
                </div>
            </div>
            <br>
            <h5 class="title is-5">Total value in inventory: <fmt:formatNumber value="${total}" type="currency"/></h5>
            <br>
        </div>
    </body>
</html>
