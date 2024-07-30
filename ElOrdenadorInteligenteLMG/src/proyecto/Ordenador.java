package proyecto;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Random;

/**
 * Clase que representa un ordenador, extendiendo la funcionalidad de
 * Dispositivo e implementando la interfaz Conectable.
 *
 * @autor Mihai Gabriel Laudoiu
 */
public class Ordenador extends Dispositivo implements Conectable, Serializable {

    private boolean esPortatil;
    private String tarjetaGrafica;
    private String[] perifericos;

    // Constructores
    public Ordenador() {
    }

    /**
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
     * @param ssoo
     * @param esTactil
     * @param tieneConexionInternet
     * @param numeroSerie
     * @param memoriaRAM
     * @param tipoCPU
     */
    public Ordenador(boolean esPortatil, String tarjetaGrafica, String[] perifericos, String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO ssoo, boolean esTactil, boolean tieneConexionInternet, String numeroSerie, float memoriaRAM, String tipoCPU) {
        super(nombre, altura, ancho, color, tipo, ssoo, esTactil, tieneConexionInternet, numeroSerie, memoriaRAM, tipoCPU);
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

    // Métodos específicos del ordenador
    /**
     * Metodo para programar el ordenador. Imprime un codigo Java basico.
     */
    public void programar() {
        System.out.println("public static void main(String[] args) {");
        System.out.println("    System.out.println(\"Hola mundo\");");
        System.out.println("}");
        System.out.println("Hola mundo!");
    }

    /**
     * Método para jugar adivinanzas.
     *
     * @throws IOException Si hay un error de entrada/salida.
     */
    public void jugarAdivinanza() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();
        int numeroAleatorio;
        int intentos = 0;
        int intento = 0;
        // Se guarda un número aleatorio entre 1 y 100
        numeroAleatorio = random.nextInt(100) + 1;
        System.out.println("Bienvenido al juego de adivinanzas!");
        System.out.println("Introduce un numero entre 1 y 100");

        // Si el valor introducido por el usuario es menor o mayor se le indicará para que siga buscando y se contarán los intentos que hace
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
        System.out.println("El numero era " + intento);
    }

    /**
     * Metodo que selecciona una broma de un array y la imprime.
     */
    public void generarBroma() {
        Random random = new Random();
        int num;
        // Se declara un array
        String[] bromas = {
            "Por que los programadores odian el mar? Porque no pueden parar de contar hasta la playa.",
            "Que le dice un arbol a otro? No me toques las ramas!",
            "Que hace una abeja en el gimnasio? Zum-baHAHAHAHAHAHAH!",
            "Como se dice dentista en chino? Loco muela.",
            "Por que los pajaros no usan Facebook? Porque ya tienen Twitter.",
            "Cual es el animal mas antiguo? La cebra, porque esta en blanco y negro."
        };
        num = random.nextInt(bromas.length);
        // Se elige un numero aleatorio del tamano del array para que siempre salga una broma en rango y la imprime
        System.out.println("Aqui tienes una broma:");
        System.out.println(bromas[num]);
        MisterRobot.hablar(bromas[num]);
    }

    /**
     * Metodo que simula una calculadora basica.
     *
     * @throws IOException Si hay un error de entrada/salida.
     */
    public void usarCalculadora() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double numero1 = 0;
        double numero2 = 0;
        int opc = 0;
        double resultado = 0;
        // Pide al usuario 2 numeros y luego le da un menu para elegir que tipo de operacion quiere hacer con esos dos numeros
        System.out.println("La calculadora basica!");
        System.out.println("Introduce el primer numero:");
        try {
            numero1 = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("El valor introducido no es correcto");
        }

        System.out.println("Por favor, introduce el segundo numero:");
        try {
            numero2 = Double.parseDouble(reader.readLine());
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
        MisterRobot.hablar("El resultado es: " + resultado);

    }

