package proyecto;

import static proyecto.Ordenador.*;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * !!!ATENCION!!! DEBIDO A PROBLEMAS DEL METODO CLS HAY QUE PULSAR VARIAS VECES
 * ENTER SI NO SE VE NADA HASTA QUE APAREZCA LO QUE SE SUPONE QUE SE TIENE QUE
 * VER !!!ATENCION!!!
 *
 * admin:admin Este tiene un gran numero de intentos definidos por lo tanto si
 * se intenta loguear con este ususario erroneamente al ser el usuario base no
 * se eliminara a los 3 fallos
 *
 * APP de mi ordenador inteligente esta aplicacion inicia con un log in y tiene
 * distintas opciones enfocadas, a usuarios, ordenadores y lo que puede hacer el
 * ordenador
 *
 * @author Laudoiu Mihai Gabriel
 */
public class OrdenadorInteligenteApp {

    private static final String RUTA_ORDENADORES = "ordenadores.txt";
    private static final String RUTA = "usuarios.txt";
    private static final int INTENTOS_MAXIMOS = 3;

    public static void main(String[] args) throws AWTException, InterruptedException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean continuar = true;
        boolean loginExitoso = false;

        try {
            while (continuar) {
                MisterRobot.hablar("Bienvenido a Ordenador Intelihenta!");

                System.out.println("Bienvenido a Ordenador Inteligente App!");

                // Intentar iniciar sesion
                System.out.println("""
                                                     .----.
                                         .---------. | == |
                                         |.-\"\"\"""-.| |----|
                                         ||       || | == |
                                         ||       || |----|
                                         |'-.....-'| |::::|
                                         `"")---(""` |___.|
                                        /:::::::::::\\" _  "
                                       /:::=======:::\\`\\`\\
                                       `\"\"\"\"\"\"\"\"\"\"\"""`  '-'""");
                for (int intento = 0; intento < INTENTOS_MAXIMOS; intento++) {
                    System.out.println("Ingrese su nombre de usuario:");
                    String nombreUsuario = br.readLine();
                    System.out.println("Ingrese su contrasena:");
                    String pass = br.readLine();

                    loginExitoso = Usuarios.login(RUTA, nombreUsuario, pass);
                    if (loginExitoso) {
                        System.out.println("Inicio de sesion exitoso.");
                        MisterRobot.hablar("Bienvenido " + nombreUsuario);
                        continuar = true;
                        break;
                    } else {
                        System.out.println("Nombre de usuario o contrasena incorrectos. Intentos restantes: " + (INTENTOS_MAXIMOS - intento - 1));
                        if (intento == INTENTOS_MAXIMOS - 1) {
                            Usuarios.eliminarUsuario(RUTA, nombreUsuario, pass);
                        } else {
                            System.out.println("Desea registrar un nuevo usuario? (si/no)");
                            String respuesta = br.readLine().trim().toLowerCase();
                            if (respuesta.toLowerCase().equals("si")) {
                                registrarUsuario(br);
                            } else {
                                System.out.println("No registrando volviendo a la aplicacion");
                            }
                        }
                    }
                }

                if (!loginExitoso) {
                    System.out.println("Se excedieron los intentos de inicio de sesion.");
                    continuar = false;
                    return;
                }
                continuar();
                Dispositivo.cls();
                // Menu principal
                while (continuar) {
                    Dispositivo.cls();
                    System.out.println("Menu principal:");
                    System.out.println("1. Registrar usuario");
                    System.out.println("2. Modificar contrasena");
                    System.out.println("3. Mostrar usuarios");
                    System.out.println("4. Administrar Ordenadores");
                    System.out.println("5. Salir");

                    System.out.print("Seleccione una opcion: ");
                    String opcion = br.readLine();
                    switch (opcion) {
                        case "1" -> {
                            registrarUsuario(br);
                            continuar();
                        }
                        case "2" -> {
                            modificarContrasena(br);
                            
                        }
                        case "3" -> {
                            Usuarios.mostrarUsuarios();
                            continuar();
                        }
                        case "4" -> {
                            administrarOrdenadores(br);
                            continuar();
                        }
                        case "5" ->
                            continuar = false;
                        default -> {
                            System.out.println("Opcion no valida.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error de I/O");
            continuar();
        }
    }

    /**
     * *
     * Menu para la opcion de administrar ordenadores
     *
     * @param br
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */
    private static void administrarOrdenadores(BufferedReader br) throws IOException, AWTException, InterruptedException {
        boolean siguiente = false;
        while (!siguiente) {
            Dispositivo.cls();
            System.out.println("Menu de Gestion de Ordenadores:");
            System.out.println("1. Dar de alta un ordenador");
            System.out.println("2. Dar de baja un ordenador");
            System.out.println("3. Buscar un ordenador");
            System.out.println("4. Obtener listado de ordenadores");
            System.out.println("5. Usar ordenador.");
            System.out.println("6. Volver al menu principal");

            System.out.print("Seleccione una opcion: ");
            String opcionGestion = br.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");

            switch (opcionGestion) {
                case "1" ->
                    altaOrdenador();
                case "2" ->
                    bajaOrdenador(br);
                case "3" ->
                    buscarOrdenador(br);
                case "4" ->
                    lista();
                case "5" ->
                    usarOrdenador();
                case "6" -> {
                    siguiente = true;
                    System.out.println("Volviendo al menu principal.");
                }
                default -> {
                    System.out.println("Opcion no valida.");
                }
            }
        }
        continuar();
        Dispositivo.cls();
    }

    /**
     * *
     * Metodo que premite el registro de un usuario
     *
     * @param reader
     * @throws IOException
     */
    private static void registrarUsuario(BufferedReader reader) throws IOException {
        System.out.println("Registro de nuevo usuario:");

        String nombreUsuario;
        String contrasena;

        System.out.print("Ingrese el nombre de usuario: ");
        //utilizacion de regex reemplazará todos los caracteres que no son alfabéticos ni dígitos en la cadena obtenida desde la entrada del usuario con una cadena vacía
        nombreUsuario = reader.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");

        System.out.print("Ingrese la contrasena: ");
        contrasena = reader.readLine();

        if (nombreUsuario != null && contrasena != null) {
            if (Usuarios.registrarUsuario(RUTA, nombreUsuario, contrasena)) {
                System.out.println("Usuario registrado correctamente.");
                MisterRobot.hablar("User " + nombreUsuario + " registrado correctemanta");
            } else {
                System.out.println("No se pudo registrar el usuario. Es posible que el nombre de usuario ya este en uso.");
            }
        } else {
            System.out.println("No se pudieron obtener los datos de usuario y contrasena.");
        }
    }

    /**
     * *
     * Permite modificar la contraseña de un usuario
     *
     * @param br
     * @throws IOException
     */
    private static void modificarContrasena(BufferedReader br) throws IOException {
        System.out.println("Modificación de contrasena:");

        String nombreUsuario;
        String nuevaContrasena;

        System.out.print("Ingrese su nombre de usuario: ");
        nombreUsuario = br.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");

        System.out.print("Ingrese su nueva contrasena: ");
        nuevaContrasena = br.readLine();

        if (nombreUsuario != null && nuevaContrasena != null) {
            if (Usuarios.cambiarContrasena(RUTA, nombreUsuario, nuevaContrasena)) {
                System.out.println("Contrasena modificada correctamente.");
            } else {
                System.out.println("No se pudo modificar la contrasena. Es posible que el nombre de usuario no exista.");
            }
            continuar();
        } else {
            System.out.println("No se pudieron obtener los datos de usuario y contrasena.");
        }
        
        Dispositivo.cls();
    }

    /**
     * *
     * Permite crear un ordenador en nuestro archivo
     *
     * @throws IOException
     */
    private static void altaOrdenador() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nombre;
        String tarjetaGrafica;
        String[] perifericos;
        float altura = 0;
        float ancho = 0;
        Color color = null;
        boolean isPortatil = false;
        boolean valido = false;
        Tipo tipoOrdenador = null;
        SSOO sistemaOperativo = null;
        boolean esTactil = false;
        boolean tieneConexionInternet = false;
        String numeroSerie;
        float memoriaRAM = 0;
        String tipoCPU;

        try {
            System.out.print("Nombre del ordenador: ");
            nombre = br.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");

            System.out.print("Tipo de tarjeta grafica: ");
            tarjetaGrafica = br.readLine();

            System.out.print("Perifericos conectados (separados por comas): ");
            perifericos = br.readLine().split(",");

            while (!valido) {
                System.out.print("Es portatil? (true/false): ");
                String input = br.readLine().toLowerCase();
                try {
                    isPortatil = Boolean.parseBoolean(input);
                    valido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Por favor, ingrese 'true' o 'false'.");
                }
            }

            while (true) {
                try {
                    System.out.print("Altura del ordenador (en cm): ");
                    altura = Float.parseFloat(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Se esperaba un valor numerico.");
                }
            }

            while (true) {
                try {
                    System.out.print("Ancho del ordenador (en cm): ");
                    ancho = Float.parseFloat(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Se esperaba un valor numerico.");
                }
            }

            while (color == null) {
                System.out.print("Color del ordenador (GRIS, BLANCO, NEGRO, ROJO): ");
                try {
                    color = Color.valueOf(br.readLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: El color proporcionado no es valido.");
                }
            }

            while (tipoOrdenador == null) {
                System.out.print("Tipo del ordenador (LITE, NORMAL, ULTRA, ENTERPRISE): ");
                try {
                    tipoOrdenador = Tipo.valueOf(br.readLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: El tipo de ordenador proporcionado no es valido.");
                }
            }

            while (sistemaOperativo == null) {
                System.out.print("Sistema operativo (LINUX, UNIX, WINDOWS, ANDROID, MACOS, IOS): ");
                try {
                    sistemaOperativo = SSOO.valueOf(br.readLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: El sistema operativo proporcionado no es valido.");
                }
            }

            while (!valido) {
                System.out.print("Es tactil? (true/false): ");
                String input = br.readLine().toLowerCase();
                try {
                    esTactil = Boolean.parseBoolean(input);
                    valido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Por favor, ingrese 'true' o 'false'.");
                }
            }

            while (!valido) {
                System.out.print("Tiene conexion a internet? (true/false): ");
                String input = br.readLine().toLowerCase();
                try {
                    tieneConexionInternet = Boolean.parseBoolean(input);
                    valido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Por favor, ingrese 'true' o 'false'.");
                }
            }

            System.out.print("Numero de serie: ");
            numeroSerie = br.readLine();

            System.out.print("Memoria RAM (en GB): ");
            try {
                memoriaRAM = Float.parseFloat(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Se esperaba un valor numerico.");
            }

            System.out.print("Tipo de CPU: ");
            tipoCPU = br.readLine();

            EscritorLMG.escribirOrdenador(RUTA_ORDENADORES, isPortatil, tarjetaGrafica, perifericos, nombre, altura, ancho, color, tipoOrdenador, sistemaOperativo, esTactil, tieneConexionInternet, numeroSerie, memoriaRAM, tipoCPU);

        } catch (IOException e) {
            System.out.println("Error de I/O.");
        } finally {
            continuar();
            Dispositivo.cls();
        }
    }

    /**
     * *
     * Permite eliminar un ordenador en nuesrto archivo gracias a un archivo
     * temporal
     *
     * @param br
     * @throws IOException
     */
    private static void bajaOrdenador(BufferedReader br) throws IOException {
        String nombre;
        try {
            System.out.print("Introduzca el nombre del ordenador a eliminar: ");
            //regex
            nombre = br.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");
            Ordenador.eliminarOrdenador(RUTA_ORDENADORES, nombre);
        } catch (IOException e) {
            System.out.println("Error de I/O.");
        }
        continuar();
        Dispositivo.cls();
    }

    /**
     * *
     * Opcion del menu que permite buscar nu ordenador
     *
     * @param br
     * @throws IOException
     */
    private static void buscarOrdenador(BufferedReader br) throws IOException {
        String nombre;
        Ordenador o = new Ordenador();
        try {
            System.out.print("Introduzca el nombre del ordenador a buscar: ");
            nombre = br.readLine().replaceAll("[^\\p{IsAlphabetic}^\\d]", "");
            if (Ordenador.busquedaOrdenador(RUTA_ORDENADORES, nombre)) {
                System.out.println("Ordenador encontrado: " + nombre);
                System.out.println("");
                o = Ordenador.ordenarOrdenador("ordenadores.txt", nombre);
                o.toString();
            } else {
                System.out.println("Ordenador no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error de I/O.");
        }
        continuar();
        Dispositivo.cls();
    }

    /**
     * *
     * opcion del menu que nos permita listar los ordenadores
     *
     * @throws IOException
     */
    private static void lista() throws IOException {
        String listado;
        try {
            listado = Ordenador.listarNombresOrdenadores(RUTA_ORDENADORES);
            System.out.println("Listado de ordenadores: " + listado);
        } catch (IOException e) {
            System.out.println("Error al obtener el listado de ordenadores");
        }
        continuar();
        Dispositivo.cls();
    }

    /**
     * *
     * Metodo que nos dice que pulsameos para continuar
     *
     * @throws IOException
     */
    private static void continuar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pulsa Enter para continuar...");
        br.readLine();
    }

    /**
     * *
     * Opcion del menu que no deja usar el ordenador
     *
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */
    private static void usarOrdenador() throws IOException, AWTException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String rutaOrdenador = "ordenadores.txt";
        System.out.print("Introduzca el nombre del ordenador a usar: ");
        String nombreOrdenador = br.readLine();
        Ordenador ordenador = ordenarOrdenador(rutaOrdenador, nombreOrdenador);
        if (ordenador != null) {
            Ordenador.menu(ordenador);
        } else {
            System.out.println("El ordenador no fue encontrado.");
        }
        continuar();
        Dispositivo.cls();
    }
}
