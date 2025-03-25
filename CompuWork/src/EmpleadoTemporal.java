import java.util.Date;

public class EmpleadoTemporal extends Empleado{
    //Atributos
    private Date fechaFin;

    //Constructor
    public EmpleadoTemporal(String nombre, String apellido, String id, String tipoEmpleado, Date fechaInicio, Date fechaFin, ReporteDesempenio reporteDesempenio) {
        super(nombre, apellido, id, tipoEmpleado, fechaInicio, reporteDesempenio);
        this.fechaFin = fechaFin;
    }

    //Getters y Setters
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    //Métodos
    //Implementación del método abstracto
    @Override
    public void cambiarDepartamento(String nuevoDepartamento) {
        if (nuevoDepartamento == null || nuevoDepartamento.isEmpty()) {
            System.out.println("El nuevo departamento no puede ser nulo o vacío");
            return;
        }
        //setDepartamento(nuevoDepartamento);
    }

}
