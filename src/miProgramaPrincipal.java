import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class miProgramaPrincipal {
    private JFrame miProgramPrincipalframe;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    miProgramaPrincipal window = new miProgramaPrincipal();
                    window.miProgramPrincipalframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public miProgramaPrincipal() {
        initialize();
    }
    public void mostrarVentana() {
        miProgramPrincipalframe.setVisible(true);
    }
    public void cerrarVentanamiProgramaPrincipal() { miProgramPrincipalframe.setVisible(false); }
    private void initialize() {
        miProgramPrincipalframe = new JFrame();
        miProgramPrincipalframe.setResizable(false);
        miProgramPrincipalframe.setTitle("Menu Convertidor ");
        miProgramPrincipalframe.setBounds(100, 100, 450, 300);
        miProgramPrincipalframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miProgramPrincipalframe.getContentPane().setLayout(null);
        JComboBox<String> lista_Opciones = new JComboBox<String>();
        lista_Opciones.setModel(new DefaultComboBoxModel<String>(new String[] {"Moneda", "Temperatura"}));
        lista_Opciones.setBounds(110, 80, 229, 37);
        miProgramPrincipalframe.getContentPane().add(lista_Opciones);

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setBounds(148, 142, 160, 37);
        miProgramPrincipalframe.getContentPane().add(botonSeleccionar);
        botonSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) lista_Opciones.getSelectedItem();
                if (opcionSeleccionada.equals("Moneda")) {
                    ConversorMoneda conversorMoneda = new ConversorMoneda();
                    conversorMoneda.mostrarVentana();
                    cerrarVentanamiProgramaPrincipal();
                } else if (opcionSeleccionada.equals("Temperatura")) {
                    ConversorTemperatura conversorTemperatura = new ConversorTemperatura();
                    conversorTemperatura.mostrarVentana();
                    cerrarVentanamiProgramaPrincipal();
                }
            }
        });
    }
} // final class miProgramaPrincipal
