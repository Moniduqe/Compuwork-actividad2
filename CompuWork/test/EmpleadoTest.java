import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {
    private EmpleadoPermanente empleadoPermanente;
    private EmpleadoTemporal empleadoTemporal;
    private Date fechaInicio;
    private Date fechaFin;

    @BeforeEach
    void setUp() {
        fechaInicio = new Date();
        fechaFin = new Date(fechaInicio.getTime() + 7776000000L); // 90 días después
        ReporteDesempenio reportePermanente = new ReporteDesempenio("EP001", 85.0);
        ReporteDesempenio reporteTemporal = new ReporteDesempenio("ET001", 87.0);
        empleadoPermanente = new EmpleadoPermanente("Juan", "Pérez", "EP001", "Permanente", fechaInicio, reportePermanente);
        empleadoTemporal = new EmpleadoTemporal("María", "López", "ET001", "Temporal", fechaInicio, fechaFin, reporteTemporal);
    }

    @Test
    void testDatosBasicosEmpleadoPermanente() {
        assertEquals("Juan", empleadoPermanente.getNombre());
        assertEquals("Pérez", empleadoPermanente.getApellido());
        assertEquals("EP001", empleadoPermanente.getId());
        assertEquals("Permanente", empleadoPermanente.getTipoEmpleado());
        assertEquals(fechaInicio, empleadoPermanente.getFechaInicio());
    }

    @Test
    void testDatosBasicosEmpleadoTemporal() {
        assertEquals("María", empleadoTemporal.getNombre());
        assertEquals("López", empleadoTemporal.getApellido());
        assertEquals("ET001", empleadoTemporal.getId());
        assertEquals("Temporal", empleadoTemporal.getTipoEmpleado());
        assertEquals(fechaInicio, empleadoTemporal.getFechaInicio());
        assertEquals(fechaFin, empleadoTemporal.getFechaFin());
    }

    @Test
    void testCambioTipoEmpleado() {
        empleadoPermanente.cambiarTipoEmpleado("Gerente");
        assertEquals("Gerente", empleadoPermanente.getTipoEmpleado());
    }

    @Test
    void testSetReporteDesempenio() {
        ReporteDesempenio nuevoReporte = new ReporteDesempenio("EP001", 95.0);
        empleadoPermanente.setReporteDesempenio(nuevoReporte);
        assertEquals(nuevoReporte, empleadoPermanente.getReporteDesempenio());
    }
}
