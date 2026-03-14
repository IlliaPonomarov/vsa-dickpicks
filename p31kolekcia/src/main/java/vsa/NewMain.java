/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Kniha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final var id = create();
        final var emr = Persistence.createEntityManagerFactory("vsaPU");
        final var em = emr.createEntityManager();
        
        final var kniha = em.find(Kniha.class, id);
        
        System.out.println("Kniha: " + kniha);
        final var queryByAuthor = em.createNamedQuery("Kniha.findKnihaByAuthor", Kniha.class);
      
        queryByAuthor.setParameter("author", "Ritchey");
        // Kniha with ritchey as auuthor
        queryByAuthor.getResultStream().forEach(System.out::println);
        
        em.close();
        
    }
    static long create() {
        Kniha k = new Kniha();
        k.setId(59665L);
        k.setNazov("Jazyk C");
        k.setAutori(new ArrayList<>());
        k.getAutori().add("Kernighan");
        k.getAutori().add("Ritchey");
        
        persist(k);
        
        return k.getId();
    }

    static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
