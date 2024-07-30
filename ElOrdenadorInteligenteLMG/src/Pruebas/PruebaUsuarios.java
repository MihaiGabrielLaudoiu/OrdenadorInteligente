package Pruebas;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 *
 * @author Mihai Gabriel Laudoiu
 */
public class PruebaUsuarios implements Serializable {

    //Atributos
    private String nombre;
    private String pass;

    //Constructores
    public PruebaUsuarios(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    //Getter y setters
    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //Metodos
    /**
     * Metodo para registrar usuarios
     *
     * @param nombre
     * @param pass
     * @return
     */
    public static boolean registro(String nombre, String pass) {
        // regex
        String regex = "^[a-zA-Z0-9]{3,20}$";
        Pattern pattern = Pattern.compile(regex);
        
        if (!pattern.matcher(nombre).matches()) {
            System.out.println("Nombre debe tener entre 3 y 20 caracteres, solo letras y numeros, sin espacios.");
            return false;
        }

        File usuarios = new File("usuarios.txt");

        // Verificar si el nombre de usuario ya existe en el archivo
        boolean existe = existeUsuario(nombre, usuarios);
        if (existe) {
            // El usuario existe y no se puede crear
            return false;
        } else {
            // El usuario no existe y se crea
            PruebaUsuarios nuevoUsuario = new PruebaUsuarios(nombre, pass);
            PruebaEscritorLMG escritor = new PruebaEscritorLMG(usuarios);
            escritor.escribirLMG(nuevoUsuario);
            return true;
        }
    }

    /**
     * Método que verifica si un usuario existe en el archivo.
     *
     * @param nombre
     * @param usuarios
     * @return true si el usuario existe, false de lo contrario.
     */
    public static boolean existeUsuario(String nombre, File usuarios) {
        boolean existe = false;
        if (!usuarios.exists() || usuarios.length() == 0) {
            return false;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(usuarios))) {
            while (true) {
                try {
                    PruebaUsuarios usuario = (PruebaUsuarios) ois.readObject();
                    if (usuario.getNombre().equals(nombre)) {
                        existe = true;
                        break;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error");
        }
        return existe;
    }

    /**
     * Método para cambiar la contraseña de un usuario existente.Se usa un
     * archivo temporal
     *
     * @param nombre Nombre de usuario del usuario cuya contraseña se va a
     * cambiar.
     * @param contra Nueva contraseña que se va a asignar al usuario.
     * @return true si la contraseña se cambió correctamente, false si no
     */
    public static boolean cambiarContraseña(String nombre, String contra) {
        File usuarios = new File("usuarios.txt");
        boolean existe;

        existe = existeUsuario(nombre, usuarios);
        if (existe) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(usuarios)); ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.txt"))) {

                while (true) {
                    try {
                        PruebaUsuarios usuario = (PruebaUsuarios) ois.readObject();
                        if (usuario.getNombre().equals(nombre)) {
                            usuario.setPass(contra);
                        }
                        // Escribir el usuario en el archivo temporal
                        oos.writeObject(usuario);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error");
                return false;
            }

            // Renombrar el archivo
            File a = new File("a.txt");
            usuarios.delete();
            a.renameTo(usuarios);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para verificar si el usuario y la contraseña son válidos.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param contraseña Contraseña.
     * @return true si las credenciales son válidas, false si no.
     */
    public static boolean login(String nombreUsuario, String contraseña) {
        File usuariosFile = new File("usuarios.txt");
        boolean existe;

        existe = existeUsuario(nombreUsuario, usuariosFile);
        if (existe) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(usuariosFile))) {
                while (true) {
                    try {
                        PruebaUsuarios usuario = (PruebaUsuarios) ois.readObject();
                        if (usuario.getNombre().equals(nombreUsuario) && usuario.getPass().equals(contraseña)) {
                            return true;
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error");
                return false;
            }
        }
        return false;
    }

    /**
     * Método para eliminar un usuario del archivo de usuarios.
     *
     * @param nombreUsuario Nombre de usuario del usuario que se va a eliminar.
     * @return true si el usuario se eliminó correctamente, false si no se
     * encontró el usuario.
     */
    public static boolean eliminarUsuario(String nombreUsuario) {
        File usuariosFile = new File("usuarios.txt");
        File a = new File("a.txt");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(usuariosFile)); ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(a))) {

            boolean usuarioEncontrado = false;

            while (true) {
                try {
                    PruebaUsuarios usuario = (PruebaUsuarios) ois.readObject();
                    if (usuario.getNombre().equals(nombreUsuario)) {
                        usuarioEncontrado = true;
                    } else {
                        oos.writeObject(usuario);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            // Si el usuario fue encontrado, renombrar el archivo temporal al original
            if (usuarioEncontrado) {
                usuariosFile.delete();
                a.renameTo(usuariosFile);
                return true;
            } else {
                a.delete();
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al eliminar usuario.");
            return false;
        }
    }
}
