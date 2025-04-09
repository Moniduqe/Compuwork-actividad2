import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTemporalTest {
    private EmpleadoTemporal empleado;
    private Date fechaInicio;
    private Date fechaFin;
    private ReporteDesempenio reporte;

    @BeforeEach
    void setUp() {
        fechaInicio = new Date();
        fechaFin = new Date(fechaInicio.getTime() + 7776000000L); // 90 días después
        reporte = new ReporteDesempenio("ET002", 88.0);
        empleado = new EmpleadoTemporal("Ana", "Martínez", "ET002", "Temporal", fechaInicio, fechaFin, reporte);
    }

    @Test
    void testFechaFinCorrecta() {
        assertEquals(fechaFin, empleado.getFechaFin());
    }

    @Test
    void testModificarFechaFin() {
        Date nuevaFechaFin = new Date(fechaFin.getTime() + 2592000000L); // 30 días más
        empleado.setFechaFin(nuevaFechaFin);
        assertEquals(nuevaFechaFin, empleado.getFechaFin());
    }

    @Test
    void testCambiarDepartamentoValido() {
        empleado.cambiarDepartamento("Marketing");
    }

    @Test
    void testCambiarDepartamentoInvalido() {
        empleado.cambiarDepartamento("");
        empleado.cambiarDepartamento(null);
    }
}
