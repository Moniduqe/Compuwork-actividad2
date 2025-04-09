import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteDesempenioTest {
    private ReporteDesempenio reporte;
    private List<Empleado> empleados;
    private EmpleadoPermanente empleadoPermanente1;
    private EmpleadoPermanente empleadoPermanente2;
    private EmpleadoTemporal empleadoTemporal;

    @BeforeEach
    void setUp() {
        reporte = new ReporteDesempenio("EP001", 85.5);
        empleados = new ArrayList<>();
        Date fechaInicio = new Date();
        Date fechaFin = new Date(fechaInicio.getTime() + 7776000000L);
        
        ReporteDesempenio reporte1 = new ReporteDesempenio("EP001", 85.5);
        ReporteDesempenio reporte2 = new ReporteDesempenio("EP002", 90.0);
        ReporteDesempenio reporte3 = new ReporteDesempenio("ET001", 95.5);
        
        empleadoPermanente1 = new EmpleadoPermanente("Carlos", "Gómez", "EP001", "Permanente", fechaInicio, reporte1);
        empleadoPermanente2 = new EmpleadoPermanente("Luis", "Ramírez", "EP002", "Permanente", fechaInicio, reporte2);
        empleadoTemporal = new EmpleadoTemporal("Ana", "Martínez", "ET001", "Temporal", fechaInicio, fechaFin, reporte3);
        
        empleados.add(empleadoPermanente1);
        empleados.add(empleadoPermanente2);
        empleados.add(empleadoTemporal);
    }

    @Test
    void testDatosBasicosReporte() {
        assertEquals("EP001", reporte.getEmpleadoId());
        assertEquals(85.5, reporte.getDesempenio(), 0.01);
    }

    @Test
    void testModificarDesempenio() {
        reporte.setDesempenio(95.0);
        assertEquals(95.0, reporte.getDesempenio(), 0.01);
    }

    @Test
    void testCalcularPromedioDesempenio() {
        double promedio = ReporteDesempenio.calcularPromedioDesempenio(empleados);
        // Promedio de (85.5 + 90.0 + 95.5) / 3 = 90.33
        assertEquals(90.33, promedio, 0.01);
    }

    @Test
    void testCalcularPromedioDesempenioSinReportes() {
        List<Empleado> empleadosSinReporte = new ArrayList<>();
        Date fechaInicio = new Date();
        empleadosSinReporte.add(new EmpleadoPermanente("Test", "Test", "TEST", "Permanente", fechaInicio, null));
        
        double promedio = ReporteDesempenio.calcularPromedioDesempenio(empleadosSinReporte);
        assertEquals(0.0, promedio, 0.01);
    }

    @Test
    void testCalcularPromedioDesempenioListaVacia() {
        List<Empleado> empleadosVacio = new ArrayList<>();
        double promedio = ReporteDesempenio.calcularPromedioDesempenio(empleadosVacio);
        assertEquals(0.0, promedio, 0.01);
    }
}
