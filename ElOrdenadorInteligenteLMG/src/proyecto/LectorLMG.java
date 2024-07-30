package proyecto;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

/**
 *Clase que nos da distintas funcionalidades que tienen que ver con el lector
 * @author Laudoiu Mihai Gabriel
 */
public class LectorLMG {

    //Metodos necesarios
    /**
     * Lee del fichero todos los objetos de la clase y escribe su información
     * por consola. Devuelve el número de objetos leídos si la operación de
     * lectura ha tenido éxito; -1 si se ha producido alguna excepción.
     *
     * @param ruta La ruta del archivo.
     * @return El número de objetos leídos si la operación fue exitosa, de lo
     * contrario -1.
     */
    public static int leerLMG(String ruta) {
        String rutaArchivo = ruta;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            int objetosLeidos = 0;
            while (true) {
                try {
                    Object objeto = ois.readObject();
                    if (objeto != null) {
                        System.out.println(objeto.toString());
                        objetosLeidos++;
                    } else {
                        break;
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
            return objetosLeidos;
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
            return -1;
        } catch (IOException e) {
            System.out.println("Error de E/S.");
            return -1;
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
            return -1;
        }
    }

    /**
     * Lee del fichero todos los objetos de la clase y devuelve una lista
     * dinámica con todos ellos.
     *
     * @param ruta La ruta del archivo.
     * @return Una lista dinámica con todos los objetos leídos del archivo.
     */
    public static LinkedList<Object> leerLMGLista(String ruta) {
        String rutaArchivo = ruta;
        LinkedList<Object> listaObjetos = new LinkedList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            while (true) {
                try {
                    Object objeto = ois.readObject();
                    if (objeto != null) {
                        listaObjetos.add(objeto);
                    } else {
                        break;
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error de E/S.");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }

        return listaObjetos;
    }

    /**
     * Busca y recupera el objeto cuyo valor coincida con el indicado en el
     * parámetro que le debes pasar, tú decides el parámetro por el que buscar,
     * pero ten en cuenta que debe tener sentido y debe ser único. Devuelve el
     * objeto de la clase si es que existe; null si no se encontró o se produce
     * alguna excepción. Debe comprobar si el fichero existe o no.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del ordenador a buscar.
     * @return El objeto Ordenador si se encuentra, null si no se encontró o se
     * produce alguna excepción.
     */
    public static Ordenador buscarLMG(String ruta, String nombre) {
        String rutaArchivo = ruta;
        Ordenador ordenador = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            while (true) {
                try {
                    Object objeto = ois.readObject();
                    if (objeto != null && objeto instanceof Ordenador) {
                        ordenador = (Ordenador) objeto;
                        if (ordenador.getNombre().equalsIgnoreCase(nombre)) {
                            return ordenador;
                        }
                    } else {
                        break;
                    }
                } catch (EOFException eof) {
                    System.out.println("Error fin de archivo");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error de E/S.");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }

        return ordenador;
    }

    //Metodos adicionales
    /**
     * Lee un archivo línea por línea y lo imprime por consola.
     *
     * @param ruta La ruta del archivo a leer.
     */
    public static void leerArchivo(String ruta) {
        String rutaArchivo = ruta;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

}
