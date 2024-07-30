package Pruebas;

import Pruebas.PruebaClaseOrndeador;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class PruebaLectorLMG {

    private File fichLector;

    // Constructores
    public PruebaLectorLMG() {
    }

    public PruebaLectorLMG(File fichLector) {
        this.fichLector = fichLector;
    }

    // Getters y setters
    public File getFichLector() {
        return fichLector;
    }

    public void setFichLector(File fichLector) {
        this.fichLector = fichLector;
    }

    /**
     * Metodo que recorre un fichero y lista todos los Ordenadores almacenados
     * en el
     *
     * @return numero de ordenadores leidos si se ha podido leer; -1 si no ha podido
     * leer
     */
    public int leerLMG() {
        ObjectInputStream ois = null;
        int valorDevuelto = 0;
        PruebaClaseOrndeador o;
        try {
            ois = new ObjectInputStream(new FileInputStream(fichLector));
            o = (PruebaClaseOrndeador) ois.readObject();
            while (o != null) {
                valorDevuelto++;
                System.out.println("\t***********ORDENADOR " + (valorDevuelto));
                System.out.println(o.toString());
                o = (PruebaClaseOrndeador) ois.readObject();
            }
        } catch (EOFException eofe) {
            System.out.println("\t********************************************************************");
            System.out.println("\t*SE HAN LISTADO TODOS LOS DATOS DEL FICHERO. EN TOTAL: " + valorDevuelto + " ORDENADORES*");
            System.out.println("\t********************************************************************");
        } catch (IOException ioe) {
            System.out.println("\t***********ERROR de E/S en la lectura " + ioe.getMessage());
            valorDevuelto = -1;
        } catch (ClassNotFoundException cnfe) {
            System.out.print("\t***********La clase no se ha encontrado " + cnfe.getMessage());
            valorDevuelto = -1;
        } catch (Exception e) {
            System.out.print("\t***********Se ha producido una EXCEPCION en la lectura " + e.getMessage());
            valorDevuelto = -1;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("\t***********No se pudo cerrar el stream en la lectura " + e.getMessage());
                }
            }
        }
        return valorDevuelto;
    }

    /**
     * Metodo que lee de un fichero todos los objetos de la clase PruebaClaseOrndeador y
 los almacena en una lista dinamica
     *
     * @return LinkedList con todos los Ordenadores
     */
    public LinkedList<PruebaClaseOrndeador> leerLMGLista() {
        LinkedList<PruebaClaseOrndeador> listaOrdenadores = new LinkedList<>();
        PruebaClaseOrndeador o;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fichLector));
            o = (PruebaClaseOrndeador) ois.readObject();
            while (o != null) {
                listaOrdenadores.add(o);
                o = (PruebaClaseOrndeador) ois.readObject();
            }
        } catch (EOFException eofe) {
            System.out.println("EOF.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("La clase no se ha encontrado");
        } catch (IOException ioe) {
            System.out.println("ERROR de E S");
        } catch (Exception e) {
            System.out.println("Se ha producido una excepcion al recorrer el fichero");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        return listaOrdenadores;
    }

    /**
     * Metodo que lee de un fichero todos los objetos de la clase PruebaClaseOrndeador y
 busca el que coincida con el numeroSerie que se pasa por parametro, cuando lo
 encuentra devuelve el objeto
     *
     * @param numeroSerie del PruebaClaseOrndeador a buscar
     * @return el objeto PruebaClaseOrndeador si se ha encontrado; null si no se ha
 encontrado
     */
    public PruebaClaseOrndeador buscarLMG(String numeroSerie) {
        PruebaClaseOrndeador o = null;
        boolean encontrado = false;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fichLector));
            while (!encontrado) {
                try {
                    o = (PruebaClaseOrndeador) ois.readObject();
                    if (o != null && o.getNombre().equalsIgnoreCase(numeroSerie)) {
                        encontrado = true;
                    }
                } catch (EOFException eofe) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR al leer datos");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioe) {
                    System.out.println("Error al cerrar el stream");
                }
            }
        }
        return o;
    }

}
