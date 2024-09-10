import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temporizadores {

    private Timer timer;
    private TimerTask tareaMostrarHora;
    private TimerTask tareaAlarma;
    
    private static final int ALARMA_INICIAL = 2 * 60 * 1000;
    private static final int INTERVALO_ALARMA = 10 * 1000;  

    public Temporizadores() {
        timer = new Timer();
        iniciarTemporizadores();
    }

    private void iniciarTemporizadores() {
        tareaMostrarHora = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String horaActual = formatoHora.format(new Date());
                System.out.println("Hora actual: " + horaActual);
            }
        };

        tareaAlarma = new TimerTask() {
            @Override
            public void run() {
                System.out.println("¡Alarma activada!");
            }
        };

        // Programar la tarea de mostrar la hora cada segundo
        timer.scheduleAtFixedRate(tareaMostrarHora, 0, 1000);

        // Programar la tarea de la alarma para que se active después de 2 minutos y luego cada 10 segundos
        timer.schedule(tareaAlarma, ALARMA_INICIAL, INTERVALO_ALARMA);
    }

    public static void main(String[] args) {
        Temporizadores temporizadores = new Temporizadores();
    }
}

