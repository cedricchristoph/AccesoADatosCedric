/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.xml;

import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author carlos
 */
public class MonedaXML implements JavaToXMLString<Moneda>{

    @Override
    public String objToStringXML(Moneda m) {
        String textoXML = null;

        return textoXML;
    }

    @Override
    public Moneda stringXMLToObj(String textoXML) {
        Moneda m = null;

        
        return m;
    }
    
}
