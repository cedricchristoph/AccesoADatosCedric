/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.xml;

import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author carlos
 */
public class HistoricoXML implements JavaToXMLString<Historico>{

    @Override
    public String objToStringXML(Historico h) {
        String textoXML = null;

        return textoXML;        
    }

    @Override
    public Historico stringXMLToObj(String textoXML) {
        Historico h = null;

        return h;
    }
    
    
    
}
