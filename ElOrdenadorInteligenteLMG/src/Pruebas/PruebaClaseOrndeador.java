package Pruebas;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import proyecto.Color;
import proyecto.Conectable;
import proyecto.Dispositivo;
import proyecto.ExceptionGabriel;
import proyecto.ObjectOutputStreamLMG;
import proyecto.SSOO;
import proyecto.Tipo;

/**
 * Clase que representa un ordenador, extendiendo la funcionalidad de
 * Dispositivo e implementando la interfaz Conectable.
 *
 * @author Mihai Gabriel Laudoiu
 */
public class PruebaClaseOrndeador extends Dispositivo implements Conectable, Serializable {

    private boolean esPortatil;
    private String tarjetaGrafica;
    private String[] perifericos;

    









 public PruebaClaseOrndeador(boolean esPortatil) {
        super();
        this.esPortatil = esPortatil;
    }








// Constructores
    /**
     * Constructor por defecto.
     */
    public PruebaClaseOrndeador(boolean esPortatil1, String tarjetaGrafica1, String perifericos1, String nombre1, float altura1, float ancho1, Color color1, Tipo tipo1, SSOO sso, boolean esTactil, boolean tieneConexionInternet, String numeroSerie1, float memoriaRAM, String tipoCPU) {
    }
    
    /**
     * *
     * Constructor con todos los parametros.
     *
     * @param esPortatil
     * @param tarjetaGrafica
     * @param perifericos
     * @param nombre
     * @param altura
     * @param ancho
     * @param color
     * @param tipo
     * @param sso
     * @param esTactil
     * @param tieneConexionInternet
     * @param numeroSerie
     * @param memoriaRAM
     * @param tipoCPU
     */
    public PruebaClaseOrndeador(boolean esPortatil, String tarjetaGrafica, String[] perifericos, String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO sso, boolean esTactil, boolean tieneConexionInternet, String numeroSerie, float memoriaRAM, String tipoCPU) {
        super(nombre, altura, ancho, color, tipo, sso, esTactil, tieneConexionInternet, numeroSerie, memoriaRAM, tipoCPU);
        this.esPortatil = esPortatil;
        this.tarjetaGrafica = tarjetaGrafica;
        this.perifericos = perifericos;
    }

    /**
     * *
     * Constructor sin numero de serie ni memoria RAM.
     *
     * @param esPortatil
     * @param tarjetaGrafica
     * @param perifericos
     * @param nombre
     * @param altura
     * @param ancho
     * @param color
     * @param tipo
     * @param sso
     * @param esTactil
     * @param tieneConexionInternet
     * @param memoriaRAM
     * @param tipoCPU
     */
    public PruebaClaseOrndeador(boolean esPortatil, String tarjetaGrafica, String[] perifericos, String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO sso, boolean esTactil, boolean tieneConexionInternet, float memoriaRAM, String tipoCPU) {
        super(nombre, altura, ancho, color, tipo, sso, esTactil, tieneConexionInternet, memoriaRAM, tipoCPU);
        this.esPortatil = esPortatil;
        this.tarjetaGrafica = tarjetaGrafica;
        this.perifericos = perifericos;
    }

    // Getters y Setters
    public boolean isEsPortatil() {
        return esPortatil;
    }

    public String getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public String[] getPerifericos() {
        return perifericos;
    }

    public void setEsPortatil(boolean esPortatil) {
        this.esPortatil = esPortatil;
    }

    public void setTarjetaGrafica(String tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    public void setPerifericos(String[] perifericos) {
        this.perifericos = perifericos;
    }

    // Metodos especificos del ordenador
    /**
     * Metodo para programar el ordenador. Imprime un codigo Java basico.
     */
    public void programar() {
        System.out.println("""
                                   public static void main(String[] args) {
                                           System.out.println("Hola mundo");
                                       }""");
    }

    /**
     * Metodo para jugar adivinanzas.
     *
     * @throws IOException
     */
    public void jugarAdivinanza() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();
        int numeroAleatorio;
        int intentos = 0;
        int intento = 0;
        //Se guarda un numero aleatorio entre 1 y 100
        numeroAleatorio = random.nextInt(100) + 1;
        System.out.println("Bienvenido al juego de adivinanzas!");
        System.out.println("Introduce un numero entre 1 y 100");
        //Si el valor introducido por el usuario es menor o mayor se le indicara para que siga buscando y se contaran los intentos que hace
        do {
            System.out.print("Introduce tu intento: ");

            try {
                intento = Integer.parseInt(reader.readLine());
                intentos++;
            } catch (NumberFormatException nfe) {
                System.out.println("El valor introducido es incorrecto");
            }

            if (intento < numeroAleatorio) {
                System.out.println("El numero que buscas es mayor.");
            } else if (intento > numeroAleatorio) {
                System.out.println("El numero que buscas es menor.");
            }
        } while (intento != numeroAleatorio);

        System.out.println("Has adivinado el numero en " + intentos + " intentos!");
    }

