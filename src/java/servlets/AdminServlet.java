/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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

        try {
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
       

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        if (action.equals("delete")) {
            try {
                String usern = request.getParameter("selectedUsername");
                UserDB userDB = new UserDB();
                Users user = new Users();
                user = (Users) userDB.getUser(usern);
                Users adminUser = userDB.getUser(username);
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

            try {
                userDB.insert(user);
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("displayMessage", "Uh-oh! Something went wrong.");
                //doGet(request, response);
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

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
