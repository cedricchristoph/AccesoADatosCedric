/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.entities;

/**
 *
 * @author cedric christoph
 */
public enum TipoCasa {
    
    APARTAMENTO, PISO, CASA, VILLA;
    
    public static final Integer TIPO_APARTAMENTO = 1;
    public static final Integer TIPO_PISO = 2;
    public static final Integer TIPO_CASA = 3;
    public static final Integer TIPO_VILLA = 4;
    
    public static TipoCasa getTipo(Integer id) {
        switch (id) {
            case 1:
                return APARTAMENTO;
            case 2:
                return PISO;
            case 3:
                return CASA;
            case 4:
                return VILLA;
            default:
                return null;
        }
    }
    
}
