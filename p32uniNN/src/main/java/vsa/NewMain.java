package vsa;

import entities.Kniha;
import entities.Osoba;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class NewMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");

    static {
        Osoba kernighan = new Osoba();
        kernighan.setMeno("Kernighan");
        Osoba ritchey = new Osoba();
        ritchey.setMeno("Ritchey");
        Osoba gosling = new Osoba();
        gosling.setMeno("Gosling");

        Kniha jazykC = new Kniha();
        jazykC.setNazov("Jazyk C");
        jazykC.setAutori(new ArrayList<>());
        jazykC.getAutori().add(kernighan);
        jazykC.getAutori().add(ritchey);

        Kniha cProgramming = new Kniha();
        cProgramming.setNazov("The C Programming Language");
        cProgramming.setAutori(new ArrayList<>());
        cProgramming.getAutori().add(kernighan);

        Kniha javaBook = new Kniha();
        javaBook.setNazov("Java Programming");
        javaBook.setAutori(new ArrayList<>());
        javaBook.getAutori().add(gosling);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(kernighan);
            em.persist(ritchey);
            em.persist(gosling);
            em.persist(jazykC);
            em.persist(cProgramming);
            em.persist(javaBook);
            em.getTransaction().commit();

            System.out.println("=== Test data inserted ===");
            System.out.println("jazykC id: " + jazykC.getId());
            System.out.println("cProgramming id: " + cProgramming.getId());
            System.out.println("javaBook id: " + javaBook.getId());
            System.out.println("=========================");

            System.out.println("\n--- Test: nájdiAutorov ---");
            List<Osoba> autori = nájdiAutorov(jazykC.getId());
            System.out.println("Autori 'Jazyk C': " + (autori != null ? autori.stream().map(Osoba::getMeno).toList() : "null"));
            System.out.println("Autori neexistujucej knihy (id=999): " + nájdiAutorov(999L));

            System.out.println("\n--- Test: pocetKnih ---");
            System.out.println("Pocet knih Kernighan: " + pocetKnih("Kernighan"));
            System.out.println("Pocet knih Gosling: " + pocetKnih("Gosling"));
            System.out.println("Pocet knih neexistujuci: " + pocetKnih("Neexistuje"));

            System.out.println("\n--- Test: nájdiKnihy ---");
            List<Kniha> knihy = nájdiKnihy("Kernighan");
            System.out.println("Knihy Kernighan: " + (knihy != null ? knihy.stream().map(Kniha::getNazov).toList() : "null"));
            System.out.println("Knihy neexistujuci: " + nájdiKnihy("Neexistuje"));

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        // test data and method calls are in static block above
    }

    public static List<Osoba> nájdiAutorov(Long idKnihy) {
        EntityManager em = emf.createEntityManager();
        try {
            Kniha k = em.find(Kniha.class, idKnihy);
            if (k == null) return null;
            return new ArrayList<>(k.getAutori());
        } finally {
            em.close();
        }
    }

    public static int pocetKnih(String menoAutora) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Osoba> osoby = em.createQuery(
                    "SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class)
                    .setParameter("meno", menoAutora)
                    .getResultList();
            if (osoby.isEmpty()) return 0;
            Long count = em.createQuery(
                    "SELECT COUNT(k) FROM Kniha k JOIN k.autori a WHERE a.meno = :meno", Long.class)
                    .setParameter("meno", menoAutora)
                    .getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    public static List<Kniha> nájdiKnihy(String menoAutora) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Osoba> osoby = em.createQuery(
                    "SELECT o FROM Osoba o WHERE o.meno = :meno", Osoba.class)
                    .setParameter("meno", menoAutora)
                    .getResultList();
            if (osoby.isEmpty()) return null;
            return em.createQuery(
                    "SELECT k FROM Kniha k JOIN k.autori a WHERE a.meno = :meno", Kniha.class)
                    .setParameter("meno", menoAutora)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
