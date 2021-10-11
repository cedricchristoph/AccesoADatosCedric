/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vistacontrolador;

/**
 *
 * @author cedric christoph
 * @param <T> Tipo de objeto
 */
public interface JavaToXMLString<T> {

    String objToStringXML( T obj);
    
    T stringXMLToObj(String texto);
}
