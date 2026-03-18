/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vsa;

/**
 *
 * @author illiaponomarov
 */
public enum Dostupnost {
    
    NASKLADE("NASKLADE"),
    IHNED("IHNED"),
    DO5DNI("DO5DNI");
    
    private String type;
    
    Dostupnost(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dostupnost{" + "ordinal=" + ordinal() + ", name=" + name() + ", type=" + type + '}';
    }
    
    
    
}
