package Pruebas;

import Pruebas.PruebaUsuarios;
import Pruebas.PruebaClaseOrndeador;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import proyecto.ObjectOutputStreamLMG;

/**
 * Escritor que ayuda a escribir en archivos
 *
 * @author mlaud
 */
public class PruebaEscritorLMG {

    private File fichEscritor;

    // Constructores
    public PruebaEscritorLMG() {
    }

    public PruebaEscritorLMG(File fichEscritor) {
        this.fichEscritor = fichEscritor;
    }

    // Getters y setters
    public File getFichEscritor() {
        return fichEscritor;
    }

    public void setFichEscritor(File fichEscritor) {
        this.fichEscritor = fichEscritor;
    }

    /**
     * Metodo que escribe un objeto PruebaClaseOrndeador en un fichero
     *
     * @param o objeto PruebaClaseOrndeador a escribir en el fichero
     * @return 1 si se ha podido escribir; -1 si no ha podido escribir
     */
    public int escribirLMG(PruebaClaseOrndeador o) {
        ObjectOutputStream oos = null;
        int valorDevuelto;
        try {
            if (fichEscritor.exists()) {
                oos = new ObjectOutputStreamLMG(new FileOutputStream(fichEscritor, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fichEscritor));
            }
            oos.writeObject(o);
            valorDevuelto = 1;
            oos.flush();
        } catch (IOException ioe) {
            System.out.print("ERROR de E/S en la escritura " + ioe.getMessage());
            valorDevuelto = -1;
        } catch (Exception e) {
            System.out.print("Se ha producido una EXCEPCION en la escritura " + e.getMessage());
            valorDevuelto = -1;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("No se pudo cerrar el stream en la escritura " + e.getMessage());
                }
            }
        }
        return valorDevuelto;
    }

    /**
     * Metodo que escribe un objeto PruebaUsuarios en un fichero
     *
     * @param u objeto PruebaClaseOrndeador a escribir en el fichero
     * @return 1 si se ha podido escribir; -1 si no ha podido escribir
     */
    public int escribirLMG(PruebaUsuarios u) {
        ObjectOutputStream oos = null;
        int valorDevuelto;
        try {
            if (fichEscritor.exists()) {
                oos = new ObjectOutputStreamLMG(new FileOutputStream(fichEscritor, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fichEscritor));
            }
            oos.writeObject(u);
            valorDevuelto = 1;
            oos.flush();
        } catch (IOException ioe) {
            System.out.print("ERROR de E/S en la escritura " + ioe.getMessage());
            valorDevuelto = -1;
        } catch (Exception e) {
            System.out.print("Se ha producido una EXCEPCION en la escritura " + e.getMessage());
            valorDevuelto = -1;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("No se pudo cerrar el stream en la escritura " + e.getMessage());
                }
            }
        }
        return valorDevuelto;
    }

    /**
     * Escribe en el fichero un conjunto de objetos de la clase PruebaClaseOrndeador,
 almacenados en un array
     *
     * @param lista array dinamico de Ordenadores a guardar en el fichero
     * @return el numero de ordenadores escritos si todo ha ido bien; -1 en caso
     * de error
     */
    public int escribirLMG(LinkedList<PruebaClaseOrndeador> lista) {
        // Uso de LinkedList
        ListIterator<PruebaClaseOrndeador> iteradorOrd = lista.listIterator();
        PruebaClaseOrndeador o;
        int valorDevuelto = 0;

        // Se recorre la lista
        while (iteradorOrd.hasNext()) {
            o = iteradorOrd.next();
            valorDevuelto = escribirLMG(o);
        }
        if (valorDevuelto == 1) {
            return lista.size();
        } else {
            return -1;
        }
    }

}
