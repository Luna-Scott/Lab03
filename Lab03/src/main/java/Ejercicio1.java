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
    private long alarmaTime;
    private boolean alarmaActiva = false;
    
    public Ejercicio1() {
        setTitle("Cronómetro con Alarma");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
       
        tiempoLabel = new JLabel("00:00:00", JLabel.CENTER);
        tiempoLabel.setFont(new Font("Times New Roman", Font.BOLD, 65));
        add(tiempoLabel);
        
        alarmaInput = new JTextField("");
        alarmaInput.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        add(alarmaInput);
        
        setAlarmaButton = new JButton("Configurar Alarma (seg)");
        setAlarmaButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        add(setAlarmaButton);
        
        setAlarmaButton.setBackground(new Color(255, 182, 193));
        
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
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
        timer.start();
    }
    
    private void actualizarTiempo() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formato.format(Calendar.getInstance().getTime());
        tiempoLabel.setText(horaActual);
        
        if (alarmaActiva && System.currentTimeMillis() >= alarmaTime) {
            JOptionPane.showMessageDialog(this, "¡Alarma!");
            alarmaActiva = false;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejercicio1().setVisible(true);
        });
    }
}