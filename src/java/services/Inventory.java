/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.util.List;
import models.Categories;
import models.HomeItems;
import models.Users;

/**
 *
 * @author 813017
 */
public class Inventory {
        public HomeItems get(int id) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        HomeItems hi = hiDB.get(id);
        return hi;
    }
    
    public List<HomeItems> getAll(String username) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        List<HomeItems> homeitems = hiDB.getAll(username);
        return homeitems;
    }
    
    public void insert(Categories catID, String itemName, double price, String owner) throws Exception {
        HomeItems hi = new HomeItems(catID, itemName, price);
        UserDB userDB = new UserDB();
        Users user = userDB.getUser(owner);
        hi.setOwner(user);
        
        ItemsDB hiDB = new ItemsDB();
        hiDB.insert(hi);
    }
    
    public void update(Integer itemID, String itemName, double price, String owner) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        HomeItems hi = hiDB.get(itemID);
        hi.setItemName(itemName);
        hi.setPrice(price);
        
        hiDB.update(hi);
    }
    
    public void delete(int id) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        HomeItems hi = (HomeItems) hiDB.get(id);
        hiDB.delete(hi);
    }
}
