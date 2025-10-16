package com.provedatos.interview.models.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "empleado")
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("primer_nombre")
    @Column(name = "primer_nombre")
    private String primerNombre;

    @JsonProperty("segundo_nombre")
    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @JsonProperty("primer_apellido")
    @Column(name = "primer_apellido")
    private String primerApellido;

    @JsonProperty("segundo_apellido")
    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Email
    @NotBlank
    private String email;

    private String codigo;

    private String cedula;

    @JsonProperty("fecha_nacimiento")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String observaciones;

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "LONGBLOB")
    private byte[] avatar;

    @ManyToOne
    private Provincia provincia;
}