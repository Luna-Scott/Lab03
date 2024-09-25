import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ejercicio2 extends JFrame {

    private JLabel etiquetaImagen;
    private Timer temporizador;
    private int indiceImagen = 0;
    private ImageIcon[] imagenes;
    private JSlider controlVelocidad; 
    private final String rutaImagenes = "C://Users//Hogar//Pictures//Laboratorio//";

    public Ejercicio2() {
        setTitle("Animación de Imágenes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        imagenes = new ImageIcon[] {
            new ImageIcon(rutaImagenes + "man1.png"),
            new ImageIcon(rutaImagenes + "man2.png"),
            new ImageIcon(rutaImagenes + "man3.png"),
            new ImageIcon(rutaImagenes + "man4.png"),
            new ImageIcon(rutaImagenes + "man5.png"),
            new ImageIcon(rutaImagenes + "man6.png"),
            new ImageIcon(rutaImagenes + "man7.png"),
            new ImageIcon(rutaImagenes + "man8.png")
        };

        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        etiquetaImagen.setIcon(imagenes[indiceImagen]);
        add(etiquetaImagen, BorderLayout.CENTER);
      

        controlVelocidad = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1000);
        controlVelocidad.setMajorTickSpacing(300);
        controlVelocidad.setPaintTicks(true);
        controlVelocidad.setPaintLabels(true);
        controlVelocidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int velocidad = controlVelocidad.getValue();
                temporizador.setDelay(velocidad);
            }
        });
        add(controlVelocidad, BorderLayout.SOUTH);

        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indiceImagen = (indiceImagen + 1) % imagenes.length;
                etiquetaImagen.setIcon(imagenes[indiceImagen]);
            }
        });

        temporizador.start();
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio2().setVisible(true);
            }
        });
    }
}
