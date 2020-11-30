/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Categories;
import models.HomeItems;
import models.Users;
import services.AccountService;

/**
 *
 * @author 813017
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDB userDB1 = new UserDB();
        CategoriesDB catDB = new CategoriesDB();

        try {
            List<Categories> categories = (List<Categories>) catDB.getAll();
            request.setAttribute("categories", categories);
            List<Users> users = (List<Users>) userDB1.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (action != null && action.equals("view")) {
            String selectedUsername = request.getParameter("selectedUsername");
            try {
                Users user = userDB1.getUser(selectedUsername);
                request.setAttribute("selectedUser", user);
                
                if(user.getActive() == true)
                {
                    request.setAttribute("useractive", "checked='checked'");
                }
                else
                {
                    request.setAttribute("useractive", "checked=''");
                }
                if(user.getIsAdmin()== true)
                {
                    request.setAttribute("useradmin", true);
                }
                else
                {
                    request.setAttribute("useradmin", false);
                }
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
            
        if (action != null && action.equals("viewCat")) {
            String selectedCat = request.getParameter("selectedCat");
            int catNum = Integer.parseInt(selectedCat);
            try {
            
                Categories cat = catDB.getCategory(catNum);
                request.setAttribute("selectedCat", cat);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //Users edit/del/add START
        if (action.equals("delete")) {
            try {
                String usern = request.getParameter("selectedUsername");
                UserDB userDB = new UserDB();
                Users user = new Users();
                user = (Users) userDB.getUser(usern);
                Users adminUser = userDB.getUser(username);
                
                //Deletes all users items.
                ItemsDB userItems = new ItemsDB();
                List<HomeItems> usersHomeItems = userItems.getAll(usern);
                
                for(int i = 0; i < usersHomeItems.size(); i++)
                {
                    userItems.delete(usersHomeItems.get(i));
                }
                
                if (!user.equals(adminUser)) {
                    userDB.delete(user);
                    request.setAttribute("displayMessage", "Successfully deleted user.");
                    doGet(request, response);
                } else {
                    request.setAttribute("displayMessage", "You cannot delete yourself!");
                    doGet(request, response);
                }

            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("displayMessage", "Uh-oh! Something went wrong.");
                doGet(request, response);
            }
        }
        if (action.equals("add")) {
            UserDB userDB = new UserDB();
            String addusername = request.getParameter("username");
            String addpassword = request.getParameter("password");
            String addemail = request.getParameter("email");
            String addfirstname = request.getParameter("firstname");
            String addlastname = request.getParameter("lastname");

           Users user = new Users(addusername, addpassword, addemail, addfirstname, addlastname, true, false);
           List<Users> userList = null;
           
            try {
                userList = (List<Users>)userDB.getAll();
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i = 0; i < userList.size(); i++)
            {
                if(userList.get(i).getUsername().equals(username))
                {
                request.setAttribute("displayMessage", "Username cannot be the same as another user!");
                doGet(request, response);
                }
                else if(userList.get(i).getEmail().equals(addemail))
                {
                request.setAttribute("displayMessage", "Email is already taken.");
                doGet(request, response);
                }
            }

            try {
                userDB.insert(user);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("displayMessage", "Uh-oh! Something went wrong when adding the user.");
                doGet(request, response);
            }
        }
        if (action.equals("edit")) {
            AccountService us = new AccountService();

            boolean userIsActive = false;
            boolean adminIsActive = false;

            String editUser = request.getParameter("username");
            String editPassword = request.getParameter("password");
            String editemail = request.getParameter("email");
            String editfirstname = request.getParameter("firstname");
            String editlastname = request.getParameter("lastname");
            Boolean editisactive = Boolean.parseBoolean(request.getParameter("useractive"));
            Boolean editisadmin = Boolean.parseBoolean(request.getParameter("useractive"));
            
            if(editisactive == true)
            {
                userIsActive = true;
            }
            else
            {
                userIsActive = false;
            }
            
            if(editisadmin == true)
            {
                adminIsActive = true;
            }
            else
            {
               adminIsActive = false; 
            }
            
            try {
                us.update(editUser, editPassword, editfirstname, editlastname, editemail, userIsActive, adminIsActive);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        //Users END
        
        //Categories Start
        
        if(action.equals("deleteCat"))
        {
            int catID = Integer.parseInt(request.getParameter("selectedCat"));
                CategoriesDB catDB = new CategoriesDB();
                Categories cat = new Categories();
                cat = (Categories) catDB.getCategory(catID);
            try {
                catDB.delete(cat);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(action.equals("addCat"))
        {
            CategoriesDB catDB = new CategoriesDB();
            String categoryName = request.getParameter("nameOfCat");
            
            Categories newCat = new Categories(categoryName);
            
            try {
                catDB.insert(newCat);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        else if(action.equals("editCat"))
        {
            CategoriesDB catDB = new CategoriesDB();
            int categoryID = Integer.parseInt(request.getParameter("catID"));
            String catName = request.getParameter("nameOfCat");
            Categories catUpdate = new Categories(categoryID, catName);
            try {
                catDB.update(catUpdate);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        //Categories end.
    }

}
