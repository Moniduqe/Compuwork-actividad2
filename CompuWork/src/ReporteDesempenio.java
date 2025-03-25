import java.util.List;

public class ReporteDesempenio {
    private String empleadoId;
    private double desempenio; // Un valor entre 0 y 100


    public ReporteDesempenio(String empleadoId, double desempenio) {
        this.empleadoId = empleadoId;
        this.desempenio = desempenio;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public double getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(double desempenio) {
        this.desempenio = desempenio;
    }



    public static double calcularPromedioDesempenio(List<Empleado> empleados) {
        double suma = 0;
        int count = 0;

        for (Empleado empleado : empleados) {
            if (null != empleado.getReporteDesempenio()) {
                suma += empleado.getReporteDesempenio().getDesempenio();
                count++;
            }
        }

        return count > 0 ? suma / count : 0;
    }
}