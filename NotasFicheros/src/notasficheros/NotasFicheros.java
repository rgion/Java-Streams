/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notasficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Rafael
 */
public class NotasFicheros {

    public static void main(String[] args) {
        try {
            abriryleerFichero();
        } catch (MiExcepcion ex) {
            // Logger.getLogger(NotasFicheros.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Mensaje de error: " + ex.getMessage());
        }
    }

    public static void abriryleerFichero() throws MiExcepcion {
        FileReader fr = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            File archivo = new File("notas.txt");
            /*con File guardamos un objeto de tipo file, que principalmente 
            guarda
            el path del fichero, y toda la información relativa al fichero
            para posteriormente trabajar con el. Destacar que podemos precindir
            de el en la mayoría de ocasiones. Es interesante revisar la documen
            tación para ver lo que nos aporta: 
            https://www.discoduroderoer.es/clase-file-y-sus-metodos*/
            fr = new FileReader(archivo);
            //fr = new FileReader("D:\\Users\\Rafael\\Documents\\notas.txt");
            /* por las caracteristicas de java, en este momento es cuando
            realiza la apertura del fichero en modo lectura. La clase filereader 
            incorpora métodos para realizar lectura del fichero caracter a 
            caracter. Para este programa estos métodos no nos interesan.
            Afortunadamente tenemos bufferedreader, que nos permite crear un buffer
            de lectura, que además de ser más eficiente que lectura caracter a 
            caracter, incorpora un método redaline, que nos permite leer toda
            una línea. Además destacar que filereader y buffereader deben 
            incluirse en un bloque try, ya que estos pueden provocar excepciones
            que deben ser controladas.
             */
            BufferedReader br = new BufferedReader(fr, 10);
            leerFichero(br);
        } catch (Exception e) {
            //e.printStackTrace();//imprime pila de ejecución
            throw new MiExcepcion("Error:" + e.getMessage(), e.getCause());
        } finally {
            cerrarFichero(fr);
        }
    }

    public static void cerrarFichero(FileReader fr) {
        // En el finally cerramos el fichero, para asegurarnos
        // que se cierra tanto si todo va bien como si salta
        // una excepcion. UN FICHERO ABIERTO SIEMPRE SE CIERRA
        try {
            if (null != fr) {
                fr.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void leerFichero(BufferedReader br) throws IOException {
        // Lectura del fichero
        String linea;
        while ((linea = br.readLine()) != null) {
            /*en este caso hacemos una lectura línea a línea hasta que se acabe
            el fichero. Siempre hay que tener un control sobre la lectura, ya 
            si continuamos leyendo cuando ha acabado el fichero provocará una
            excepción
             */
            String vector[] = linea.split(" ");
            generarBoletin(vector);

        }
    }

    public static void generarBoletin(String vector[])
            throws IOException {
        //el formato es el siguiente Pepe García Hernández 5 7 3 7 10 c-5
        //estructura del documento 
        /*---------------------------------------------
Boletín de notas IES FBMOLL
---------------------------------------------
Alumno: Pepe García Hernández
----------------------------------------
Módulo                            Nota
------------------------------   -------
Lenguaje de marcas         5
Programación                   7
Entornos de desarrollo     3
Base de datos                  7
Sistemas informáticos      10
FOL                                  c-5
------------------------------------------
Nº de módulos aprobados:     5
Nº de módulos suspendidos:  0
Nº de módulos convalidados: 1
-------------------------------------------
         */
 /*con la escritura de ficheros es similar a la lectura. Como principal
        detalle a destacar es que si queremos añadir información al final del 
        fichero, podemos hacerlo incorporando el flag true:
        FileWriter fichero = new FileWriter("D:\\mifichero.txt,true);
        Por tanto, en el momento de crear nuestro filewriter, si no existe el 
        fichero lo crea, y además lo abre en modo escritura. Si ya existe, y 
        tenemos el flag a true, añade información. Si ya existe y no añadimos el 
        flag, machaca el fichero con la información que pudiera tener
         */

        FileWriter fichero = new FileWriter("notas\\"
                + vector[0] + vector[1] + vector[2] + ".txt");
        PrintWriter pw = new PrintWriter(fichero);
        int convalidadas = 0;
        int aprobadas = 0;
        int suspendidas = 0;

        pw = new PrintWriter(fichero);
        pw.println("---------------------------------------------");
        pw.println("Boletín de notas IES FBMOLL");
        pw.println("---------------------------------------------");
        pw.println("Alumno: " + vector[0] + " " + vector[1] + " " + vector[2]);
        pw.println("------------------------------------");
        pw.println("Módulo                        Nota");
        pw.println("--------------------------   -------");
        pw.println("Lenguaje de marcas" + "\t \t" + vector[3]);
        pw.println("Programación" + "\t \t \t" + vector[4]);
        pw.println("Entornos de desarrollo" + "\t \t" + vector[5]);
        pw.println("Base de datos" + "\t \t \t" + vector[6]);
        pw.println("Sistemas informáticos" + "\t \t" + vector[7]);
        pw.println("FOL" + "\t \t \t \t" + vector[8]);
        for (int i = 3; i < vector.length; i++) {
            if (vector[i].equals("c-5")) {
                convalidadas++;
            } else if (Integer.parseInt(vector[i]) <= 5) {
                suspendidas++;
            } else {
                aprobadas++;
            }
        }
        pw.println("------------------------------------------");
        pw.println("Nº de módulos aprobados:\t" + aprobadas);
        pw.println("Nº de módulos suspendidos:\t" + suspendidas);
        pw.println("Nº de módulos convalidados:\t" + convalidadas);
        pw.println("------------------------------------------");
        //como siempre CERRAMOS UN FICHERO
        cerrarFicheroEscritura(pw);
    }

    public static void cerrarFicheroEscritura(PrintWriter pw)
            throws IOException {
        // En el finally cerramos el fichero, para asegurarnos
        // que se cierra tanto si todo va bien como si salta
        // una excepcion.
        try {
            if (null != pw) {
                pw.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
