/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notasficheros;

/**
 *
 * @author Rafael
 */
public class MiExcepcion extends Exception {

    public MiExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
