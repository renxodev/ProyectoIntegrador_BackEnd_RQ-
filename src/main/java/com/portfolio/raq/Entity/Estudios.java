package com.portfolio.raq.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Estudios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String descripcionE;
    
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String nombreE;

    //Constructor
    public Estudios() {
    }

    public Estudios(String descripcionE, String nombreE) {
        this.descripcionE = descripcionE;
        this.nombreE = nombreE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE (String descripcion) {
        this.descripcionE = descripcion;
    }

    public String getNombreE() {
        return nombreE;
    }
    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }
}
