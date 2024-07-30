package proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para los objetos usuarios que nos permite distintas funcionalidades
 * para estos
 *
 * @author Laudoiu Mihai Gabriel
 */
public class Usuarios {

    //Atributos
    private static final String RUTA_USUARIOS = "usuarios.txt";

    String nombre;
    String pass;
    int intento = 3;
    //Constructores

    public Usuarios(String nombre, String pass, int intento) {
        this.nombre = nombre;
        this.pass = pass;
        this.intento = intento;
    }
    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public int getIntento() {
        return intento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIntento(int intento) {
        this.intento = intento;
    }
    //Metodos

    /**
     * Metodo login que permite iniciar sesion con un usuario que se encuentre
     * en una ruta, tiene 3 intentos por lo tanto si se sobrepasan se eliminara
     * el usuario.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre de usuario.
     * @param pass La contrasena del usuario.
     * @return true si el inicio de sesion es exitoso, false en caso contrario.
     */
    public static boolean login(String ruta, String nombre, String pass) {
        File inputFile = new File(ruta);
        File tempFile = new File("temp.txt");
        boolean login = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile)); BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datoUsuario = line.split("\\|");
                if (datoUsuario.length >= 3 && datoUsuario[0].equals(nombre)) { // Verifica si datoUsuario tiene al menos 3 elementos
                    int intentos = Integer.parseInt(datoUsuario[2]);
                    if (datoUsuario[1].equals(pass)) {
                        login = true;
                        int maxIntentos;
                        if (nombre.equals("admin")) {
                            maxIntentos = 9999;
                        } else {
                            maxIntentos = 3;
                        }
                        bw.write(datoUsuario[0] + "|" + datoUsuario[1] + "|" + maxIntentos);
                        bw.newLine();
                    } else {
                        intentos--;
                        if (intentos > 0) {
                            bw.write(datoUsuario[0] + "|" + datoUsuario[1] + "|" + intentos);
                            bw.newLine();
                            System.out.println("Contrasena incorrecta. Intentos restantes: " + intentos);
                        } else {
                            System.out.println("Usuario eliminado por exceder intentos fallidos.");
                        }
                    }
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo de usuarios.");
            return false;
        }
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error al actualizar el archivo de usuarios.");
            return false;
        }

        return login;
    }
    /***
     * Permite el registro de un usuario(se guardara en un archivo)
     * @param ruta
     * @param nombre
     * @param pass
     * @return 
     */
    public static boolean registrarUsuario(String ruta, String nombre, String pass) {
        // Validar el nombre de usuario con una expresión regular
        if (!nombre.matches("[a-zA-Z0-9]{3,20}")) {
            System.out.println("Error: El nombre de usuario debe contener solo letras y numeros, con longitud entre 3 y 20 caracteres.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] usuario = linea.split("\\|");
                if (usuario.length >= 1 && usuario[0].equals(nombre)) {
                    System.out.println("Error: Nombre de usuario ya existe.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(nombre + "|" + pass + "|3");
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al registrar usuario.");
            return false;
        }
    }

    /**
     * Método que permite eliminar un usuario de la lista de archivos.
     *
     * @param ruta La ruta del archivo de usuarios.
     * @param nombre El nombre de usuario a eliminar.
     * @param pass La contraseña del usuario a eliminar.
     */
    public static void eliminarUsuario(String ruta, String nombre, String pass) {
        try {
            File inputFile = new File(ruta);
            File tempFile = new File("a.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String linea;
            boolean userDeleted = false;

            while ((linea = br.readLine()) != null) {
                String[] usuario = linea.split("\\|");
                if (usuario.length == 3 && usuario[0].equals(nombre) && usuario[1].equals(pass)) {
                    userDeleted = true;
                    continue;
                }
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            br.close();
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Error al eliminar usuario.");
            }

            if (userDeleted) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("Usuario no encontrado o contraseña incorrecta.");
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar usuario.");
        }
    }

    /**
     * Cambia la contraseña de un usuario en el archivo de usuarios.
     *
     * @param ruta La ruta del archivo de usuarios.
     * @param nombre El nombre de usuario cuya contraseña se cambiará.
     * @param nuevaPass La nueva contraseña.
     * @return true si se cambió la contraseña correctamente, false si ocurrió
     * un error.
     */
    public static boolean cambiarContrasena(String ruta, String nombre, String nuevaPass) {
        File inputFile = new File(ruta);
        File tempFile = new File("a.txt");
        boolean changed = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile)); BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split("\\|");
                if (userData[0].equals(nombre)) {
                    bw.write(userData[0] + "|" + nuevaPass + "|" + userData[2]);
                    changed = true;
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en el archivo de usuarios.");
            return false;
        }
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error al actualizar el archivo de usuarios.");
            return false;
        }

        return changed;
    }

    /**
     * Muestra los usuarios registrados en el archivo de usuarios.
     */
    public static void mostrarUsuarios() {
        System.out.println("Usuarios registrados:");
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 1) {
                    System.out.println("Nombre de usuario: " + datos[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
        }
    }

    //ToString
    @Override
    public String toString() {
        return "Usuarios{" + "nombre=" + nombre + ", pass=" + pass + ", intento=" + intento + '}';
    }

}
