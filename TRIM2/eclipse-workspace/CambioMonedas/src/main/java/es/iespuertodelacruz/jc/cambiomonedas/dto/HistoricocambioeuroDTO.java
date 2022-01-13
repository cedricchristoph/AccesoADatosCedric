package es.iespuertodelacruz.jc.cambiomonedas.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;

@Component
public class HistoricocambioeuroDTO extends RepresentationModel<HistoricocambioeuroDTO> {


    private static final long serialVersionUID = 1L;

    private Integer idhistoricocambioeuro;

    private Date fecha;

    private BigDecimal equivalenteeuro;
    
    

	private Integer idmoneda;

    public HistoricocambioeuroDTO() {
    }    
    
    

    public Historicocambioeuro toHistorico() {
    	
    	Historicocambioeuro h = new Historicocambioeuro();
    	if( idhistoricocambioeuro != null)
    		h.setIdhistoricocambioeuro(idhistoricocambioeuro);
    	h.setEquivalenteeuro(equivalenteeuro);
    	h.setFecha(fecha);
    	h.setFkidmoneda(null);
    	return h;
    }
    
    public HistoricocambioeuroDTO(Historicocambioeuro hce) {
		super();
		this.idhistoricocambioeuro = hce.getIdhistoricocambioeuro();
		this.fecha = hce.getFecha();
		this.equivalenteeuro = hce.getEquivalenteeuro();
		//this.idmoneda = hce.getFkidmoneda().getIdmoneda();
	}



	public Integer getIdhistoricocambioeuro() {
		return idhistoricocambioeuro;
	}

	public void setIdhistoricocambioeuro(Integer idhistoricocambioeuro) {
		this.idhistoricocambioeuro = idhistoricocambioeuro;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getEquivalenteeuro() {
		return equivalenteeuro;
	}

	public void setEquivalenteeuro(BigDecimal equivalenteeuro) {
		this.equivalenteeuro = equivalenteeuro;
	}

	@JsonIgnore
	public Integer getFkidmoneda() {
		return idmoneda;
	}

	public void setFkidmoneda(Integer fkidmoneda) {
		this.idmoneda = fkidmoneda;
	}




  

	
	
}
