/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostasficheros2;

import java.io.Serializable;

/**
 *
 * @author Rafael
 */
public class NotasAlumnos implements Serializable{
/*Pepe García Hernández 5 7 3 7 10 c-5   
Lenguaje de marcas         5
Programación                   7
Entornos de desarrollo     3
Base de datos                  7
Sistemas informáticos      10
FOL   */ 
    private String nombre;
    private String apellido1;
    private String apellido2;    
    private String lm;
    private String prog;
    private String ed;
    private String bbdd;
    private String si;
    private String fol;

    public NotasAlumnos(String nombre, String apellido1, String apellido2, String lm, String prog, String ed, String bbdd, String si, String fol) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.lm = lm;
        this.prog = prog;
        this.ed = ed;
        this.bbdd = bbdd;
        this.si = si;
        this.fol = fol;
    }

    public NotasAlumnos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getBbdd() {
        return bbdd;
    }

    public void setBbdd(String bbdd) {
        this.bbdd = bbdd;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getFol() {
        return fol;
    }

    public void setFol(String fol) {
        this.fol = fol;
    }
    
    
}
