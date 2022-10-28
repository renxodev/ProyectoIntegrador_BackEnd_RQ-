package com.portfolio.raq.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ExperienciaLaboral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String descripcionEx;
    
    
    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String nombreEx;

    //Constructor
    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(String descripcionEx, String nombreEx) {
        this.descripcionEx = descripcionEx;
        this.nombreEx = nombreEx;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionEx() {
        return descripcionEx;
    }

    public void setDescripcionEx (String descripcionEx) {
        this.descripcionEx = descripcionEx;
    }

    public String getNombreEx() {
        return nombreEx;
    }

    public void setNombreEx(String nombreEx) {
        this.nombreEx = nombreEx;
    }
}
