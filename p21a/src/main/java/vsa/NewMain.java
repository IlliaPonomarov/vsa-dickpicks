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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author edu
 */
public class NewMain {
    
    private static final String ENTITY_MANAGER_NAME = "vsaPU";

    // Ilustruje generovany kluc
    public static void main(String[] args) {
        Person p;
        p = new Person(
                "Test",
                toLocalDate(2002, 2, 2),
                true
        );
        p.setId(1283L);
        persist(p);
        System.out.println("" + p.getId());
        
        final var existPerson = findById(p.getId()).get();
        
        System.out.println("Person:" + existPerson);
    }
    
    
    public static LocalDate toLocalDate(final int year, final int month, final int day) {
   
        final var calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
    
        return calendar.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
    private static Optional<Person> findById(final Long id) {
        final var emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
        final var em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        try {
            final var person = em.find(Person.class, id);
            
            if(person != null) {
                return Optional.ofNullable(person);
            }
        
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
        return Optional.empty();
    }

    private static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
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
