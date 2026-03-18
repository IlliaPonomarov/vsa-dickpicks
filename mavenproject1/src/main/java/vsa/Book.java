/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsa;

import java.util.Objects;
import jakarta.persistence.*;

/**
 *
 * @author illiaponomarov
 */

@Entity
@Table(name = "book")
@NamedQueries({
     @NamedQuery(name = "Book.findByTitul", query = "SELECT b FROM Book b WHERE b.titul = :titul"),
     @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),

})
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISBN")
    private Long id;
    
    @Column(name = "titul", nullable = false, unique = true)
    private String titul;
    
    @Column(name = "dostupnost")
    @Enumerated(value = EnumType.STRING)
    private Dostupnost dostupnost;
    
    @Column(name = "cena")
    private Double cena;
    
    public Book(){}
   
    public Book(String titul) {
        this.titul = titul;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public Dostupnost getDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(Dostupnost dostupnost) {
        this.dostupnost = dostupnost;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.titul);
        hash = 53 * hash + Objects.hashCode(this.dostupnost);
        hash = 53 * hash + Objects.hashCode(this.cena);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.titul, other.titul)) {
            return false;
        }
        if (!Objects.equals(this.dostupnost, other.dostupnost)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.cena, other.cena);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", titul=" + titul + ", dostupnost=" + dostupnost + ", cena=" + cena + '}';
    }
    
    
}
