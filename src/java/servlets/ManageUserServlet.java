/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Chels
 */
@WebServlet(name = "ManageUserServlet", urlPatterns = {"/ManageUserServlet"})
public class ManageUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        //Get the user name of the session.
        String username = (String) session.getAttribute("username");
        UserDB userDB = new UserDB();
        
        Users user = new Users();
        user = userDB.getUser(username);
        
        request.setAttribute("selectedUser", user);
         if(user.getActive() == true)
                {
                    request.setAttribute("useractive", "checked='checked'");
                }
                else
                {
                    request.setAttribute("useractive", "checked=''");
                }
        request.setAttribute("username", username);
        getServletContext().getRequestDispatcher("/WEB-INF/manageuser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        //Get the user name of the session.
        String usernameSession = (String) session.getAttribute("username");
        UserDB userDB = new UserDB();
        
        Users user = new Users();
        user = userDB.getUser(usernameSession);
        String action = request.getParameter("action");
        
        if(action.equals("Save"))
        {
            AccountService us = new AccountService();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            Boolean editisactive = Boolean.parseBoolean(request.getParameter("useractive"));
            boolean userIsActive = false;
            
            if(editisactive == true)
            {
                userIsActive = true;
            }
            else
            {
                userIsActive = false;
            }
            
            try {
                us.update(username, password, firstname, lastname,email, userIsActive, user.getIsAdmin());
                request.setAttribute("message", "Account has been successfully updated!");
                if(userIsActive == false)
                {
                    session.setAttribute("displayMessage", "Account successfully been deactivated.");
                    response.sendRedirect("login");
                }
                else
                {
                doGet(request, response);
                }
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "An error has occoured, try again later!");
                doGet(request, response);
            }
            
        }
    }

}
