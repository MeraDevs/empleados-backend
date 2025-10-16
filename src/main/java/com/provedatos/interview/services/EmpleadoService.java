package com.provedatos.interview.services;

import java.util.List;

import com.provedatos.interview.models.entities.DatosLaborales;
import com.provedatos.interview.models.entities.Empleado;
import com.provedatos.interview.models.entities.Provincia;

public interface EmpleadoService {

    List<Empleado> listarTodo();

    Empleado obtenerEmpleadoPorId(Long id);

    DatosLaborales obtenerInfoLaboralPorEmpleado(Long id);

    Empleado agregarEmpleado(Empleado nuevoEmpleado);

    DatosLaborales agregarInfoLaboralEmpleado(Long empleadoId, DatosLaborales datosLaborales);

    List<Provincia> listarProvincias();
}