    /**
     * Metodo que selecciona una broma de un array y la imprime.
     */
    public void generarBroma() {
        Random random = new Random();
        int num;
        //Se declara un array
        String[] bromas = {
            "Por que los programadores odian el mar? Porque no pueden parar de contar hasta la playa.",
            "Que le dice un arbol a otro? No me toques las ramas!",
            "Que hace una abeja en el gimnasio? Zum-ba!",
            "Como se dice dentista en chino? Loco muela.",
            "Por que los pajaros no usan Facebook? Porque ya tienen Twitter.",
            "Cual es el animal mas antiguo? La cebra, porque esta en blanco y negro."
        };
        num = random.nextInt(bromas.length);
        //Se elige un numero aleatorio del tamano del array para que siempre salga una broma en rango y la imprime
        System.out.println("Aqui tienes una broma:");
        System.out.println(bromas[num]);
    }

    /**
     * Metodo que simula una calculadora basica.
     *
     * @throws IOException
     */
    public void usarCalculadora() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double numero1 = 0;
        double numero2 = 0;
        int opc = 0;
        double resultado = 0;
        //Pide al usuario 2 numeros y luego le da un menu para elegir que tipo de operacion quiere hacer con esos dos numeros
        System.out.println("La calculadora basica!");
        System.out.println("Introduce el primer numero:");
        try {
            numero1 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("El valor introducido no es correcto");
        }

        System.out.println("Por favor, introduce el segundo numero:");
        try {
            numero2 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("El valor introducido no es correcto");
        }

        System.out.println("Por favor, selecciona la operacion a realizar:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicacion");
        System.out.println("4. Division");

        try {
            opc = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("El valor introducido no es correcto");
        }

