/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author Chels
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       String action = request.getParameter("action");
       UserDB userDB = new UserDB();
       List<Users> userlist = null;
        try {
            userlist = (List<Users>) userDB.getAll();
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       if(action.equals("add"))
       {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            String email = request.getParameter("email");
            String emailConfirm = request.getParameter("emailConfirm");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            
            for(int i = 0; i < userlist.size(); i++)
            {
                if(userlist.get(i).getUsername().equals(username))
                {
                    request.setAttribute("message", "Username already exists!");
                    doGet(request, response);
                }
                else if(userlist.get(i).getEmail().equals(email))
                {
                    request.setAttribute("message", "Email is already in use.");
                    doGet(request, response);
                }
            }
            
            if(!password.equals(passwordConfirm) || !passwordConfirm.equals(password))
            {
                request.setAttribute("message", "Passwords do not match.");
                doGet(request, response);
            }
            else if(!email.equals(emailConfirm) || !emailConfirm.equals(email))
            {
                request.setAttribute("message", "Emails do not match.");
                doGet(request, response);
            }
            else
            {
                Users newUser = new Users(username, password, email, firstname, lastname, true, false);
                try {
                    userDB.insert(newUser);
                    session.setAttribute("displayMessage", "Account successfully created! You can now login.");
                    response.sendRedirect("login");
                } catch (Exception ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
       }
        
        
    }

}
