/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Person> q = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> pl = q.getResultList();
        for (Person p : pl) {
            System.out.println(p.getName() + " zenaty=" + p.getMarried() + " narodeny=" + p.getBorn());
        }

        TypedQuery<Person> q1 = em.createNamedQuery("Person.findByName", Person.class);
        q1.setParameter("name", "Fero");
        for (Person p : q1.getResultList()) {
            System.out.println(p.getName() + " zenaty=" + p.getMarried() + " narodeny=" + p.getBorn());
        }

        TypedQuery<Person> q2 = em.createQuery("select p from Person p where p.born is null", Person.class);
        q2.getResultList().forEach(p -> {
            System.out.println(p.getName() + " zenaty=" + p.getMarried() + " narodeny=" + p.getBorn());
        });

        TypedQuery<Long> q3 = em.createQuery("select count(p) from Person p where p.salary < 1000", Long.class);
        System.out.println("" + q3.getSingleResult());
        em.close();
    }
    
}
