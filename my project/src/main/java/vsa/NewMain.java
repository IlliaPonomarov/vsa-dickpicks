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
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.stream.IntStream;

/**
 *
 * @author edu
 */
public class NewMain {

    // Ilustruje generovany kluc
    public static void main(String[] args) {
        var emr = Persistence.createEntityManagerFactory("vsaPU");
        var em = emr.createEntityManager();
        
        IntStream.range(0, 100).forEach(i -> {
                    final var osoba = new Osoba();
                    final var id = Long.valueOf(39000 + i);
                    final var plat = Long.valueOf(800 + i);
                    osoba.setId(id);
                    osoba.setMeno("Illia");
                    osoba.setPlat(plat);
                    osoba.setNarodeny(new Date());
                    osoba.setZenaty(false);
                    persist(osoba, em);          
        });
        
        // Retrive all Osobs
        final TypedQuery<Osoba> allOsobs = em.createNamedQuery("Osoba.findAll", Osoba.class);
        
        allOsobs.getResultStream().forEach(p -> {
                    final var newOsoba = p;
                    newOsoba.setPlat(newOsoba.getPlat() + 100);
                    persist(newOsoba, em);          
        });
        
        final TypedQuery<Osoba> allOsobsLowerThenNSalaryQuery = em.createNamedQuery("Osoba.findBySalary", Osoba.class);
        allOsobsLowerThenNSalaryQuery.setParameter("plat", 1000.0);
        allOsobsLowerThenNSalaryQuery.getResultStream().forEach(System.out::println);
        
        final TypedQuery<Double> sumOfSalary = em.createNamedQuery("Osoba.salarySum", Double.class);
        final var sumOfAllSalaries = sumOfSalary.getSingleResult();
        
        System.out.println("Sum of all salaries of osobs: " + sumOfAllSalaries);
        
        em.close();
    }
    
    
    
    public static void cv25() {
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
     
    private static void persist(final Osoba osoba, final EntityManager em) {
        
        try{
            em.getTransaction().begin();
            em.persist(osoba);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();
        }   
    }
}
