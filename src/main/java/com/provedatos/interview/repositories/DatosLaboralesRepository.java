package com.provedatos.interview.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provedatos.interview.models.entities.DatosLaborales;

public interface DatosLaboralesRepository extends JpaRepository<DatosLaborales, Long> {

    Optional<DatosLaborales> findByEmpleadoId(Long id);
}
