/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Pruebas.PruebaClaseOrndeador;
import java.io.EOFException;
import java.util.List;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import proyecto.Color;
import proyecto.SSOO;
import proyecto.Tipo;
import static Pruebas.PruebaClaseOrndeador.escribirOrdenador;

public class pruebas {

    public static void main(String[] args) {
        Color color = Color.NEGRO;
        Tipo tipo = Tipo.NORMAL;
        SSOO sso = SSOO.WINDOWS;

        String nombre = "MiOrdenador";
        float altura = 30.5f;
        float ancho = 20.0f;

        String tarjetaGrafica = "NVIDIA GeForce RTX 3080";
        String perifericos = "as";

        boolean esPortatil = false;
        boolean esTactil = true;
        boolean tieneConexionInternet = true;

        String numeroSerie = "1234567890";
        float memoriaRAM = 16.0f;
        String tipoCPU = "Intel Core i9";

        // Crear el objeto PruebaClaseOrndeador
        PruebaClaseOrndeador miOrdenador = new PruebaClaseOrndeador(esPortatil);
        
        // Guardar el objeto en un archivo
        int resultado = escribirOrdenador(miOrdenador);
        
        if (resultado == 1) {
            System.out.println("El objeto Ordenador se ha guardado correctamente en el archivo.");
        } else {
            System.out.println("Hubo un error al intentar guardar el objeto Ordenador en el archivo.");
        }

//        // Listar los objetos PruebaClaseOrndeador del archivo
//        List<Ordenador> listaOrdenadores = listarOrdenadores("ordenadores.txt");
//        System.out.println("Lista de Ordenadores:");
//        for (PruebaClaseOrndeador ordenador : listaOrdenadores) {
//            System.out.println(ordenador);
//        }
//    }

//    public static List<Ordenador> listarOrdenadores(String nombreArchivo) {
//        List<Ordenador> ordenadores = new ArrayList<>();
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nombreArchivo)))) {
//            while (true) {
//                try {
//                    PruebaClaseOrndeador ordenador = (PruebaClaseOrndeador) ois.readObject();
//                    ordenadores.add(ordenador);
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error al leer el archivo de ordenadores: " + e.getMessage());
//        }
//
//        return ordenadores;
    }
}
