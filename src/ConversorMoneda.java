
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class ConversorMoneda {
    private JFrame monedaframe;
    private JTextField ingreso_Moneda;
    private JLabel resultado_Moneda;
    private JComboBox<String> menu_Monda_Entrada;
    private JComboBox<String> menu_Monda_Salida;

    private static double tasaDolar = 0.022;
    private static double tasaEuro = 0.0011;
    private static double tasaLibrasEsterlinas = 00.029;
    private static double tasaYenJapones = 0.00016;
    private static double tasaWonSulCoreano = 0.000018;
    private static double tasaPesoChileno =  0.000028;

    public ConversorMoneda() { initialize(); }
    public void mostrarVentana() {
        monedaframe.setVisible(true);
    }
    public void cerrarventanaConversorMoneda() { monedaframe.setVisible(false); }
    public void finalVentana() {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar usando el programa?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            miProgramaPrincipal miprogramaPrincipal = new miProgramaPrincipal();
            miprogramaPrincipal.mostrarVentana();
        } else { JOptionPane.showMessageDialog(null, "Programa Finalizado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);}
            cerrarventanaConversorMoneda();
    }
    class NumerosVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            if (text.matches("\\d+")) {
                int numero = Integer.parseInt(text);
                if (numero > 0) {
                    return true;
                } else {
                    mostrarError("El número debe ser mayor a 0.");
                    return false;
                }
            } else {
                mostrarError("Debe ingresar solo números en el campo de moneda.");
                return false;
            }
        }
        private void mostrarError(String mensaje) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void initialize() {
        monedaframe = new JFrame();
        monedaframe.setResizable(false);
        monedaframe.setTitle("Convertidor Moneda ");
        monedaframe.setBounds(100, 100, 450, 300);
        monedaframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        monedaframe.getContentPane().setLayout(null);

        ingreso_Moneda = new JTextField();
        ingreso_Moneda.setBounds(58, 68, 130, 33);
        monedaframe.getContentPane().add(ingreso_Moneda);
        ingreso_Moneda.setColumns(10);
        ingreso_Moneda.setInputVerifier(new NumerosVerifier());

        menu_Monda_Entrada = new JComboBox<String>();
        menu_Monda_Entrada.setModel(new DefaultComboBoxModel<String>(new String[]{"Peso Chileno", "Dolar", "Euro", "Libras Esterlina", "Yen Japones", "Won sul-coreano"}));
        menu_Monda_Entrada.setBounds(58, 127, 130, 33);
        monedaframe.getContentPane().add(menu_Monda_Entrada);

        JButton boton_Convert = new JButton("Convertir");
        boton_Convert.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });

        boton_Convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valor_Ingreso = ingreso_Moneda.getText();
                double valorIngreso_Moneda = Double.parseDouble(valor_Ingreso);
                String Menu_Entrada = (String) menu_Monda_Entrada.getSelectedItem();
                String Menu_Salida = (String) menu_Monda_Salida.getSelectedItem();

                double resultado_conversion = convertirMoneda(Menu_Entrada, Menu_Salida, valorIngreso_Moneda);
                String resultado_Final_Convercion = String.format("%.2f", resultado_conversion);
                resultado_Moneda.setText(resultado_Final_Convercion);
                ingreso_Moneda.getInputVerifier().verify(ingreso_Moneda);

                finalVentana();
            }
        });

        boton_Convert.setBounds(147, 189, 106, 33);
        monedaframe.getContentPane().add(boton_Convert);

        menu_Monda_Salida = new JComboBox<String>();
        menu_Monda_Salida.setModel(new DefaultComboBoxModel<String>(new String[]{"Dolar", "Euro", "Libra Esterlina", "Yen Japones", "Won sul-coreano", "Peso Chileno"}));
        menu_Monda_Salida.setBounds(207, 127, 136, 33);
        monedaframe.getContentPane().add(menu_Monda_Salida);

        resultado_Moneda = new JLabel("00.00");
        resultado_Moneda.setBounds(207, 69, 136, 30);
        monedaframe.getContentPane().add(resultado_Moneda);
    } // final initialize
    public static double convertirMoneda(String opcionOrigen, String opcionDestino, double cantidad_entrada) {
        double tasaDestino = 0.0;
        if (opcionOrigen.equals(opcionDestino)) {
            JOptionPane.showMessageDialog(null, "¡Error! Las conversion de moneda son iguales. Elegir otra opción.", "Error", JOptionPane.ERROR_MESSAGE);
            return cantidad_entrada * tasaDestino;
        } else if (opcionOrigen.equals("Dolar")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaDolar);
        } else if (opcionOrigen.equals("Euro")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaEuro);
        } else if (opcionOrigen.equals("Libras Esterlina")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaLibrasEsterlinas);
        } else if (opcionOrigen.equals("Yen Japones")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaYenJapones);
        } else if (opcionOrigen.equals("Won sul-coreano")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaWonSulCoreano);
        } else if (opcionOrigen.equals("Peso Chileno")) {
            tasaDestino = obtenerTasaDestino(opcionDestino, tasaPesoChileno);
        } return cantidad_entrada * tasaDestino;
    }
    public static double obtenerTasaDestino(String opcionDestino, double tasaOrigen) {
        double tasaDestino = 0.0;
        if (opcionDestino.equals("Dolar")) {
            tasaDestino = tasaDolar;
        } else if (opcionDestino.equals("Euro")) {
            tasaDestino = tasaEuro;
        } else if (opcionDestino.equals("Libra Esterlina")) {
            tasaDestino = tasaLibrasEsterlinas;
        } else if (opcionDestino.equals("Yen Japones")) {
            tasaDestino = tasaYenJapones;
        } else if (opcionDestino.equals("Won sul-coreano")) {
            tasaDestino = tasaWonSulCoreano;
        } else if (opcionDestino.equals("Peso Chileno")) {
            tasaDestino = tasaPesoChileno;
        } return tasaOrigen / tasaDestino;
    }
} // final class ConversorMoneda
