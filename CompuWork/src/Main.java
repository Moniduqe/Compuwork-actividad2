import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {
    private static List<Empleado> empleados = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Gestión de Empleados y Departamentos");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel panel = new JPanel(new BorderLayout());
                textArea = new JTextArea();
                textArea.setEditable(false);
                panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

                JMenuBar menuBar = new JMenuBar();

                // Menú de Empleados con Icono
                JMenu menuEmpleados = new JMenu("Empleados");
                ImageIcon empleadoIcon = new ImageIcon(Main.class.getResource("/empleado_icon.png")); // Reemplaza con tu icono
                menuEmpleados.setIcon(empleadoIcon);

                JMenuItem crearItem = new JMenuItem("Crear Empleado");
                JMenuItem actualizarItem = new JMenuItem("Actualizar Empleado");
                JMenuItem eliminarItem = new JMenuItem("Eliminar Empleado");
                JMenuItem mostrarItem = new JMenuItem("Mostrar Empleados");
                JMenuItem actualizarDesempenioItem = new JMenuItem("Actualizar Desempeño");

                crearItem.addActionListener(e -> crearEmpleado());
                actualizarItem.addActionListener(e -> actualizarEmpleado());
                eliminarItem.addActionListener(e -> eliminarEmpleado());
                mostrarItem.addActionListener(e -> mostrarEmpleados());
                actualizarDesempenioItem.addActionListener(e -> actualizarDesempenio());

                menuEmpleados.add(crearItem);
                menuEmpleados.add(actualizarItem);
                menuEmpleados.add(eliminarItem);
                menuEmpleados.add(mostrarItem);
                menuEmpleados.add(actualizarDesempenioItem);

                // Menú de Departamentos con Icono
                JMenu menuDepartamentos = new JMenu("Departamentos");
                ImageIcon departamentoIcon = new ImageIcon(Main.class.getResource("/departamento_icon.png")); // Reemplaza con tu icono
                menuDepartamentos.setIcon(departamentoIcon);

                JMenuItem crearDepartamentoItem = new JMenuItem("Crear Departamento");
                JMenuItem actualizarDepartamentoItem = new JMenuItem("Actualizar Departamento");
                JMenuItem eliminarDepartamentoItem = new JMenuItem("Eliminar Departamento");
                JMenuItem asignarEmpleadoItem = new JMenuItem("Asignar Empleado a Departamento");
                JMenuItem mostrarDepartamentosItem = new JMenuItem("Mostrar Departamentos");

                crearDepartamentoItem.addActionListener(e -> crearDepartamento());
                actualizarDepartamentoItem.addActionListener(e -> actualizarDepartamento());
                eliminarDepartamentoItem.addActionListener(e -> eliminarDepartamento());
                asignarEmpleadoItem.addActionListener(e -> asignarEmpleadoADepartamento());
                mostrarDepartamentosItem.addActionListener(e -> mostrarDepartamentos());

                menuDepartamentos.add(crearDepartamentoItem);
                menuDepartamentos.add(actualizarDepartamentoItem);
                menuDepartamentos.add(eliminarDepartamentoItem);
                menuDepartamentos.add(asignarEmpleadoItem);
                menuDepartamentos.add(mostrarDepartamentosItem);

                // Menú de Reportes con Icono
                JMenu menuReportes = new JMenu("Reportes");
                ImageIcon reporteIcon = new ImageIcon(Main.class.getResource("/reporte_icon.png")); // Reemplaza con tu icono
                menuReportes.setIcon(reporteIcon);

                JMenuItem generarReporteItem = new JMenuItem("Generar Reporte");
                generarReporteItem.addActionListener(e -> generarReporte());
                menuReportes.add(generarReporteItem);

                menuBar.add(menuEmpleados);
                menuBar.add(menuDepartamentos);
                menuBar.add(menuReportes);

                frame.setJMenuBar(menuBar);
                frame.add(panel);
                frame.setVisible(true);
            }
        });
    }
    private static void crearEmpleado() {
        String[] opciones = {"Permanente", "Temporal"};
        String tipoEmpleado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de empleado:", "Crear Empleado", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (tipoEmpleado != null) {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Nombre:"));
            JTextField nombreField = new JTextField();
            panel.add(nombreField);
            panel.add(new JLabel("Apellido:"));
            JTextField apellidoField = new JTextField();
            panel.add(apellidoField);
            panel.add(new JLabel("ID:"));
            JTextField idField = new JTextField();
            panel.add(idField);

            int result = JOptionPane.showOptionDialog(null, panel, "Ingrese los datos del empleado", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Cancelar"}, "Aceptar");
            if (result == JOptionPane.OK_OPTION) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String id = idField.getText();

                if (tipoEmpleado.equals("Permanente")) {
                    EmpleadoPermanente empleado = new EmpleadoPermanente(nombre, apellido, id, "Permanente", new Date(), new ReporteDesempenio(id, 0)); // Inicializar con 0
                    empleados.add(empleado);
                } else if (tipoEmpleado.equals("Temporal")) {
                    Date fechaFin = new Date(); // Puedes modificar esto para leer una fecha específica
                    EmpleadoTemporal empleado = new EmpleadoTemporal(nombre, apellido, id, "Temporal", new Date(), fechaFin, new ReporteDesempenio(id, 0)); // Inicializar con 0
                    empleados.add(empleado);
                }
            }
        }
    }

    private static void actualizarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado a actualizar:");
        Empleado empleado = buscarEmpleadoPorId(id);

        if (empleado != null) {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Nombre:"));
            JTextField nombreField = new JTextField(empleado.getNombre());
            panel.add(nombreField);
            panel.add(new JLabel("Apellido:"));
            JTextField apellidoField = new JTextField(empleado.getApellido());
            panel.add(apellidoField);

            int result = JOptionPane.showOptionDialog(null, panel, "Actualizar Empleado", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Cancelar"}, "Aceptar");
            if (result == JOptionPane.OK_OPTION) {
                empleado.setNombre(nombreField.getText());
                empleado.setApellido(apellidoField.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private static void eliminarEmpleado() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:");
        Empleado empleado = buscarEmpleadoPorId(id);

        if (empleado != null) {
            empleados.remove(empleado);
            JOptionPane.showMessageDialog(null, "Empleado eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private static void mostrarEmpleados() {
        StringBuilder sb = new StringBuilder();
        for (Empleado empleado : empleados) {
            sb.append(empleado.getId()).append(" - ").append(empleado.getNombre()).append(" ").append(empleado.getApellido()).append(" - ").append(empleado.getTipoEmpleado()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private static Empleado buscarEmpleadoPorId(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    private static void crearDepartamento() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        panel.add(nombreField);
        panel.add(new JLabel("Descripción:"));
        JTextField descripcionField = new JTextField();
        panel.add(descripcionField);
        panel.add(new JLabel("Jefe:"));
        JTextField jefeField = new JTextField();
        panel.add(jefeField);

        int result = JOptionPane.showOptionDialog(null, panel, "Ingrese los datos del departamento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Cancelar"}, "Aceptar");
        if (result == JOptionPane.OK_OPTION) {
            String idDepartamento = idField.getText();
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            String jefe = jefeField.getText();
            Departamento departamento = new Departamento(idDepartamento, nombre, descripcion, jefe);
            departamentos.add(departamento);
        }
    }

    private static void actualizarDepartamento() {
        String idDepartamento = JOptionPane.showInputDialog("Ingrese el ID del departamento a actualizar:");
        Departamento departamento = buscarDepartamentoPorId(idDepartamento);

        if (departamento != null) {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Nombre:"));
            JTextField nombreField = new JTextField(departamento.getNombreDepartamento());
            panel.add(nombreField);
            panel.add(new JLabel("Descripción:"));
            JTextField descripcionField = new JTextField(departamento.getDescripcion());
            panel.add(descripcionField);
            panel.add(new JLabel("Jefe:"));
            JTextField jefeField = new JTextField(departamento.getJefe());
            panel.add(jefeField);

            int result = JOptionPane.showOptionDialog(null, panel, "Actualizar Departamento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Cancelar"}, "Aceptar");
            if (result == JOptionPane.OK_OPTION) {
                departamento.setNombreDepartamento(nombreField.getText());
                departamento.setDescripcion(descripcionField.getText());
                departamento.setJefe(jefeField.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
        }
    }

    private static void eliminarDepartamento() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del departamento a eliminar:");
        Departamento departamento = buscarDepartamentoPorId(id);

        if (departamento != null) {
            departamentos.remove(departamento);
            JOptionPane.showMessageDialog(null, "Departamento eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
        }
    }

    private static void mostrarDepartamentos() {
        StringBuilder sb = new StringBuilder();
        for (Departamento departamento : departamentos) {
            sb.append(departamento.getId()).append(" - ").append(departamento.getNombreDepartamento()).append(" - ").append(departamento.getDescripcion()).append(" - Jefe: ").append(departamento.getJefe()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private static Departamento buscarDepartamentoPorId(String id) {
        for (Departamento departamento : departamentos) {
            if (departamento.getId().equals(id)) {
                return departamento;
            }
        }
        return null;
    }

    private static void asignarEmpleadoADepartamento() {
        String idEmpleado = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
        Empleado empleado = buscarEmpleadoPorId(idEmpleado);

        if (empleado != null) {
            String idDepartamento = JOptionPane.showInputDialog("Ingrese el ID del departamento:");
            Departamento departamento = buscarDepartamentoPorId(idDepartamento);

            if (departamento != null) {
                departamento.getEmpleados().add(empleado);  // Asignar empleado al departamento
                JOptionPane.showMessageDialog(null, "Empleado asignado al departamento " + departamento.getNombreDepartamento());
            } else {
                JOptionPane.showMessageDialog(null, "Departamento no encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private static void actualizarDesempenio() {
        String idEmpleado = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
        Empleado empleado = buscarEmpleadoPorId(idEmpleado);
        if (empleado != null) {
            String desempenioStr = JOptionPane.showInputDialog("Ingrese el desempeño del empleado (0-100):");
            int desempenio = Integer.parseInt(desempenioStr);
            empleado.getReporteDesempenio().setDesempenio(desempenio);
            JOptionPane.showMessageDialog(null, "Desempeño actualizado para el empleado " + empleado.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private static void generarReporte() {
        String[] opciones = {"Empleado", "Departamento"};
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de reporte:", "Generar Reporte",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Crear un StringBuilder para acumular el reporte
        StringBuilder reporte = new StringBuilder();

        if ("Empleado".equals(seleccion)) {
            String idEmpleado = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
            Empleado empleado = buscarEmpleadoPorId(idEmpleado);
            if (empleado != null) {
                // Generar el reporte para el empleado
                reporte.append("Reporte de Empleado\n");
                reporte.append("ID: ").append(empleado.getId()).append("\n");
                reporte.append("Nombre: ").append(empleado.getNombre()).append("\n");
                reporte.append("Apellido: ").append(empleado.getApellido()).append("\n");
                reporte.append("Tipo: ").append(empleado.getTipoEmpleado()).append("\n");
                reporte.append("Desempeño: ").append(empleado.getReporteDesempenio().getDesempenio()).append("\n");
            } else {
                // Mostrar mensaje de error en el textArea
                reporte.append("Empleado no encontrado.");
            }
        } else if ("Departamento".equals(seleccion)) {
            String idDepartamento = JOptionPane.showInputDialog("Ingrese el ID del departamento:");
            Departamento departamento = buscarDepartamentoPorId(idDepartamento);
            if (departamento != null) {
                // Generar el reporte para el departamento
                reporte.append("Reporte de Departamento\n");
                reporte.append("ID: ").append(departamento.getId()).append("\n");
                reporte.append("Nombre: ").append(departamento.getNombreDepartamento()).append("\n");
                reporte.append("Descripción: ").append(departamento.getDescripcion()).append("\n");
                reporte.append("Jefe: ").append(departamento.getJefe()).append("\n");

                // Mostrar empleados del departamento
                List<Empleado> empleadosDepartamento = departamento.getEmpleados();
                if (empleadosDepartamento != null && !empleadosDepartamento.isEmpty()) {
                    double totalDesempenio = 0;
                    int cantidadEmpleados = empleadosDepartamento.size();

                    reporte.append("\nLista de Empleados:\n");
                    for (Empleado empleado : empleadosDepartamento) {
                        double desempenio = empleado.getReporteDesempenio().getDesempenio();
                        reporte.append("ID: ").append(empleado.getId())
                                .append(" - Nombre: ").append(empleado.getNombre())
                                .append(" ").append(empleado.getApellido())
                                .append(" - Desempeño: ").append(desempenio).append("\n");
                        totalDesempenio += desempenio;
                    }

                    // Calcular promedio de desempeño
                    double promedioDesempenio = totalDesempenio / cantidadEmpleados;
                    reporte.append("\nPromedio de Desempeño del Departamento: ").append(promedioDesempenio).append("\n");
                } else {
                    reporte.append("No hay empleados asignados a este departamento.\n");
                }
            } else {
                // Mostrar mensaje de error en el textArea
                reporte.append("Departamento no encontrado.");
            }
        }

        // Finalmente, mostrar el contenido en el textArea
        textArea.setText(reporte.toString());
    }
}

