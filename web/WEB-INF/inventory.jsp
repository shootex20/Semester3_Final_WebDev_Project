<%-- 
    Document   : inventory
    Created on : Oct 10, 2020, 11:24:35 AM
    Author     : 813017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        table, th, td {
         border: 1px solid black;
         width: 30%;
        }
    
    </style>
        
    <head>
        <link rel="shortcut icon" href="#">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    
    <body>
        <h1>HOME nVentory</h1>
        <br>
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
        <h2>Inventory for ${nameofinventory}.</h2>
            
        <table>
         <tr>
           <th>Category</th>
           <th>Name</th>
           <th>Price</th>
           <th>Delete</th>
           <th>Edit</th>
         </tr>
        <c:forEach items="${homeitems}" var="homeitem">
         <tr>
           <td>${homeitem.category.categoryName}</td>
           <td>${homeitem.itemName}</td>
           <td>$${homeitem.price}</td>
           <td>
            <form method="post"> 
            <input type="submit" name="action" value="Delete">
            <input type="hidden" name="itemID" value="${homeitem.itemID}">
            </form>
            </td>
            <!---
           <td>
               <form method="post"> 
            <input type="hidden" name="action" value="view">
            <input type="submit" value="Edit"> 
            <input type="hidden" name="selectedItem" value="${homeitem.itemID}">
           </form>
         </td>
            --->
         </tr>
         </c:forEach>
       </table>
        <br>
        
        <h3>Add Item</h3>
        <form method="post">
        <br>
        <label for="category">Category: </label>
        <select name="category" id="category">
        <c:forEach items="${categories}" var="category">
        <option value="${category.categoryID}">${category.categoryName}</option>
        </c:forEach>
        </select>
        <br>
        <label for="title">Item Name: </label>
        <input type="text" name="itemnames" value="${selectedItem.itemName}">
        <br>
        <label for="title">Price: </label>
        <input type="text" name="itemprice" value="${selectedItem.price}">
        <br>
        <input type="submit" name="action" value="Add">
        <br>
        <br>
        </form>
        <!---
        <c:if test="${selectedItem == null}">
        <h3>Add Item</h3>
        <form method="post">
        <br>
        <label for="category">Category: </label>
        <select name="category" id="category">
        <c:forEach items="${categories}" var="category">
        <option value="${category.categoryID}">${category.categoryName}</option>
        </c:forEach>
        </select>
        <br>
        <label for="title">Item Name: </label>
        <input type="text" name="itemnames" value="${selectedItem.itemName}">
        <br>
        <label for="title">Price: </label>
        <input type="text" name="itemprice" value="${selectedItem.price}">
        <br>
        <input type="submit" name="action" value="Add">
        <br>
        <br>
        </form>
        </c:if>
        <c:if test="${selectedItem != null}">
        <h3>Edit Item</h3>
        <form method="post">
        <br>
        <label for="category">Category: </label>
        <select name="category1" id="category">
        <c:forEach items="${categories}" var="category">
        <option value="${category.categoryID}">${category.categoryName}</option>
        </c:forEach>
        </select>
        <br>
        <label for="title">Item Name: </label>
        <input type="text" name="itemnames" value="${selectedItem.itemName}">
        <br>
        <label for="title">Price: </label>
        <input type="text" name="itemprice" value="${selectedItem.price}">
        <br>
        <input type="hidden" name="catID" value="${category.categoryID}">
        <input type="hidden" name="itemID" value="${selectedItem.itemID}">
        <input type="submit" name="action" value="Save">
        <br>
        <br>
        </form>
        </c:if>
        --->
        <br>
        <p>${message}</p>
        <p>${total}</p>
        <br>
    </body>
</html>
