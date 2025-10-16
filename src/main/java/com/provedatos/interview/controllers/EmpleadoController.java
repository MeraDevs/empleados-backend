package com.provedatos.interview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.provedatos.interview.models.entities.DatosLaborales;
import com.provedatos.interview.models.entities.Empleado;
import com.provedatos.interview.services.EmpleadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("app/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos() {
        List<Empleado> empleados = empleadoService.listarTodo();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/personal-info/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);

        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/work-info/{empleadoId}")
    public ResponseEntity<DatosLaborales> obtenerInfoLaboral(@PathVariable Long empleadoId) {
        DatosLaborales datosLaborales = empleadoService.obtenerInfoLaboralPorEmpleado(empleadoId);

        return ResponseEntity.ok(datosLaborales);
    }

    @PostMapping("/nuevo-empleado")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado nuevoEmpleado) {
        Empleado empleado = empleadoService.agregarEmpleado(nuevoEmpleado);

        return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
    }

    @PostMapping("/{empleadoId}/nueva-info-laboral")
    public ResponseEntity<DatosLaborales> agregarInfoLaboralEmpleado(
            @PathVariable Long empleadoId,
            @RequestBody DatosLaborales nuevosDatosLaborales) {
        DatosLaborales datosLaborales = empleadoService.agregarInfoLaboralEmpleado(empleadoId, nuevosDatosLaborales);

        return ResponseEntity.status(HttpStatus.CREATED).body(datosLaborales);
    }

}
