
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ConversorTemperatura {
    private JFrame temperaturaframe;
    private JTextField ingreso_Temperatura;
    private JLabel resultado_Temperatura;
    private JComboBox menu_Temp_Entrada;
    private JComboBox menu_Temp_Salida;
    private JButton botonSelecionar;
    public ConversorTemperatura() {
        initialize();
    }
    public void mostrarVentana() {
        temperaturaframe.setVisible(true);
    }
    public void cerrarventanaConversorTemperatura() { temperaturaframe.setVisible(false); }
    public void finalVentana() {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar usando el programa?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            miProgramaPrincipal miprogramaPrincipal = new miProgramaPrincipal();
            miprogramaPrincipal.mostrarVentana();
        } else { JOptionPane.showMessageDialog(null, "Programa Finalizado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);}
            cerrarventanaConversorTemperatura();
    }
    private void initialize() {
        temperaturaframe = new JFrame();
        temperaturaframe.setResizable(false);
        temperaturaframe.setTitle("Convertidor Temperatura ");
        temperaturaframe.setBounds(100, 100, 450, 300);
        temperaturaframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temperaturaframe.getContentPane().setLayout(null);

        menu_Temp_Entrada = new JComboBox<String>();
        menu_Temp_Entrada.setModel(new DefaultComboBoxModel<String>(new String[] {"Fahrenheit", "Celsius"}));
        menu_Temp_Entrada.setBounds(39, 113, 159, 37);
        temperaturaframe.getContentPane().add(menu_Temp_Entrada);

        menu_Temp_Salida = new JComboBox<String>();
        menu_Temp_Salida.setModel(new DefaultComboBoxModel<String>(new String[] {"Celsius", "Fahrenheit"}));
        menu_Temp_Salida.setBounds(237, 113, 159, 37);
        temperaturaframe.getContentPane().add(menu_Temp_Salida);

        botonSelecionar = new JButton("Seleccionar");
        botonSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingreso_Temperatura.setInputVerifier(new NumerosVerifier());
                if (!ingreso_Temperatura.getInputVerifier().verify(ingreso_Temperatura)) {
                    JOptionPane.showMessageDialog(temperaturaframe, "Debe ingresar solo números en el campo temperatura.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String entrada_Temperatura = ingreso_Temperatura.getText();
                double valor_Ingreso_Temperatura = Double.parseDouble(entrada_Temperatura);
                String Menu_Entrada = (String) menu_Temp_Entrada.getSelectedItem();
                String Menu_Salida = (String) menu_Temp_Salida.getSelectedItem();
                double resultado_conversion = convertirTemperatura(Menu_Entrada, Menu_Salida, valor_Ingreso_Temperatura);
                String resultado_Final_Convercion = String.format("%.2f", resultado_conversion);
                resultado_Temperatura.setText(resultado_Final_Convercion);

                finalVentana();
            }
        });
        botonSelecionar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });
        botonSelecionar.setBounds(141, 179, 159, 37);
        temperaturaframe.getContentPane().add(botonSelecionar);

        resultado_Temperatura = new JLabel("00.00");
        resultado_Temperatura.setBounds(237, 43, 159, 37);
        temperaturaframe.getContentPane().add(resultado_Temperatura);

        ingreso_Temperatura = new JTextField();
        ingreso_Temperatura.setText("0");
        ingreso_Temperatura.setBounds(39, 43, 159, 37);
        temperaturaframe.getContentPane().add(ingreso_Temperatura);
        ingreso_Temperatura.setColumns(10);
    }  // final initialize
    public static double convertirTemperatura(String opcionOrigen, String opcionDestino, double cantidad_entrada) {
        double resultado_Final = 0.0;
        if (opcionOrigen.equals(opcionDestino)) {
            JOptionPane.showMessageDialog(null, "¡Error! Las conversion de temperatura son iguales. Elegir otra opción.", "Error", JOptionPane.ERROR_MESSAGE);
            return resultado_Final;
        } else if (opcionOrigen.equals("Fahrenheit")) {
            resultado_Final = resul_Final_Conversion(opcionDestino, cantidad_entrada); // convierte fahrenheit a Celsius
        } else if (opcionOrigen.equals("Celsius")) {
            resultado_Final = resul_Final_Conversion(opcionDestino, cantidad_entrada);  // convierte Celsius a fahrenheit
        } return resultado_Final;
    }
    public static double resul_Final_Conversion(String fin_destino, double cantidad_entrada) {
        double resultado= 0.0;
        if (fin_destino.equals("Fahrenheit")) {
            double fahrenheit = cantidad_entrada;
            resultado = (fahrenheit - 32) * 5/9; // convierte fahrenheit a Celsius
        } else if (fin_destino.equals("Celsius")) {
            double celsius = cantidad_entrada;
            resultado = (celsius * 9 / 5) + 32; // convierte Celsius a fahrenheit
        } return resultado;
    }
    class NumerosVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            String no_Texto = ((JTextField) input).getText().trim();
            return !no_Texto.isEmpty() && no_Texto.matches("-?\\d+(\\.\\d+)?");
        }
    }
} // final class ConversorTemperatura
