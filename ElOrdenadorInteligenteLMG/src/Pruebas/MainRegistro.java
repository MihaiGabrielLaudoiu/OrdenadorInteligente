package Pruebas;
import Pruebas.PruebaUsuarios;
import Pruebas.PruebaClaseOrndeador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import proyecto.Color;
import proyecto.SSOO;
import proyecto.Tipo;
import static Pruebas.PruebaClaseOrndeador.*;

public class MainRegistro {

    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nombreUsuario;
        String pass;
        int intentos = 3;
        String opcion;
        String nuevoNombreUsuario;
        String nuevaPass;
        String opc;
        boolean esPortatil;
        String tarjetaGrafica;
        String[] perifericos;
        String nombre;
        float altura;
        float ancho;
        Color color;
        Tipo tipo;
        SSOO sso;
        boolean esTactil;
        boolean tieneConexionInternet;
        float memoriaRAM;
        String tipoCPU;
        PruebaClaseOrndeador nuevoOrdenador;
        int resultado;

        while (intentos > 0) {
            System.out.println("Menu:");
            System.out.println("1. Login");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("Elige una opcion:");

            try {
                opcion = bf.readLine();

                switch (opcion) {
                    case "1":
                        System.out.println("Iniciar sesion:");
                        System.out.println("Nombre de usuario:");
                        nombreUsuario = bf.readLine();
                        System.out.println("Contrasena:");
                        pass = bf.readLine();

                        if (PruebaUsuarios.login(nombreUsuario, pass)) {
                            System.out.println("Inicio de sesion exitoso.");
                            // Menu adicional
                            while (true) {
                                System.out.println("Menu adicional:");
                                System.out.println("1. Agregar Ordenador");
                                System.out.println("2. Eliminar Ordenador");
                                System.out.println("3. Buscar ordenador");
                                System.out.println("4. Obtener listado");
                                System.out.println("5. Usar ordenador");
                                System.out.println("6. Volver al menu principal");
                                System.out.println("Elige una opcion:");

                                try {
                                    opc = bf.readLine();
                                    switch (opc) {
                                        case "1":
                                            
                                            break;

                                        case "2":
                                            System.out.println("Eliminar ordenador:");
                                            System.out.println("Ingrese el nombre del ordenador a eliminar:");
                                            String nombreOrdenadorEliminar = bf.readLine();
                                            if (eliminarOrdenador(nombreOrdenadorEliminar)) {
                                                System.out.println("Ordenador '" + nombreOrdenadorEliminar + "' eliminado con exito.");
                                            } else {
                                                System.out.println("No se encontró ningún ordenador con el nombre '" + nombreOrdenadorEliminar + "'.");
                                            }
                                            break;

                                        case "3":

                                            break;

                                        case "4":
                                            List<PruebaClaseOrndeador> listaOrdenadores = PruebaClaseOrndeador.listarOrdenadores("ordenadores.txt");
                                            for (PruebaClaseOrndeador ordenador : listaOrdenadores) {
                                                System.out.println(ordenador.toString());
                                            }

                                            break;

                                        case "5":

                                            break;
                                        case "6":
                                            System.out.println("Saliendo...");
                                            return;
                                        default:
                                            System.out.println("Opcion no valida.");
                                            break;
                                    }
                                } catch (IOException e) {
                                    System.out.println("Error IO.");
                                }
                            }
                        } else {
                            System.out.println("Nombre de usuario o contrasena incorrectos.");
                            intentos--;
                            System.out.println("Intentos restantes: " + intentos);
                        }
                        break;

                    case "2":
                        System.out.println("Registrarse:");
                        System.out.println("Nombre de usuario:");
                        nuevoNombreUsuario = bf.readLine();
                        System.out.println("Contrasena:");
                        nuevaPass = bf.readLine();

                        if (PruebaUsuarios.registro(nuevoNombreUsuario, nuevaPass)) {
                            System.out.println("Registro exitoso.");
                        } else {
                            System.out.println("El nombre de usuario ya esta en uso.");
                        }
                        break;

                    case "3":
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error al leer la entrada del usuario.");
            }
        }

        System.out.println("Limite de intentos alcanzado. Saliendo...");

    }
}
