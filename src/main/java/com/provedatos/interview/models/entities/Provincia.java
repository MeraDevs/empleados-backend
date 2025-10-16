package com.provedatos.interview.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "provincia")
@Data
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre_provincia")
    private String nombreProvincia;

    @JsonProperty("capital_provincia")
    private String capitalProvincia;

    @JsonProperty("descripcion_provincia")
    @Column(name = "descripcion_provincia", columnDefinition = "TEXT")
    private String descripcionProvincia;

    @JsonProperty("poblacion_provincia")
    private String poblacionProvincia;

    @JsonProperty("superficie_provincia")
    private String superficieProvincia;

    @JsonProperty("latitud_provincia")
    private String latitudProvincia;

    @JsonProperty("longitud_provincia")
    private String longitudProvincia;

    @JsonProperty("id_region")
    private String idRegion;

}
