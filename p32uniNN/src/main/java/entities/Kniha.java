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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author igor
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Kniha.findAll", query = "SELECT k FROM Kniha k")
})
public class Kniha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazov;
    
    @ManyToMany
    @JoinTable(
      name = "knihaautor",
      joinColumns = @JoinColumn(name = "kniha_id"),
      inverseJoinColumns = @JoinColumn(name = "osoba_id")
    )
    private List<Osoba> autori;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Osoba> getAutori() {
        return autori;
    }

    public void setAutori(List<Osoba> autori) {
        this.autori = autori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kniha)) {
            return false;
        }
        Kniha other = (Kniha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "p4app2.Kniha[ id=" + id + " ]";
    }

}
