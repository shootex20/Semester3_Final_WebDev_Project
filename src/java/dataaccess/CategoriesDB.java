/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.Categories;


/**
 *
 * @author 813017
 */
public class CategoriesDB {
    
          public List<Categories> getAll() throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
          TypedQuery<Categories> query = em.createNamedQuery("Categories.findAll", Categories.class);
         List<Categories> results = query.getResultList();
            return results;
    }
    public int insert(Categories cat) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(cat);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
            return 1;
        
        }
    }
    public int delete(Categories cat) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(cat));
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
            return 1;
        }
    }
        public int update(Categories cat) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
       try {
            trans.begin();
            em.merge(cat);
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
            return 1;
        } 
    }
    
        public Categories getCategory(int catID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Categories user = em.find(Categories.class, catID);
            return user;
        } finally {
            em.close();
        }
    }
    
}
