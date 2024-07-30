package proyecto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import static java.lang.Math.random;

/**
 * *
 * Clase creada para que dispositivos hereden de ella
 *
 * @author Mihai Gabriel Laudoiu
 */
public abstract class Dispositivo implements Comparable<Dispositivo> {

    //Atributos
    protected String nombre, numeroSerie;
    protected static int n_dispositivos;
    protected float altura, ancho, memoria;
    protected Color color;
    protected Tipo tipo;
    protected SSOO ssoo;
    protected boolean tactil, conexionInternet;
    protected final static String COMPROBACION = "[A-Z]{3}-[0-9]{8}";
    protected String cpu;

    //Constructores
    public Dispositivo(String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO sitemaOperativo, boolean tactil, boolean conexionInternet, String numeroSerie, float memoria, String cpu) {
        this(nombre, altura, ancho, color, tipo, sitemaOperativo, tactil, conexionInternet, memoria, cpu);
        this.numeroSerie = numeroSerie;
        n_dispositivos++;
    }

    public Dispositivo(String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO sitemaOperativo, boolean tactil, boolean conexionInternet, float memoria, String cpu) {
        this.nombre = nombre;
        this.altura = altura;
        this.ancho = ancho;
        this.color = color;
        this.tipo = tipo;
        this.ssoo = sitemaOperativo;
        this.tactil = tactil;
        this.conexionInternet = conexionInternet;
        this.numeroSerie = generadorNumeroSerie();
        this.memoria = memoria;
        this.cpu = cpu;
        n_dispositivos++;
    }

    public Dispositivo() {
        n_dispositivos++;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public static int getN_dispositivos() {
        return n_dispositivos;
    }

    public float getAltura() {
        return altura;
    }

    public float getAncho() {
        return ancho;
    }

    public Color getColor() {
        return color;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public SSOO getSitemaOperativo() {
        return ssoo;
    }

    public boolean isTactil() {
        return tactil;
    }

    public boolean isConexionInternet() {
        return conexionInternet;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getCOMPROBACION() {
        return COMPROBACION;
    }

    public float getMemoria() {
        return memoria;
    }

    public String getCpu() {
        return cpu;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setSitemaOperativo(SSOO sitemaOperativo) {
        this.ssoo = sitemaOperativo;
    }

    public void setTactil(boolean tactil) {
        this.tactil = tactil;
    }

    public void setConexionInternet(boolean conexionInternet) {
        this.conexionInternet = conexionInternet;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setMemoria(float memoria) {
        this.memoria = memoria;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    //Metodos
    /**
     * *
     * Metodo que genera un nº de serie
     *
     * @return
     */
    public final String generadorNumeroSerie() {
        String numero;
        int secNumero;
        StringBuilder nSerie;
        StringBuilder letras;
        char abecedario[] = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        int eleccion;
        do {
            nSerie = new StringBuilder();
            letras = new StringBuilder();
            do {
                secNumero = (int) (random() * 100000000);
            } while (String.valueOf(secNumero).length() != 8);
            numero = String.valueOf(secNumero);
            for (int i = 0; i < 3; i++) {
                eleccion = (int) ((random() * 26) - 1);
                letras.append(abecedario[eleccion]);
            }
            nSerie.append(letras).append("-").append(numero);
        } while (!comprobarNumSerie(nSerie.toString()));
        return nSerie.toString();
    }

    /**
     * *
     * Metodo que comprueba un numero de serie
     *
     * @param nSerie
     * @return
     */
    public boolean comprobarNumSerie(String nSerie) {
        return nSerie.matches(COMPROBACION);
    }

    /**
     * Método que borra la consola
     *
     * @throws AWTException
     * @throws InterruptedException
     */
    public static void cls() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
            Thread.sleep(100);
        } catch (AWTException | InterruptedException e) {
        }
    }

    //To string
    @Override
    public String toString() {
        return """
               Dispositivo:
               nombre = """ + nombre + "\naltura = " + altura + " metros" + "\nancho = " + ancho + " metros" + "\ncolor = " + color + "\ntipo = " + tipo + "\nsitemaOperativo = " + ssoo + "\ntactil = " + tactil + "\nconexionInternet = " + conexionInternet + "\nnumeroSerie = " + numeroSerie + "\nmemoria = " + memoria + "\ncpu = " + cpu;
    }

    //Compare to
    @Override
    public int compareTo(Dispositivo d) {
        return d.getNumeroSerie().compareToIgnoreCase(getNumeroSerie());
    }

    public abstract String calcularDimension();
}
