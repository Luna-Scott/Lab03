import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ejercicio1 {

    private final Timer timer;
    private Timer alarmTimer;
    private long tiempoConfig;
    private long alarmaIntervalo;
    
    public Ejercicio1() {
        timer = new Timer();
    }

    // Método para iniciar el cronómetro
    public void iniciarCronometro() {
        TimerTask tareaCronometro = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String horaActual = formatoHora.format(new Date());
                System.out.println("Hora Actual: " + horaActual);
            }
        };
        timer.scheduleAtFixedRate(tareaCronometro, 0, 1000); // Ejecuta cada segundo
    }

    // Método para configurar el tiempo y la alarma
    public void configurarAlarma(long minutos, long intervaloAlarmaSegundos) {
        tiempoConfig = minutos * 60 * 1000; // Convertir minutos a milisegundos
        alarmaIntervalo = intervaloAlarmaSegundos * 1000; // Convertir segundos a milisegundos

        TimerTask tareaAlarma = new TimerTask() {
            @Override
            public void run() {
                System.out.println("¡Alarma!");
            }
        };

        // Configurar el Timer para la alarma
        alarmTimer = new Timer();
        alarmTimer.schedule(tareaAlarma, tiempoConfig, alarmaIntervalo);
    }

    // Método para detener el cronómetro y la alarma
    public void detenerCronometro() {
        timer.cancel();
        if (alarmTimer != null) {
            alarmTimer.cancel();
        }
    }

    public static void main(String[] args) {
        Ejercicio1 ejercicio1 = new Ejercicio1();
        ejercicio1.iniciarCronometro();
        ejercicio1.configurarAlarma(2, 10); // Configura alarma para 2 minutos y alarma cada 10 segundos
    }
}
