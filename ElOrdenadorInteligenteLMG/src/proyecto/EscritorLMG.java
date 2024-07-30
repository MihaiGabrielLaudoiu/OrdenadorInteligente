package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Clase para escribir información en un archivo.
 *
 * @author Laudoiu Mihai Gabriel
 */
public class EscritorLMG {
    //Metodos que pide la tarea
    //He creado dos metodos comunes uno para escribir un solo objeto y otro para crear varios objetos, puesto que objeto en mi caso puede ser usuario o ordenador y el metodo es comun, usamos T
    /**
     * Devuelve 1 si se consigue y -1 si salta algun catch escribe en fichero
     * objeto pasado por parametro
     *
     * @param <T>
     * @param ruta
     * @param objeto
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static <T> int escribirLMG(String ruta, T objeto) throws FileNotFoundException, IOException {
        String rutaArchivo = ruta;
        T obj = objeto;

        File archivo = new File(rutaArchivo);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            oos.writeObject(obj);
            oos.close();
            return 1;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * *
     * escriba en el fichero un conjunto de objetos de la clase, almacenados en
     * una lista dinámica que se le pasará como parámetro. Devuelve el nº de
     * objetos escritos si la operación de escritura tiene éxito; -1 si ocurre
     * alguna excepción.
     *
     * @param <T>
     * @param ruta
     * @param objetos
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static <T> int escribirListaLMG(String ruta, List<T> objetos) throws FileNotFoundException, IOException {
        String rutaArchivo = ruta;
        List<T> listaObjetos = objetos;

        File archivo = new File(rutaArchivo);
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(archivo));
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            for (T objeto : listaObjetos) {
                oos.writeObject(objeto);
            }
            return listaObjetos.size();
        } catch (IOException e) {
            System.out.println("Error en la escritura");
            return -1;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Error en la escritura");
                }
            }
        }
    }

    //Metodos adicionales
    /**
     * Metodo privado para escribir una fila1 en un archivo.
     *
     * @param ruta La ruta del archivo.
     * @param fila La fila1 a escribir.
     */
    private static void escribirFila(String ruta, String fila) {
        String rutaArchivo = ruta;
        String fila1 = fila;

        try {
            try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
                writer.write(fila1 + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

    /**
     * Metodo para imprimir la informacion de un ordenador en un archivo.
     *
     * @param ruta La ruta del archivo.
     * @param esPortatil Indica si el ordenador es portatil.
     * @param tarjetaGrafica La tarjeta grafica del ordenador.
     * @param perifericos Los perifericos conectados al ordenador.
     * @param nombre El nombre del ordenador.
     * @param altura La altura del ordenador.
     * @param ancho El ancho del ordenador.
     * @param color El color del ordenador.
     * @param tipo El tipo del ordenador.
     * @param ssoo El sistema operativo del ordenador.
     * @param esTactil Indica si el ordenador es tactil.
     * @param tieneConexionInternet Indica si el ordenador tiene conexion a
     * internet.
     * @param numeroSerie El numero de serie del ordenador.
     * @param memoriaRAM La cantidad de memoria RAM del ordenador.
     * @param tipoCPU El tipo de CPU del ordenador.
     */
    public static void escribirOrdenador(String ruta, boolean esPortatil, String tarjetaGrafica, String[] perifericos, String nombre, float altura, float ancho, Color color, Tipo tipo, SSOO ssoo, boolean esTactil, boolean tieneConexionInternet, String numeroSerie, float memoriaRAM, String tipoCPU) {
        String rutaArchivo = ruta;
        boolean portatil = esPortatil;
        String grafica = tarjetaGrafica;
        String[] dispositivos = perifericos;
        String nombreOrdenador = nombre;
        float alto = altura;
        float anchoOrdenador = ancho;
        Color colorOrdenador = color;
        Tipo tipoOrdenador = tipo;
        SSOO sistemaOperativo = ssoo;
        boolean tactil = esTactil;
        boolean conexionInternet = tieneConexionInternet;
        String serie = numeroSerie;
        float ram = memoriaRAM;
        String cpu = tipoCPU;

        String fila = String.join("||", String.valueOf(portatil), grafica, String.join("|", dispositivos), nombreOrdenador, String.valueOf(alto), String.valueOf(anchoOrdenador), colorOrdenador.toString(), tipoOrdenador.toString(), sistemaOperativo.toString(), String.valueOf(tactil), String.valueOf(conexionInternet), serie, String.valueOf(ram), cpu);
        escribirFila(rutaArchivo, fila);
    }

}
