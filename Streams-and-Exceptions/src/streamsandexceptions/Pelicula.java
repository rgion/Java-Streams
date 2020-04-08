/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamsandexceptions;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Esta clase define como son los objetos "película" que serán leidos y escritos
 * en ficheros
 *
 * @author rgion
 */
public class Pelicula implements Serializable {

    // Atributos
    private String titulo = "";
    private String ano = ""; //año
    private String director = "";
    private String duracion = "";
    private String sinopsis = "";
    private String reparto = "";
    private String sesion = "";

    //Constructores
    /**
     * Constructor vacío
     */
    public Pelicula() {
    }

    /**
     * Constructor con parámetros
     *
     * @param titulo Título de la película
     * @param ano Año de salida
     * @param director Director de la película
     * @param duracion Duración en minutos
     * @param sinopsis Sinopsis
     * @param reparto Actores de la película
     * @param sesion Sesión en la que se emite la película (formato HH:MM)
     */
    public Pelicula(String titulo, String ano, String director, String duracion, String sinopsis, String reparto, String sesion) {
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.sesion = sesion;
    }

    /**
     * Constructor copia
     *
     * @param p Película a copiar
     */
    public Pelicula(Pelicula p) {
        this.titulo = p.titulo;
        this.ano = p.ano;
        this.director = p.director;
        this.duracion = p.duracion;
        this.sinopsis = p.sinopsis;
        this.reparto = p.reparto;
        this.sesion = p.sesion;
    }

    //Getters y Setters
    /**
     * Getter del título
     *
     * @return Título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter del título
     *
     * @param titulo Título de la película
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter del Año
     *
     * @return Año
     */
    public String getAno() {
        return ano;
    }

    /**
     * Setter del Año
     *
     * @param ano Año
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * Getter del Director
     *
     * @return Director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Setter del Director
     *
     * @param director Director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Getter de la Duración
     *
     * @return Duración en minutos
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Setter de la Duración
     *
     * @param duracion Duración
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Getter de la Sinopsis
     *
     * @return Sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Setter de la Sipnopsis
     *
     * @param sinopsis Sinopsis
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * Getter del Reparto
     *
     * @return Reparto de actores
     */
    public String getReparto() {
        return reparto;
    }

    /**
     * Setter del reparto
     *
     * @param reparto Reparto de actores
     */
    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    /**
     * Getter de la Sesión
     *
     * @return Sesión (formato HH:MM)
     */
    public String getSesion() {
        return sesion;
    }

    /**
     * Setter de la Sesión
     *
     * @param sesion Sesión (formato HH:MM)
     */
    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    // Métodos
    /**
     * Muestra por consola los atributos de una instancia de "Pelicula"
     * <ul>
     * <li>Título</li>
     * <li>Año</li>
     * <li>Duración</li>
     * <li>Sinopsis</li>
     * <li>Reparto</li>
     * <li>Sesión</li>
     * </ul>
     */
    public void mostrarPelicula() {
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Año: " + this.getAno());
        System.out.println("Duración: " + this.getDuracion());
        System.out.println("Sinopsis: " + this.getSinopsis());
        System.out.println("Reparto: " + this.getReparto());
        System.out.println("Sesión: " + this.getSesion());
    }

    /**
     * Pide por consola los atributos para una instancia de "Pelicula"
     * <ul>
     * <li>Título</li>
     * <li>Año</li>
     * <li>Duración</li>
     * <li>Sinopsis</li>
     * <li>Reparto</li>
     * <li>Sesión</li>
     * </ul>
     */
    public void pedirPelicula() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce el título: ");
        this.setTitulo(leer.nextLine());
        System.out.println("Introduce el año: ");
        this.setAno(leer.nextLine());
        System.out.println("Introduce la duración: ");
        this.setDuracion(leer.nextLine());
        System.out.println("Introduce la sinopsis: ");
        this.setSinopsis(leer.nextLine());
        System.out.println("Introduce el reparto: ");
        this.setReparto(leer.nextLine());
        System.out.println("Introduce la sesion: ");
        this.setSesion(leer.nextLine());
    }
}
