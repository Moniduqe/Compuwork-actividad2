import java.util.Date;

public class EmpleadoPermanente extends Empleado{

    public EmpleadoPermanente(String nombre, String apellido, String id,String tipoEmpleado ,Date fechaInicio, ReporteDesempenio reporteDesempenio) {
        super(nombre, apellido, id, tipoEmpleado, fechaInicio, reporteDesempenio);
    }

    @Override
    public void cambiarDepartamento(String nuevoDepartamento) {
        if (nuevoDepartamento == null || nuevoDepartamento.isEmpty()) {
            System.out.println("El nuevo departamento no puede ser nulo o vacío");
            return;
        }
        //setDepartamento(nuevoDepartamento);
    }
}
