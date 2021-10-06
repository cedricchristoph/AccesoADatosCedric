/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml;

import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import es.iespuertodelacruz.jc.monedasxml.utils.ManejoFichero;
import es.iespuertodelacruz.jc.monedasxml.xml.HistoricoXML;
import es.iespuertodelacruz.jc.monedasxml.xml.MonedaXML;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class MonedasXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Moneda m = new Moneda(1,"libra","uk");
        
        MonedaXML monedaXML = new MonedaXML();
        
        String strXMLmoneda = monedaXML.objToStringXML(m);
        
        System.out.println(
                strXMLmoneda
        );
        
        Moneda m2 = monedaXML.stringXMLToObj(strXMLmoneda);
        
        System.out.println(m2);
        
        
        Historico h = new Historico();
        h.setMoneda(m2);
        h.setEquivalenteEuro(2.3);
        h.setFecha(new Date());
        h.setIdHistorico(1);
        
        HistoricoXML hXML = new HistoricoXML();
        
        System.out.println(
                hXML.objToStringXML(h)
        );
        
        
        m2.getHistoricos().add(h);
        
        System.out.println(monedaXML.objToStringXML(m2)
        );
     
        
        ManejoFichero mf = new ManejoFichero("/tmp/mixml.txt");
        
        //mf.agregarTexto(mXML.objToStringXML(m2));
        System.out.println("---- lectura ----");
        System.out.println(
                mf.leerTodo()
        );
        
        Moneda m3 = monedaXML.stringXMLToObj(mf.leerTodo());
        System.out.println(m3);
        
    }
    
}
