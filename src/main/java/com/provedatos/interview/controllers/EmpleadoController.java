package com.provedatos.interview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.provedatos.interview.models.entities.DatosLaborales;
import com.provedatos.interview.models.entities.Empleado;
import com.provedatos.interview.models.entities.Provincia;
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

    @GetMapping("/provincias")
    public ResponseEntity<List<Provincia>> listarProvincias() {
        List<Provincia> provincias = empleadoService.listarProvincias();

        return ResponseEntity.ok(provincias);
    }

    @PostMapping(value = "/nuevo-empleado", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Empleado> agregarEmpleado(
            @RequestPart("empleado") Empleado nuevoEmpleado,
            @RequestPart(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {
        Empleado empleado = empleadoService.agregarEmpleado(nuevoEmpleado, fotoPerfil);

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
