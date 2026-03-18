/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsa;

import jakarta.persistence.Persistence;


/**
 *
 * @author illiaponomarov
 */
public class Main {
    
    
    public static void main(String... arg) {
       
        final var t = "fucikjjk8nhj0";
        var b = novaKniha(t);
        System.out.println("Creating new book: " + b);
        
        try {
          System.out.println("Dostupnost: " + zistiDostupnost(t));
  
        } catch(Exception ex) {
           System.out.println(ex.getMessage());
        }
        
        upravCeny();
        var f = getBookByTitle(t);
        System.out.println(f);
        
    }
    
    
    public static Long novaKniha(final String titul) {
        final var emr = Persistence.createEntityManagerFactory("projektPU");
        final var em = emr.createEntityManager();
        
        if(getBookByTitle(titul) != null) return null;
        
        try {
            em.getTransaction().begin();
           final var book = new Book();
        
            book.setTitul(titul);
            book.setCena(100.0);
            em.persist(book);
            em.getTransaction().commit();
            
            return book.getId();
        } catch(Exception ex) {
            System.out.println(ex);
            em.getTransaction().rollback();
            return null;
        } finally {      
          em.close();
        }
    }
    
    
    public static void upravCeny() {
        final var emr = Persistence.createEntityManagerFactory("projektPU");
        final var em = emr.createEntityManager();
        final var query = em.createNamedQuery("Book.findAll", Book.class);
        
        try {
            em.getTransaction().begin();
            query.getResultList().stream().forEach(b -> {
      var p = b.getCena();
      if (p != null) b.setCena(p * 0.2);
            });
            em.getTransaction().commit();
        
        } catch(Exception ex) {
        
           em.getTransaction().rollback();
        }

    }
    
    private static class NeznamaDostupnost extends Exception {
        
        public NeznamaDostupnost(final String message) {
           super(message);
        }
    }
    
    public static Dostupnost zistiDostupnost(String titul) throws Exception{
       final var book =  getBookByTitle(titul);
       
       if(book == null) {
          throw new NeznamaDostupnost("neznamy titul");
       }
       
       return book.getDostupnost();
    }
    
    public static Book getBookByTitle(final String titul) {
        final var emr = Persistence.createEntityManagerFactory("projektPU");
        final var em = emr.createEntityManager();
        final var query = em.createNamedQuery("Book.findByTitul", Book.class);
        
        query.setParameter("titul", titul);
        
        try {           
            return query.setMaxResults(1).getResultList().stream().findFirst().orElse(null);
        
        } catch(Exception ex) {
            System.out.println(ex);
            throw ex;
        } finally {      
          em.close();
        }
    }
    
}
