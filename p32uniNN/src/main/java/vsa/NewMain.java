/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Kniha;
import entities.Osoba;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class NewMain {

    public static void main(String[] args) {
        Osoba autor1 = new Osoba();
        autor1.setMeno("Kernighan");
        Osoba autor2 = new Osoba();
        autor2.setMeno("Ritchey");
        Kniha kniha = new Kniha();
        kniha.setNazov("Jazyk C");
        kniha.setAutori(new ArrayList<>());
        kniha.getAutori().add(autor1);
        kniha.getAutori().add(autor2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {

            em.persist(kniha);
            em.persist(autor1);
            em.persist(autor2);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void persist(Object object) {
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
