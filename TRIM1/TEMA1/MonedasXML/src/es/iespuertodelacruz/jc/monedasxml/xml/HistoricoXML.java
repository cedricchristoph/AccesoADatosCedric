/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.xml;

import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import java.io.OutputStream;
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
    public String objToStringXML(Historico historico) {
        JAXBContext contexto;
        Marshaller marshaller;
        OutputStream os = null;
        StringWriter sw = new StringWriter();
        try {
            contexto = JAXBContext.newInstance(historico.getClass());
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(historico, sw);
        } catch (JAXBException ex) {
            System.out.println(ex);
        } finally {
            return sw.toString();
        }       
    }

    @Override
    public Historico stringXMLToObj(String textoXml) {
        JAXBContext contexto;
        Marshaller marshaller;
        StringReader sr = new StringReader(textoXml);
        Historico historico = null;
        try {
            contexto = JAXBContext.newInstance(Historico.class);
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            historico = (Historico) unmarshaller.unmarshal(sr);
        } catch (JAXBException ex) {
            System.out.println(ex);
        } finally {
            return historico;
        }
    }
    
    
    
}
