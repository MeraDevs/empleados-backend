package com.provedatos.interview.models.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "datos_laborales")
@Data
public class DatosLaborales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("fecha_ingreso")
    private LocalDate fechaIngreso;

    private String cargo;

    private String departamento;

    private float sueldo;

    @JsonProperty("jornada_parcial")
    private boolean jornadaParcial = false;

    private String observaciones;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    private Provincia provincia;

}
