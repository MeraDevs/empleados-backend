package com.provedatos.interview.services.implement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.provedatos.interview.models.entities.DatosLaborales;
import com.provedatos.interview.models.entities.Empleado;
import com.provedatos.interview.models.entities.Provincia;
import com.provedatos.interview.repositories.DatosLaboralesRepository;
import com.provedatos.interview.repositories.EmpleadoRepository;
import com.provedatos.interview.repositories.ProvinciaRepository;
import com.provedatos.interview.services.EmpleadoService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DatosLaboralesRepository datosLaboralesRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;


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
    @Transactional
    public Empleado agregarEmpleado(Empleado nuevoEmpleado, MultipartFile fotoPerfil) {
        // Asegurar que es un nuevo empleado sin ID
        if (nuevoEmpleado.getId() != null) {
            throw new IllegalArgumentException("El nuevo empleado no debe tener un ID asignado.");
        }
        
        // Procesar la foto de perfil si se proporciona
        if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
            try {
                byte[] fotoPerfilBytes = fotoPerfil.getBytes();
                nuevoEmpleado.setFotoPerfil(fotoPerfilBytes);
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar la foto del perfil", e);
            }
        }

        // Guardar el empleado
        Empleado empleadoGuardado = empleadoRepository.save(nuevoEmpleado);
        
        // Asegurar que el ID se asigna correctamente
        if (empleadoGuardado.getId() == null) {
            throw new RuntimeException("Error al asignar ID al empleado");
        }
        
        return empleadoGuardado;
    }

    @Override
    @Transactional
    public DatosLaborales agregarInfoLaboralEmpleado(Long empleadoId, DatosLaborales datosLaborales) {
        // Verificar si ya tiene datos laborales (solo si la relación es 1:1)
        Optional<DatosLaborales> existente = datosLaboralesRepository.findByEmpleadoId(empleadoId);
        if (existente.isPresent()) {
            throw new IllegalStateException(
                    "El empleado con id " + empleadoId + " ya tiene datos laborales registrados.");
        }

        // Buscar el empleado y asegurar que esté en el contexto de persistencia
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + empleadoId));

        // Asegurar que datosLaborales no tenga ID (es un nuevo registro)
        datosLaborales.setId(null);
        
        // Establecer la relación
        datosLaborales.setEmpleado(empleado);
        
        return datosLaboralesRepository.save(datosLaborales);
    }

    @Override
    public List<Provincia> listarProvincias() {
        return provinciaRepository.findAll();
    }

}
