/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.HomeItems;
import models.Users;

/**
 *
 * @author 813017
 */
public class ItemsDB {
    
    
    public List<HomeItems> getAll(String owner) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Users user = em.find(Users.class, owner);
            return user.getHomeItemsList();
        } finally {
            em.close();
        }
    }

    public HomeItems get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            HomeItems note = em.find(HomeItems.class, id);
            // System.out.println("first name: " + note.getOwner().getFirstName());
            // get all notes of the same owner as that note
            // List<Note> notes = note.getOwner().getNoteList();
            return note;
        } finally { 
            em.close();
        }
    }

    public void insert(HomeItems hi) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Users user = hi.getOwner();
            user.getHomeItemsList().add(hi);
            trans.begin();
            em.persist(hi);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(HomeItems hi) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(hi);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(HomeItems hi) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Users user = hi.getOwner();
            user.getHomeItemsList().remove(hi);
            trans.begin();
            em.remove(em.merge(hi));
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
