package com.provedatos.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provedatos.interview.models.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

}