        switch (opc) {
            case 1 ->
                resultado = numero1 + numero2;
            case 2 ->
                resultado = numero1 - numero2;
            case 3 ->
                resultado = numero1 * numero2;
            case 4 -> {
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    System.out.println("No se puede dividir entre cero.");
                }
            }
            default -> {
                System.out.println("Opcion invalida.");
                return;
            }
        }

        System.out.println("El resultado es: " + resultado);
    }

    /**
     * Metodo para calcular la dimension del ordenador.
     *
     * @return String con la dimension del ordenador.
     */
    @Override
    public String calcularDimension() {
        //Dependiendo de los valores de los atributos del ordenador te dice si es muy grande o muy pequeno o normal
        if (altura < 0.5 && ancho < 0.3) {
            return "Mini ordenador";
        } else if (altura > 0.9 && ancho > 0.6) {
            return "Ordenador gigantesco";
        } else {
            return "Ordenador de dimensiones normales";
        }
    }

    /**
     * Metodo toString para representar la informacion del ordenador.
     *
     * @return Cadena de texto con la informacion del ordenador.
     */
    @Override
    public String toString() {
        //Metodo to string que devuelve un string de lo que contiene nuestro objeto y recorre la lista de perifericos para agregarlo tambien en el string final
        StringBuilder listaPerifericos = new StringBuilder();
        for (int i = 0; i < perifericos.length; i++) {
            if (i != perifericos.length - 1) {
                listaPerifericos.append(perifericos[i]).append(" ,");
            } else {
                listaPerifericos.append(perifericos[i]);
            }
        }
        return super.toString() + "\nEs portatil= " + esPortatil + "\nTarjeta grafica= " + tarjetaGrafica + "\nPerifericos= " + listaPerifericos.toString();
    }

    /**
     * Implementacion de la interfaz Conectable.
     *
     * @return true si el dispositivo esta conectado a internet, false en caso
     * contrario.
     */
    @Override
    public boolean conectable() {
        //Definimos si el dispositivo esta conectado a internet y utiliza la excepcion personalizada
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String respuesta;

        System.out.println("Este dispositivo esta conectado a internet? (S/N)");
        do {
            try {
                respuesta = reader.readLine();
                if (respuesta.equalsIgnoreCase("S")) {
                    return true;
                } else if (respuesta.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    throw new ExceptionGabriel("Solo puede ser 'S' o 'N'.");
                }
            } catch (IOException e) {
                System.out.println("Error: No se pudo leer la entrada del usuario.");
                return false;
            } catch (ExceptionGabriel e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Menu para probar las funcionalidades del ordenador.
     *
     * @param ordenador1 el ordenador a probar.
     * @throws IOException
     */
    public static void menu(PruebaClaseOrndeador ordenador1) throws IOException {
        boolean salir = false;
        int nada;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {

            //Espera al usuario para continuar
            System.out.println("Pulsa una tecla para continuar");
            try {
                nada = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Solo se admiten ciertos valores.");
            }

            limpiarPantalla();
            System.out.println("Selecciona una opcion:");
            System.out.println("1. Calcular dimensiones");
            System.out.println("2. Programar");
            System.out.println("3. Jugar adivinanza");
            System.out.println("4. Generar broma");
            System.out.println("5. Usar calculadora");
            System.out.println("6. Conectar a internet");
            System.out.println("7. Salir");

            int opcion = 0;
            try {
                opcion = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Solo se admiten ciertos valores.");
            }

            switch (opcion) {
                case 1 ->
                    System.out.println(ordenador1.calcularDimension());
                case 2 ->
                    ordenador1.programar();
                case 3 ->
                    ordenador1.jugarAdivinanza();
                case 4 ->
                    ordenador1.generarBroma();
                case 5 ->
                    ordenador1.usarCalculadora();
                case 6 ->
                    System.out.println("El dispositivo esta conectado a internet: " + ordenador1.conectable());
                case 7 ->
                    salir = true;
                default ->
                    System.out.println("Opcion invalida.");
            }
        } while (!salir);
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Escribir ordenador
     *
     * @param ordenador
     * @return
     */
    public static int escribirOrdenador(PruebaClaseOrndeador ordenador) {
        File f = new File("ordenadores.txt");
        boolean append = f.exists();
        int valorDevuelto = 0;

        try (ObjectOutputStreamLMG oos = new ObjectOutputStreamLMG(new FileOutputStream(f, append), append)) {
            oos.writeObject(ordenador);
            valorDevuelto = 1;
        } catch (IOException ioe) {
            System.out.println("ERROR de E/S en la escritura");
            valorDevuelto = -1;
        }

        return valorDevuelto;
    }


    public static boolean eliminarOrdenador(String nombreOrdenador) {
        File ordenadores = new File("ordenadores.txt");
        File copiaFile = new File("copia_ordenadores.txt");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ordenadores)); ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(copiaFile))) {

            boolean ordenadorEncontrado = false;

            while (true) {
                try {
                    PruebaClaseOrndeador ordenador = (PruebaClaseOrndeador) ois.readObject();
                    if (ordenador.getNombre().equals(nombreOrdenador)) {
                        ordenadorEncontrado = true;
                    } else {
                        oos.writeObject(ordenador);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            // Si el ordenador fue encontrado, renombrar el archivo temporal al original
            if (ordenadorEncontrado) {
                ordenadores.delete();
                copiaFile.renameTo(ordenadores);
                return true;
            } else {
                copiaFile.delete();
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al eliminar ordenador.");
            return false;
        }
    }

    public static List<PruebaClaseOrndeador> listarOrdenadores(String nombreArchivo) {
        List<PruebaClaseOrndeador> ordenadores = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nombreArchivo)))) {
            while (true) {
                try {
                    PruebaClaseOrndeador ordenador = (PruebaClaseOrndeador) ois.readObject();
                    ordenadores.add(ordenador);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de ordenadores: " + e.getMessage());
        }

        return ordenadores;
    }

}
