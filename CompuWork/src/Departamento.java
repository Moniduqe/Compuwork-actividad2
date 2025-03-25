import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombreDepartamento;
    private String descripcion;
    private String jefe;
    private ArrayList<Empleado> empleados;

    public Departamento(String id, String nombreDepartamento, String descripcion, String jefe) {
        this.id = id;
        this.nombreDepartamento = nombreDepartamento;
        this.descripcion = descripcion;
        this.jefe = jefe;
        this.empleados = new ArrayList<>();
    }
    // Getters y Setters
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public List<Empleado> getEmpleados() {
        return empleados;  // Suponiendo que 'empleados' es una lista de los empleados asignados al departamento
    }


    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    // Agregar un empleado al departamento
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    // Eliminar un empleado del departamento
    public void eliminarEmpleado(String id) throws Exception {
        for (Empleado e : empleados) {
            if (e.getId() == 
                    id) {
                empleados.remove(e);
                return;
            }
        }
        throw new Exception("Empleado no encontrado, favor validar id ingresado");
    }
}
