/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostasficheros2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import notasficheros.MiExcepcion;

/**
 *
 * @author Rafael
 */
public class NotasFicheros2 {


    public static void main(String[] args) {
        try {
            abrirFicheroLectura();
        } catch (MiExcepcion ex) {
           // Logger.getLogger(NotasFicheros.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Mensaje de error: "+ ex.getMessage());
        }
    }

    public static void abrirFicheroLectura() throws MiExcepcion{
        FileReader fr=null;
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
            BufferedReader br = new BufferedReader(fr,10);
            leerFichero(br);
        } catch (Exception e) {
            e.printStackTrace();//imprime pila de ejecución
            throw new MiExcepcion("Error:"+e.getMessage(),e.getCause());
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

    public static void leerFichero(BufferedReader br) throws IOException, ClassNotFoundException {
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
            throws IOException, ClassNotFoundException{
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
        ObjectOutputStream fsal=new ObjectOutputStream(
                new FileOutputStream("notas\\"
                + vector[0] + vector[1] + vector[2] + ".txt"));        
        NotasAlumnos alumno1 = new NotasAlumnos(vector[0], vector[1],vector[2], 
                vector[3],vector[4],vector[5],vector[6],vector[7],vector[8]);
        fsal.writeObject(alumno1);
        fsal.close();
        //leemos el objeto que acabamos de escribir y lo mostramos por pantalla
        ObjectInputStream fent=new ObjectInputStream(
                new FileInputStream("notas\\"
                + vector[0] + vector[1] + vector[2] + ".txt"));        
        NotasAlumnos alumno = new NotasAlumnos();
        alumno=(NotasAlumnos) fent.readObject();
        System.out.println("---------------------------------------------");
        System.out.println("Boletín de notas IES FBMOLL");
        System.out.println("---------------------------------------------");
        System.out.println("Alumno: " + alumno.getNombre() + " " + 
                alumno.getApellido1() + " " + alumno.getApellido2());
        System.out.println("------------------------------------");
        System.out.println("Módulo                        Nota");
        System.out.println("--------------------------   -------");
        System.out.println("Lenguaje de marcas" + "\t \t" + alumno.getLm());
        System.out.println("Programación" + "\t \t \t" + alumno.getProg());
        System.out.println("Entornos de desarrollo" + "\t \t" + alumno.getEd());
        System.out.println("Base de datos" + "\t \t \t" + alumno.getBbdd());
        System.out.println("Sistemas informáticos" + "\t \t" + alumno.getSi());
        System.out.println("FOL" + "\t \t \t \t" + alumno.getFol());        
        fent.close();                
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
    

