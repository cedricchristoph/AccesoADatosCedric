/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.xml;

import es.iespuertodelacruz.cc.entities.ListaCasas;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author cedric christoph
 */
public class ListaCasasXML implements JavaToXMLString<ListaCasas> {

    @Override
    public String objToStringXML(ListaCasas lista) {
        JAXBContext contexto;
        Marshaller marshaller;
        OutputStream os = null;
        StringWriter sw = new StringWriter();
        try {
            contexto = JAXBContext.newInstance(lista.getClass());
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(lista, sw);
        } catch (JAXBException ex) {
            System.out.println(ex);
        } finally {
            return sw.toString();
        }
    }

    @Override
    public ListaCasas stringXMLToObj(String textoXml) {
        JAXBContext contexto;
        Marshaller marshaller;
        StringReader sr = new StringReader(textoXml);
        ListaCasas lista = null;
        try {
            contexto = JAXBContext.newInstance(ListaCasas.class);
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            lista = (ListaCasas) unmarshaller.unmarshal(sr);
        } catch (JAXBException ex) {
            System.out.println(ex);
        } finally {
            return lista;
        }
    }
        
}
