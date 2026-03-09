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

/**
 *
 * @author edu
 */
public class NewMain {

    // Ilustruje generovany kluc
    public static void main(String[] args) {
        verzia1();
//        verzia2();
    }

    // Nasledujuce verzie aplikacie ilustruju persistence context, 
    // managovane objekty a metodu equals
    public static void verzia1() {
        Person p;
        p = new Person();
        p.setName("Fero");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();    // skuste zakomentovat
        em.persist(p);
        em.getTransaction().commit();   // skuste zakomentovat

        long id = p.getId();
        System.out.println("id: " + id);

        Person p2 = em.find(Person.class, id);
        System.out.println("p == p2 :     " + (p == p2));
        System.out.println("p.equals(p2): " + p.equals(p2));

        em.close();
    }

    // To iste co verzia1 ale s pouzitim vlastneho entity managera pre persist aj find
    public static void verzia2() {
        Person p;
        p = new Person();
        p.setName("Fero");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();    // skuste zakomentovat
        em.persist(p);
        em.getTransaction().commit();   // skuste zakomentovat

        long id = p.getId();
        System.out.println("id: " + id);
        
        em.close();

        em = emf.createEntityManager();
        Person p2 = em.find(Person.class, id);
        System.out.println("p == p2 :     " + (p == p2));
        System.out.println("p.equals(p2): " + p.equals(p2));
                
        em.close();
    }
}
