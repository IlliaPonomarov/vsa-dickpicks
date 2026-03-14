/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import entities.Osoba;
import entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;

/**
 *
 * @author edu
 */
public class NewMain {

    // Ilustruje generovany kluc
    public static void main(String[] args) {
        var emr = Persistence.createEntityManagerFactory("vsaPU");
        var em = emr.createEntityManager();
        final var osoba = new Osoba();
        
        osoba.setId(13745L);
        osoba.setMeno("Illia");
        osoba.setPlat(-1000L);
        osoba.setNarodeny(new Date());
        osoba.setZenaty(false);
        
        em.getTransaction().begin();
        em.persist(osoba);
        em.getTransaction().commit();

        em.close();
        
        emr = Persistence.createEntityManagerFactory("vsaPU");
        em = emr.createEntityManager();
        final var id = osoba.getId();
        em.getTransaction().begin();
        final var person = em.find(Person.class, id);
        final var exOsoba = em.find(Osoba.class, id);
        em.getTransaction().commit();
        em.close();
        
        System.out.println("Osoba: " + exOsoba );
        System.out.println("Person: " + person );

//        removeFero(id);
    }

    // ilustracia clear a detach
    public static long clearFero() {
        Person p;
        p = new Person();
        p.setName("Fero");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();    
        em.persist(p);
        em.getTransaction().commit();   

        long id = p.getId();
        
        em.clear();     // em.detach(p);

        Person p2 = em.find(Person.class, id);
        System.out.println("p == p2 :     " + (p == p2));
        System.out.println("p.equals(p2): " + p.equals(p2));

        em.close();
        return id;
    }
    
    // ilustruje refresh
    public static void refreshFero(long id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        Person p = em.find(Person.class, id);      
        System.out.println("" + p.getName() + " " + p.getSalary());

        p.setSalary(3000.0);
        System.out.println("" + p.getName() + " " + p.getSalary());

        em.refresh(p);
        System.out.println("" + p.getName() + " " + p.getSalary());

        em.close();
    }

    // ilustruje merge
    public static void mergeFero(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        
        // vytvorim kopiu
        Person p = new Person();
        p.setId(id);
        p.setName("Fero");
        // plat zmenim
        p.setSalary(333.33);

        // persist vyvola unique key violation
//        em.getTransaction().begin();
//        em.persist(p2);
//        em.getTransaction().commit();
//        System.out.println(p2);
        
        // merge
        em.getTransaction().begin();
        Person p2 = em.merge(p);
        em.getTransaction().commit();
        System.out.println(p2);
        
        System.out.println("p == p2 :     " + (p == p2));

        em.close();
    }

    // ilustruje getreference a remove
    // zapnite sql-log a pozrite rozdiel medzi find a getReference
    public static void removeFero(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();
        
        // nemusi inicializovat vsetky datove cleny 
        Person p = em.getReference(Person.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();

        em.close();
    }
}
