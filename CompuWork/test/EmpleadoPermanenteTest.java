import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoPermanenteTest {
    private EmpleadoPermanente empleado;
    private Date fechaInicio;
    private ReporteDesempenio reporte;

    @BeforeEach
    void setUp() {
        fechaInicio = new Date();
        reporte = new ReporteDesempenio("EP002", 90.0);
        empleado = new EmpleadoPermanente("Carlos", "Gómez", "EP002", "Permanente", fechaInicio, reporte);
    }

    @Test
    void testCambiarDepartamentoValido() {
        empleado.cambiarDepartamento("Ventas");
    }

    @Test
    void testCambiarDepartamentoInvalido() {
        empleado.cambiarDepartamento("");
        empleado.cambiarDepartamento(null);
    }
}
