/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamsandexceptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * En esta clase tenemos distintos métodos de lectura y escritura de ficheros
 *
 * @author rgion
 */
public final class LecturaEscrituraStreams {

    private final static String[] campos = {"Título: ", "Año: ", "Director: ", "Duración: ", "Sinopsis: ", "Reparto: ", "Sesión: "};
    private final static String lineSeparator = System.getProperty("line.separator");

    /**
     * Pide al usuario la ruta de un fichero
     *
     * Si la ruta del fichero de salida es incorrecta (que esté vacia), pedirá
     * constantemente que se introduzca una ruta hasta que esta sea correcta.
     *
     * @param tipo Tipo del fichero (entrada/salida)
     * @return Devuelve la ruta del fichero
     */
    public static String pedirRuta(String tipo) throws RutaInvalida {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo de " + tipo + ":");
        System.out.println("(Ejemplo archivo.txt)");
        String ruta = leer.nextLine();
        if ("".equals(ruta)) { // si la ruta introducida está vacía        
                throw new RutaInvalida("Ruta de "+tipo+ " sin informar"); //lanza la excepción de Fichero de Salida       
        }
        return ruta;
    }

    /**
     * Método de lectura y escritura Byte a Byte.
     *
     * Leerá un fichero con InputStream y escribirá en otro con OutputStream.
     *
     * @see java.io.InputStream
     * @see java.io.OutputStream
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Pedirá la ruta del fichero a escribir</li>
     * <li>Leerá byte a byte el fichero de entrada y escribirá en el de salida
     * con un formato en particular</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerEscribirByteByte() throws RutaInvalida {
        try (FileInputStream fin = new FileInputStream(pedirRuta("entrada"));
                FileOutputStream fout = new FileOutputStream(pedirRuta("salida"))) {
            int c;
            int i = 0;
            fout.write(campos[i].getBytes());
            do {
                c = fin.read(); // lee un byte (carácter) 
                if (c != -1 && (char) c != '#' && (char) c != '{') { // si no es el fin del documento, no es un '#' ni un '{'
                    if ((char) c == '\n') {
                        fout.write(' ');
                    } else {
                        fout.write(c); //escribe el carácter en el fichero de salida
                    }
                } else if ((char) c == '#') { //si el carácter es un '#' es un nuevo campo
                    if (i == 3) {
                        fout.write(" minutos".getBytes()); // comprobamos si estamos saliendo del campo duración
                    }
                    i = (i + 1) % 7; //aumenta el índice de campo (esta operación permite que siempre sea un número de 0 a 6)
                    fout.write(lineSeparator.getBytes()); //línea en blanco
                    fout.write(campos[i].getBytes()); // escribe el campo
                } else if ((char) c == '{') { // si es un '{' empieza una película nueva
                    i = (i + 1) % 7; //aumenta el índice de campo
                    fout.write((lineSeparator + lineSeparator).getBytes()); // 2 líneas en blanco
                    fout.write(campos[i].getBytes()); //escribe el campo (debería ser "Título")
                }
            } while (c != -1); //mientras no se llegue al final del documento
        }  catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        }
    }

    /**
     * Método de lectura y escritura Carácter a Carácter.
     *
     * Leerá un fichero con FileReader y escribirá en otro con FileWriter.
     *
     * @see java.io.FileReader
     * @see java.io.FileWriter
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Pedirá la ruta del fichero a escribir</li>
     * <li>Leerá carácter a carácter el fichero de entrada y escribirá en el de
     * salida con un formato en particular</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerEscribirCarCar() throws RutaInvalida {
        try (FileReader lector = new FileReader(pedirRuta("entrada"));
                FileWriter escritor = new FileWriter(pedirRuta("salida"))) {
            int i = 0;
            int c;
            escritor.write(campos[i]);
            do {
                c = lector.read(); // lee un carácter 
                if (c != -1 && c != '#' && c != '{') { // si no es el fin del documento, no es un '#' ni un '{'
                    if (c == '\n') {
                        escritor.write(' '); //si es un salto de línea escribe un blanco
                    } else {
                        escritor.write(c); //escribe el carácter
                    }
                } else if (c == '#') { //si el carácter es un '#' es un nuevo campo
                    if (i == 3) {
                        escritor.write(" minutos");
                    }
                    i = (i + 1) % 7;
                    escritor.write(lineSeparator);
                    escritor.write(campos[i]); // escribe el campo
                } else if ((char) c == '{') { // si es un '{' empieza una película nueva
                    i = (i + 1) % 7;
                    escritor.write(lineSeparator + lineSeparator);
                    escritor.write(campos[i]); // escribe el campo (debería ser "Título")
                }
            } while (c != -1);
        } catch (FileNotFoundException ex) {
            throw new RutaInvalida(); //lanza esta excepción si la ruta del fichero de entrada no tiene ningún fichero
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        }
    }

    /**
     * Método de lectura y escritura con Buffers.
     *
     * Leerá un fichero con BufferedReader y escribirá en otro con
     * BufferedWriter.
     *
     * @see java.io.BufferedReader
     * @see java.io.BufferedWriter
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Pedirá la ruta del fichero a escribir</li>
     * <li>Leerá línea a línea el fichero de entrada y escribirá en el de salida
     * con un formato en particular</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerEscribirBuffer() throws RutaInvalida {
        try (BufferedReader lectorBuffer = new BufferedReader(new FileReader(pedirRuta("entrada")));
                BufferedWriter escritorBuffer = new BufferedWriter(new FileWriter(pedirRuta("salida")))) {
            boolean eof = false;
            String lineaLeida; // línea leida
            String[] peliculas; // películas en la línea leida
            String[] texto; // campos en la línea leida
            int i = 0; //indice de campos
            escritorBuffer.append(campos[i]); //escribe el primer campo "Título"
            do {
                lineaLeida = lectorBuffer.readLine(); // lee una línea
                if (lineaLeida != null) {
                    peliculas = lineaLeida.split("\\{"); //divide la línea por '{' (películas)
                    for (int j = 0; j < peliculas.length; j++) {
                        texto = peliculas[j].split("#"); //divide la línea por '#' (campos)
                        for (int k = 0; k < texto.length; k++) {
                            escritorBuffer.append(texto[k]);
                            escritorBuffer.append(" ");
                            if (k < texto.length - 1) {
                                if (i == 3) {
                                    //si estamos en el campo "Duración" añade los minutos al final
                                    escritorBuffer.append("minutos");
                                }
                                escritorBuffer.newLine();
                                escritorBuffer.append(campos[i = (i + 1) % 7]);
                            }
                        }
                        if (j < peliculas.length - 1) {
                            escritorBuffer.newLine();
                            escritorBuffer.newLine();
                            escritorBuffer.append(campos[i = (i + 1) % 7]);
                        }
                    }
                } else {
                    eof = true;
                }
            } while (!eof);
        }  catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        }
    }

    /**
     * Método de lectura de un fichero con buffer y escritura de objetos.
     *
     * Leerá un fichero con BufferedReader y escribirá en otro con
     * ObjectOutputStream
     *
     * @see java.io.BufferedReader
     * @see java.io.ObjectOutputStream
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Pedirá la ruta del fichero a escribir.</li>
     * <li>Leerá línea a línea el fichero de entrada.</li>
     * <li>Creará una instacia de Pelicula que irá rellenando con lo que
     * lea</li>
     * <li>Escribirá el objeto creado en un fichero de objetos</li>
     * <li>Repetirá los pasos 3 a 5 hasta llegar al final del fichero de
     * entrada</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerLineaEscribirObj() throws RutaInvalida {
        try (BufferedReader lectorBuffer = new BufferedReader(new FileReader(pedirRuta("entrada")));
                ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            boolean eof = false;
            String lineaLeida;
            String[] peliculas;
            String[] texto;
            int i = 0; //indice de campos
            Pelicula p = new Pelicula(); //inicializa una película
            while (!eof) {
                lineaLeida = lectorBuffer.readLine(); //lee una línea
                if (lineaLeida != null) {
                    peliculas = lineaLeida.split("\\{"); //divide la línea por '{' (películas)
                    for (int j = 0; j < peliculas.length; j++) {
                        texto = peliculas[j].split("#"); //divide la línea por '#' (campos)
                        for (int k = 0; k < texto.length; k++) {
                            switch (i) { // añade el texto en el campo que toque (atributos del objeto)
                                case 0:
                                    p.setTitulo(p.getTitulo() + texto[k] + " ");
                                    break;
                                case 1:
                                    p.setAno(p.getAno() + texto[k] + " ");
                                    break;
                                case 2:
                                    p.setDirector(p.getDirector() + texto[k] + " ");
                                    break;
                                case 3:
                                    p.setDuracion(p.getDuracion() + texto[k] + " ");
                                    break;
                                case 4:
                                    p.setSinopsis(p.getSinopsis() + texto[k] + " ");
                                    break;
                                case 5:
                                    p.setReparto(p.getReparto() + texto[k] + " ");
                                    break;
                                case 6:
                                    p.setSesion(p.getSesion() + texto[k] + " ");
                                    break;
                            }
                            //si hay más de un elemento en el array "texto" y no es el último elemento del array (pasar al siguiente campo)
                            if (k < texto.length - 1) {
                                i++;
                            }
                        }
                        //si hay más de un elemento en el array "peliculas" y no es el último elemento del array (pasamos a la siguiente película)
                        if (j < peliculas.length - 1) {
                            objectSalida.writeObject(p);
                            //p.mostrarPelicula();
                            p = new Pelicula();
                            i = 0;
                        }
                    }
                } else {
                    eof = true;
                }
            }
            objectSalida.writeObject(p);
            //p.mostrarPelicula();
        } catch (EOFException ex) {
            System.out.println("FIN DE FICHERO");
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        }
    }

    /**
     * Método de lectura de un fichero de objetos y escritura en otro fichero de
     * objetos.
     *
     * Leerá un fichero con ObjectInputStream y escribirá en otro con
     * ObjectOutputStream
     *
     * @see java.io.ObjectInputStream
     * @see java.io.ObjectOutputStream
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Pedirá la ruta del fichero a escribir.</li>
     * <li>Leerá un objeto del fichero de entrada</li>
     * <li>Escribirá el objeto leido en el fichero de salida</li>
     * <li>Repetirá los pasos 3 y 4 hasta llegar al final del fichero de
     * entrada</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerObjEscribirObj() throws RutaInvalida {
        try (ObjectInputStream objectEntrada = new ObjectInputStream(new FileInputStream(pedirRuta("entrada")));
                ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            boolean eof = false;
            while (!eof) {
                objectSalida.writeObject(new Pelicula((Pelicula) objectEntrada.readObject()));
            }
        } catch (EOFException e) {
            System.out.println("fin de fichero");
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase");
        }
    }

    /**
     * Método de lectura de un fichero de objetos y escritura por consola.
     *
     * Leerá un fichero con ObjectInputStream y lo mostrará por consola.
     *
     * @see java.io.ObjectInputStream
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero a leer.</li>
     * <li>Leerá un objeto del fichero de entrada</li>
     * <li>Mostrará por consola el objeto leido</li>
     * <li>Repetirá los pasos 2 y 3 hasta llegar al final del fichero de
     * entrada</li>
     * </ol>
     *
     * @throws RutaInvalida Excepción lanzada cuando no encuentra el
     * fichero de entrada
     */
    public static void leerObjEscribirCons() throws RutaInvalida {
        try (ObjectInputStream objectEntrada = new ObjectInputStream(new FileInputStream(pedirRuta("entrada")))) {
            boolean eof = false;
            Pelicula p = new Pelicula();
            while (!eof) {
                p = (Pelicula) objectEntrada.readObject();
                p.mostrarPelicula();
            }
        } catch (EOFException e) {
            System.out.println("fin de fichero");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase");
        } catch (IOException ex) {
            System.out.println("Error de lectura");
        }
    }

    /**
     * Método de lectura por consola y escritura de objetos en fichero.
     *
     * Pedirá al usuario los atributos del objeto Pelicula y lo escribirá en un
     * fichero de objetos con ObjectOutputStream.
     *
     * @throws streamsandexceptions.RutaInvalida
     * @see java.io.ObjectOutputStream
     *
     * Proceso a seguir:
     * <ol>
     * <li>Pedirá la ruta del fichero de objetos a escribir.</li>
     * <li>Pedirá al usuario que introduzca por consola una película</li>
     * <li>Creará una instancia de Pelicula</li>
     * <li>Escribirá el objeto en el fichero de salida</li>
     * </ol>
     */
    public static void leerConsEscribirObj() throws RutaInvalida {
        try (ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            Pelicula p = new Pelicula(); //crea una nueva película  
            p.pedirPelicula();  //llenamos sus atributos pidiendolos por pantalla
            objectSalida.writeObject(p); //lo guardamos en un fichero
        } catch (IOException ex) {
            System.out.println("Error de escritura");
        }
    }
}
