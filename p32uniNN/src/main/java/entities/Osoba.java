/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author igor
 */
@Entity
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String meno;
    
    @ManyToMany(mappedBy = "autori")
    private List<Kniha> knihas;

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Kniha> getKnihas() {
        return knihas;
    }

    public void setKnihas(List<Kniha> knihas) {
        this.knihas = knihas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.meno);
        hash = 29 * hash + Objects.hashCode(this.knihas);
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
        final Osoba other = (Osoba) obj;
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.knihas, other.knihas);
    }

    @Override
    public String toString() {
        return "Osoba{" + "id=" + id + ", meno=" + meno + ", knihas=" + knihas + '}';
    }

    
    
}
