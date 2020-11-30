/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoriesDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import services.Inventory;

/**
 *
 * @author Chelsey Coughlin
 */
@WebServlet(name = "InventoryServlet", urlPatterns = {"/InventoryServlet"})
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Inventory inv = new Inventory();
        
        

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        try {
            List<HomeItems> homeitems = (List<HomeItems>) inv.getAll(username);
            request.setAttribute("homeitems", homeitems);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

            UserDB userDB = new UserDB();
            Users user = new Users();
        try {
            user = userDB.getUser(username);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String firstlast = user.getFirstName() + " " + user.getLastName();
            session.setAttribute("nameofinventory", firstlast);
            
            CategoriesDB catdb = new CategoriesDB();

            List<Categories> categories = new ArrayList<Categories>();
        try {
            categories = (List<Categories>) catdb.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("categories", categories);

            double totalPrice = 0;
            List<HomeItems> items = new ArrayList<HomeItems>();
            try
            {
                items = (List<HomeItems>) inv.getAll(username);
            }
            catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            for(int i = 0; i < items.size(); i++)
            {
                double temp = items.get(i).getPrice();
                totalPrice = totalPrice + temp;
            }

                totalPrice = Math.round(totalPrice * 100);
                totalPrice = totalPrice/100;

            String info = "Total value in inventory: $" + totalPrice;

            //Gives total inventory.
            request.setAttribute("total", info);

            request.getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get the user name.
        HttpSession session = request.getSession();
        //Get the user name of the session.
        String username = (String) session.getAttribute("username");

        Inventory inv = new Inventory();

        String action = request.getParameter("action");
        String category = request.getParameter("category");
        String itemName = request.getParameter("itemnames");
        String inputPrice = request.getParameter("itemprice");
        
        

        if(action.equals("Add"))
        {
            
                    //Checks category List
        CategoriesDB catdb = new CategoriesDB();
        
        List<Categories> cat = null;
        
        int catNum = 0;
        Categories catObj = null;
        
        try {
            cat = (List<Categories>) catdb.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int parseCatId = Integer.parseInt(category);
        for(int i = 0; i < cat.size(); i++)
        {
            if(cat.get(i).getCategoryID().equals(parseCatId))
            {
                catObj = cat.get(i);
            }
        }
            
        double price = Double.parseDouble(inputPrice);

            if(price > 0)
            {

            HomeItems hi;
            try {
                inv.insert(catObj,itemName, price, username);
                request.setAttribute("message", "Item has been sucessfully added.");
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {

                request.setAttribute("message", "Invalid. Please re-enter.");
                doGet(request, response);
            }
        }
        if(action.equals("Delete"))
        {
                    String id = request.getParameter("itemID");
            System.out.print(id);
            int intid = Integer.parseInt(id);
            try {
                inv.delete(intid);
                request.setAttribute("message", "Item successfully deleted.");
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}