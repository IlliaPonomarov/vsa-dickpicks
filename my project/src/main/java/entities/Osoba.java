/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author illiaponomarov
 */
@Table(name = "Person")
@Entity
public class Osoba {
    @Id
    private Long id;
    
    @Column(name = "name")
    private String meno;
    
    @Column(name = "salary")
    private double plat;
    
    @Column(name = "born")
    private Date narodeny;
    
    @Column(name = "married")
    private boolean zenaty;
    
    public Osoba(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public double getPlat() {
        return plat;
    }

    public void setPlat(double plat) {
        this.plat = plat;
    }

    public Date getNarodeny() {
        return narodeny;
    }

    public void setNarodeny(Date narodeny) {
        this.narodeny = narodeny;
    }

    public boolean isZenaty() {
        return zenaty;
    }

    public void setZenaty(boolean zenaty) {
        this.zenaty = zenaty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.meno);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.plat) ^ (Double.doubleToLongBits(this.plat) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.narodeny);
        hash = 83 * hash + (this.zenaty ? 1 : 0);
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
        if (Double.doubleToLongBits(this.plat) != Double.doubleToLongBits(other.plat)) {
            return false;
        }
        if (this.zenaty != other.zenaty) {
            return false;
        }
        if (!Objects.equals(this.meno, other.meno)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.narodeny, other.narodeny);
    }

    @Override
    public String toString() {
        return "Osoba{" + "id=" + id + ", meno=" + meno + ", plat=" + plat + ", narodeny=" + narodeny + ", zenaty=" + zenaty + '}';
    }
    
    
}