    /**
     * Metodo para calcular la dimension del ordenador.
     *
     * @return Una cadena con la dimension del ordenador.
     */
    @Override
    public String calcularDimension() {
        String ret;
        // Dependiendo de los valores de los atributos del ordenador te dice si es muy grande o muy pequeno o normal
        if (altura < 0.5 && ancho < 0.3) {
            ret = "Mini ordenador";
        } else if (altura > 0.9 && ancho > 0.6) {
            ret = "Ordenador gigantesco";
        } else {
            ret = "Ordenador de dimensiones normales";
        }

        return ret;
    }

    /**
     * Implementacion de la interfaz Conectable.
     *
     * @return true si el dispositivo esta conectado a internet, false en caso
     * contrario.
     */
    @Override
    public boolean conectable() {
        // Definimos si el dispositivo esta conectado a internet y utiliza la excepcion personalizada
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
                System.out.println("Error");
            }
        } while (true);
    }

    /**
     * Metodo para convertir un array de Strings a una cadena con un separador.
     *
     * @param array El array de Strings a convertir.
     * @param separador El separador a utilizar entre los elementos.
     * @return La cadena resultante con los elementos del array separados por el
     * separador.
     */
    public static String pasarDeArrayACadena(String[] array, String separador) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder cadena = new StringBuilder();
        for (String valor : array) {
            cadena.append(valor).append(separador);
        }

        // Eliminar el ultimo separador
        cadena.delete(cadena.length() - separador.length(), cadena.length());
        return cadena.toString();
    }

    /**
     * *
     *Permite continuar al pulsar enter o otra tecla
     * @throws IOException
     */
    private static void continuar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pulsa Enter para continuar...");
        br.readLine();
    }

    /**
     * Menú para probar las funcionalidades del ordenador.
     *
     * @param ordenador1 El ordenador a probar.
     * @throws IOException Si hay un error de entrada/salida.
     * @throws java.awt.AWTException Si hay un error de AWT (Abstract Window
     * Toolkit).
     * @throws java.lang.InterruptedException Si se interrumpe el hilo mientras
     * duerme.
     */
    public static void menu(Ordenador ordenador1) throws IOException, AWTException, InterruptedException {
        boolean salir = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {
            Dispositivo.cls();
            MisterRobot.hablar("Lets go!");
            System.out.println("1. Calcular dimensiones");
            System.out.println("2. Programar");
            System.out.println("3. Jugar adivinanza");
            System.out.println("4. Generar broma");
            System.out.println("5. Usar calculadora");
            System.out.println("6. Conectar a internet");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opcion: ");
            try {
                opcion = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Solo se admiten ciertos valores.");
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    System.out.println(ordenador1.calcularDimension());
                    continuar();
                    break;
                case 2:
                    ordenador1.programar();
                    continuar();
                    break;
                case 3:
                    ordenador1.jugarAdivinanza();
                    continuar();
                    break;
                case 4:
                    ordenador1.generarBroma();
                    continuar();
                    break;
                case 5:
                    ordenador1.usarCalculadora();
                    continuar();
                    break;
                case 6:
                    System.out.println("El dispositivo esta conectado a internet: " + ordenador1.conectable());
                    continuar();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    continuar();
            }
        } while (!salir);
    }

    /**
     * Método para ordenar un ordenador.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del ordenador.
     * @return El objeto Ordenador ordenado.
     */
    public static Ordenador ordenarOrdenador(String ruta, String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|\\|");
                if (partes[3].equals(nombre)) {
                    boolean esPortatil = Boolean.parseBoolean(partes[0]);
                    String tarjetaGrafica = partes[1];
                    String[] perifericos = partes[2].split("\\|");
                    float altura = Float.parseFloat(partes[4]);
                    float ancho = Float.parseFloat(partes[5]);
                    Color color = Color.valueOf(partes[6]);
                    Tipo tipo = Tipo.valueOf(partes[7]);
                    SSOO ssoo = SSOO.valueOf(partes[8]);
                    boolean esTactil = Boolean.parseBoolean(partes[9]);
                    boolean tieneConexionInternet = Boolean.parseBoolean(partes[10]);
                    String numeroSerie = partes[11];
                    float memoriaRAM = Float.parseFloat(partes[12]);
                    String tipoCPU = partes[13];

                    return new Ordenador(esPortatil, tarjetaGrafica, perifericos, nombre, altura, ancho, color, tipo, ssoo, esTactil, tieneConexionInternet, numeroSerie, memoriaRAM, tipoCPU);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir valores numéricos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al convertir valores de enumeración: " + e.getMessage());
        }
        return null;
    }

    /**
     * Le pasamos un archivo por parametro y nos devuelve un string con los
     * nombres bien parseados.
     *
     * @param archivo La ruta del archivo.
     * @return Un string con los nombres bien parseados.
     * @throws IOException Si hay un error de entrada/salida.
     */
    public static String listarNombresOrdenadores(String archivo) throws IOException {
        StringBuilder nombres = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|\\|");
                if (partes.length >= 5) {
                    String nombre = partes[3];
                    if (nombres.length() > 0) {
                        nombres.append(", ");
                    }
                    nombres.append(nombre);
                }
            }
        }
        return nombres.toString();
    }

    /**
     * Le damos una ruta y nombre y nos devuelve true o false si lo ha
     * encontrado o no.
     *
     * @param rutaFichero La ruta del archivo.
     * @param nombreBuscado El nombre que estamos buscando.
     * @return true si se encuentra el nombre buscado, false en caso contrario.
     */
    public static boolean busquedaOrdenador(String rutaFichero, String nombreBuscado) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split("\\|\\|");
                if (columnas.length >= 4) {
                    String nombre = columnas[3];
                    if (nombre.equals(nombreBuscado)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
        return false;
    }

    /**
     * Le damos una ruta y un nombre y nos elimina el ordenador de la lista
     * gracias a que crea una lista temporal.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del ordenador a eliminar.
     */
    public static void eliminarOrdenador(String ruta, String nombre) {
        File archivo = new File(ruta);
        File aTemporal = new File("a.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo)); BufferedWriter bw = new BufferedWriter(new FileWriter(aTemporal))) {

            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split("\\|\\|");
                if (columnas.length >= 4 && columnas[3].equals(nombre)) {
                    encontrado = true;
                    continue;
                }
                bw.write(linea);
                bw.newLine();
            }

            if (encontrado) {
                System.out.println("El ordenador con nombre " + nombre + " ha sido eliminado.");
            } else {
                System.out.println("No se encontró un ordenador con nombre " + nombre + ".");
            }

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }

        if (!archivo.delete()) {
            System.out.println("Error al eliminar el archivo original.");
            return;
        }
        if (!aTemporal.renameTo(archivo)) {
            System.out.println("Error al renombrar el archivo temporal.");
        }
    }

    //toString
    @Override
    public String toString() {
        System.out.println("===== Informacion del Ordenador =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Altura: " + altura + " metros");
        System.out.println("Ancho: " + ancho + " metros");
        System.out.println("Color: " + color);
        System.out.println("Tipo: " + tipo);
        System.out.println("Sistema Operativo: " + ssoo);
        System.out.println("Es Tactil: " + (isTactil() ? "Si" : "No"));
        System.out.println("Tiene Conexion a Internet: " + (isConexionInternet() ? "Si" : "No"));
        System.out.println("Numero de Serie: " + numeroSerie);
        System.out.println("Memoria RAM: " + memoria + " GB");
        System.out.println("Tipo de CPU: " + cpu);
        System.out.println("Es Portatil: " + (esPortatil ? "Si" : "No"));
        System.out.println("Tarjeta Grafica: " + tarjetaGrafica);
        System.out.println("Perifericos: " + pasarDeArrayACadena(perifericos, ", "));
        System.out.println("=====================================");
        return "";
    }

}
