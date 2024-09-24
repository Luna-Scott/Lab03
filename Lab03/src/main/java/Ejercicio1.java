import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ejercicio1 extends JFrame {
    private JLabel tiempoLabel;
    private JTextField alarmaInput;
    private JButton setAlarmaButton;
    private Timer timer; 
    private long alarmaTime;  // Tiempo en milisegundos para la alarma
    private boolean alarmaActiva = false;

    public Ejercicio1() {
        setTitle("Cronómetro con Alarma");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Label para mostrar la hora
        tiempoLabel = new JLabel("00:00:00", JLabel.CENTER);
        tiempoLabel.setFont(new Font("Serif", Font.BOLD, 36));
        add(tiempoLabel);

        // Campo de texto para ingresar la alarma (en segundos)
        alarmaInput = new JTextField("Ingresa tiempo en segundos");
        add(alarmaInput);

        // Botón para configurar la alarma
        setAlarmaButton = new JButton("Configurar Alarma");
        add(setAlarmaButton);

        // Acción del botón de configurar alarma
        setAlarmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tiempoEnSegundos = Integer.parseInt(alarmaInput.getText());
                    alarmaTime = System.currentTimeMillis() + (tiempoEnSegundos * 1000);
                    alarmaActiva = true;
                    JOptionPane.showMessageDialog(null, "Alarma configurada para " + tiempoEnSegundos + " segundos.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
                }
            }
        });

        // Timer para actualizar la hora cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
        timer.start();
    }

    // Método para actualizar la hora
    private void actualizarTiempo() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formato.format(Calendar.getInstance().getTime());
        tiempoLabel.setText(horaActual);

        // Verificar si la alarma debe sonar
        if (alarmaActiva && System.currentTimeMillis() >= alarmaTime) {
            JOptionPane.showMessageDialog(this, "¡Alarma!");
            alarmaActiva = false;  // Desactiva la alarma después de sonar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejercicio1().setVisible(true);
        });
    }
}
