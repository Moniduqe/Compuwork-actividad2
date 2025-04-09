import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoTest {
    private Departamento departamento;
    private EmpleadoPermanente empleadoPermanente;
    private EmpleadoTemporal empleadoTemporal;

    @BeforeEach
    void setUp() {
        departamento = new Departamento("D001", "Recursos Humanos", "Departamento de RRHH", "Juan Pérez");
        Date fechaInicio = new Date();
        Date fechaFin = new Date(fechaInicio.getTime() + 7776000000L);
        ReporteDesempenio reporte1 = new ReporteDesempenio("EP001", 85.5);
        ReporteDesempenio reporte2 = new ReporteDesempenio("ET001", 90.0);
        
        empleadoPermanente = new EmpleadoPermanente("Carlos", "Gómez", "EP001", "Permanente", fechaInicio, reporte1);
        empleadoTemporal = new EmpleadoTemporal("Ana", "Martínez", "ET001", "Temporal", fechaInicio, fechaFin, reporte2);
    }

    @Test
    void testDatosBasicosDepartamento() {
        assertEquals("D001", departamento.getId());
        assertEquals("Recursos Humanos", departamento.getNombreDepartamento());
        assertEquals("Departamento de RRHH", departamento.getDescripcion());
        assertEquals("Juan Pérez", departamento.getJefe());
    }

    @Test
    void testAgregarEmpleado() {
        departamento.agregarEmpleado(empleadoPermanente);
        assertTrue(departamento.getEmpleados().contains(empleadoPermanente));
        assertEquals(1, departamento.getEmpleados().size());

        departamento.agregarEmpleado(empleadoTemporal);
        assertTrue(departamento.getEmpleados().contains(empleadoTemporal));
        assertEquals(2, departamento.getEmpleados().size());
    }

    @Test
    void testEliminarEmpleado() throws Exception {
        departamento.agregarEmpleado(empleadoPermanente);
        departamento.agregarEmpleado(empleadoTemporal);
        
        departamento.eliminarEmpleado("EP001");
        assertFalse(departamento.getEmpleados().contains(empleadoPermanente));
        assertEquals(1, departamento.getEmpleados().size());
    }

    @Test
    void testEliminarEmpleadoNoExistente() {
        departamento.agregarEmpleado(empleadoPermanente);
        
        Exception exception = assertThrows(Exception.class, () -> {
            departamento.eliminarEmpleado("ID_NO_EXISTE");
        });
        
        assertEquals("Empleado no encontrado, favor validar id ingresado", exception.getMessage());
    }
}
