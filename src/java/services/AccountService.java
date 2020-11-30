package services;

import dataaccess.UserDB;
import java.util.List;
import models.Users;

public class AccountService {

    public Users login(String username, String password) {
        UserDB userDB = new UserDB();

        try {
            Users user = userDB.getUser(username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void update(String username, String password, String firstname, String lastname, String email, boolean active, boolean isAdmin) throws Exception {
        UserDB userDB = new UserDB();
        Users user = userDB.getUser(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setActive(active);
        user.setIsAdmin(isAdmin);
        userDB.update(user);
    }

    public void insert(String username, String password, String firstname, String lastname, String email) throws Exception {
        UserDB userDB = new UserDB();

        Users user = new Users(username, password, email, firstname, lastname, true, false);
        userDB.insert(user);
    }

    public List<Users> getAll() throws Exception {
        UserDB userDB = new UserDB();

        return userDB.getAll();
    }
}
