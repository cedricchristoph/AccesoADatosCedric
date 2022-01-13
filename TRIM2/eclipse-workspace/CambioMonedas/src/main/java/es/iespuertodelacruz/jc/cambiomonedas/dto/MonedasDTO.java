package es.iespuertodelacruz.jc.cambiomonedas.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;




@Component
public class MonedasDTO extends RepresentationModel<MonedasDTO>{

    private static final long serialVersionUID = 1L;

    private Integer idmoneda;

    private String nombre;
    
    private String pais;

    private List<HistoricocambioeuroDTO> historicos;


    public List<HistoricocambioeuroDTO> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricocambioeuroDTO> historicos) {
		this.historicos = historicos;
	}

	public MonedasDTO() {


    }	
	
	
	public Monedas toMoneda() {
		Monedas m = new Monedas();
		if(idmoneda != null)
			m.setIdmoneda(idmoneda);
		m.setPais(pais);
		m.setNombre(nombre);
		ArrayList<Historicocambioeuro> lista = new ArrayList<>();

		for(HistoricocambioeuroDTO hdto :historicos) {
			Historicocambioeuro h = hdto.toHistorico();

			h.setFkidmoneda(m);
			lista.add(h);

		}
		m.setHistoricocambioeuroCollection(lista);	
		return m;
		
	}
    
    public MonedasDTO(Monedas moneda) {
    	idmoneda = moneda.getIdmoneda();
    	nombre = moneda.getNombre();
    	pais = moneda.getPais();
    	ArrayList<HistoricocambioeuroDTO> lista = new ArrayList<HistoricocambioeuroDTO>();
    	for( Historicocambioeuro h : moneda.getHistoricocambioeuroCollection()) {
    		lista.add(new HistoricocambioeuroDTO(h));
    	}
    	historicos = lista;
    	 
    }
    
    
    
    public Integer getIdmoneda() {
		return idmoneda;
	}

	public void setIdmoneda(Integer idmoneda) {
		this.idmoneda = idmoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	
}
