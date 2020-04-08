/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamsandexceptions;

import java.util.Scanner;

/**
 * Programa para probar como se utilizan ficheros y excepciones en Java
 *
 * @author rgion
 * @version 1.2
 */
public class StreamsAndExceptions {

    /**
     * El programa principal espera que el usuario introduzca una opción, y
     * llama a métodos de lectura y escritura de ficheros según la opción
     * elegida.
     *
     * @see streamsandexceptions.StreamsAndExceptions#pedirOpcion()
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            try {
                switch (pedirOpcion()) {
                    case 1:
                        LecturaEscrituraStreams.leerEscribirByteByte();
                        break;
                    case 2:
                        LecturaEscrituraStreams.leerEscribirCarCar();
                        break;
                    case 3:
                        LecturaEscrituraStreams.leerEscribirBuffer();
                        break;
                    case 4:
                        tratamientoDeObjetos();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (RutaInvalida ex) {
                System.out.println("Mensaje:" + ex.getMessage());
                ex.impError();
            }
        }
    }

    /**
     * Muestra un menú por consola y se queda a la espera de que el usuario
     * introduzca una opción.
     *
     * @return Devuelve la opción elegida por el usuario.
     * <ul>
     * <li>Lectura y escritura Byte a Byte</li>
     * <li>Lectura y escritura carácter a carácter</li>
     * <li>Lectura y escritura con buffers</li>
     * <li>Tratamiento de objetos</li>
     * <li>Salir</li>
     * </ul>
     */
    public static int pedirOpcion() {
        Scanner leer = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("1) L/E Byte a byte");
        System.out.println("2) L/E Carácter a carácter");
        System.out.println("3) L/E con buffers");
        System.out.println("4) Tratamiento de objetos");
        System.out.println("5) Salir");
        System.out.println("---------------------------");
        System.out.println("Introduce una opción: ");
        return leer.nextInt();
    }

    /**
     * Al seleccionar la opción de tratamiento de ojetos, el programa vuelve a
     * esperar que el usuario elija que tipo de operaciones de lectura-escritura
     * con objetos quiere hacer.
     *
     * @see streamsandexceptions.StreamsAndExceptions#pedirOpcionObjetos()
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void tratamientoDeObjetos() throws RutaInvalida {

        boolean salir = false;
        while (!salir) {
            switch (pedirOpcionObjetos()) {
                case 1:
                    LecturaEscrituraStreams.leerLineaEscribirObj();
                    break;
                case 2:
                    LecturaEscrituraStreams.leerObjEscribirObj();
                    break;
                case 3:
                    LecturaEscrituraStreams.leerObjEscribirCons();
                    break;
                case 4:
                    LecturaEscrituraStreams.leerConsEscribirObj();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Muestra un menú por consola y se queda a la espera de que el usuario
     * introduzca una opción.
     *
     * @return Devuelve la opción elegida por el usuario. <ul>
     * <li>Lectura línea a línea y escritura con objetos.</li>
     * <li>Lectura de objetos y escritura de objetos.</li>
     * <li>Lectura de objetos y escritura por consola.</li>
     * <li>Lectura por consola y escritura de objetos.</li>
     * <li>Volver al menú anterior</li>
     * </ul>
     */
    public static int pedirOpcionObjetos() {
        Scanner leer = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("1) Lectura línea a línea y escritura con objetos.");
        System.out.println("2) Lectura de objetos y escritura de objetos.");
        System.out.println("3) Lectura de objetos y escritura por consola.");
        System.out.println("4) Lectura por consola y escritura de objetos. (añadirá objetos al final del fichero existente)");
        System.out.println("5) Volver al menú principal.");
        System.out.println("---------------------------");
        System.out.println("Introduce una opción: ");
        return leer.nextInt();
    }

}
