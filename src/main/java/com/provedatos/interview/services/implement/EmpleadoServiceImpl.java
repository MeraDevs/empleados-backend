package com.provedatos.interview.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provedatos.interview.models.entities.DatosLaborales;
import com.provedatos.interview.models.entities.Empleado;
import com.provedatos.interview.repositories.DatosLaboralesRepository;
import com.provedatos.interview.repositories.EmpleadoRepository;
import com.provedatos.interview.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DatosLaboralesRepository datosLaboralesRepository;

    @Override
    public List<Empleado> listarTodo() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public DatosLaborales obtenerInfoLaboralPorEmpleado(Long id) {
        return datosLaboralesRepository.findByEmpleadoId(id)
                .orElse(null);
    }

    @Override
    public Empleado agregarEmpleado(Empleado nuevoEmpleado) {
        if (nuevoEmpleado.getId() != null) {
            throw new IllegalArgumentException("El nuevo empleado no debe tener un ID asignado.");
        }

        return empleadoRepository.save(nuevoEmpleado);
    }

    @Override
    public DatosLaborales agregarInfoLaboralEmpleado(Long empleadoId, DatosLaborales datosLaborales) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + empleadoId));

        // Verificar si ya tiene datos laborales (solo si la relación es 1:1)
        Optional<DatosLaborales> existente = datosLaboralesRepository.findByEmpleadoId(empleadoId);
        if (existente.isPresent()) {
            throw new IllegalStateException(
                    "El empleado con id " + empleadoId + " ya tiene datos laborales registrados.");
        }

        datosLaborales.setEmpleado(empleado);
        return datosLaboralesRepository.save(datosLaborales);
    }

}
