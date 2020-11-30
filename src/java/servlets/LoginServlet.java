/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String logout = request.getParameter("logout");
        
        if (logout != null) {
            session.invalidate();
            request.setAttribute("displayMessage", "Logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        
        AccountService as = new AccountService();
        Users user = as.login(username, password);
        
        if (user == null) {
            request.setAttribute("displayMessage", "Please enter a username and password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        if (user.getIsAdmin() == true && user.getActive() == true) {
            response.sendRedirect("admin");
        } else if(user.getActive() == true){
            response.sendRedirect("inventory");
        }
        else
        {
        request.setAttribute("displayMessage", "Account is either not active or invalid information has been given.");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }


    }

}
