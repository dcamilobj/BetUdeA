/**
 * 
 */
package co.edu.udea.iw.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.udea.iw.dto.Simulacion;

/**
 * @author user
 *
 */
@XmlRootElement
public class ApuestaJersey {
	
	private Long id;	
	private String evento;
	private Date fechaEvento;
	private Double valorApostado;	
	private Double cuota;
	private String opcionSeleccionada;
	private String estado;
	private Date fechaApuesta;
	private String opcionCorrecta;	
	

	public ApuestaJersey() {
		super();
	}

	/**
	 * @param id
	 * @param evento
	 * @param fechaEvento
	 * @param valorApostado
	 * @param cuota
	 * @param opcionSeleccionada
	 * @param estado
	 * @param fechaApuesta
	 */
	public ApuestaJersey(Long id, String evento, Date fechaEvento, Double valorApostado, Double cuota,
			String opcionSeleccionada, String estado, Date fechaApuesta, String opcionCorrecta) {
		super();
		this.id = id;
		this.evento = evento;
		this.fechaEvento = fechaEvento;
		this.valorApostado = valorApostado;
		this.cuota = cuota;
		this.opcionSeleccionada = opcionSeleccionada;
		this.estado = estado;
		this.fechaApuesta = fechaApuesta;
		this.opcionCorrecta = opcionCorrecta;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the evento
	 */
	public String getEvento() {
		return evento;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEvento(String evento) {
		this.evento = evento;
	}

	/**
	 * @return the fechaEvento
	 */
	public Date getFechaEvento() {
		return fechaEvento;
	}

	/**
	 * @param fechaEvento the fechaEvento to set
	 */
	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	/**
	 * @return the valorApostado
	 */
	public Double getValorApostado() {
		return valorApostado;
	}

	/**
	 * @param valorApostado the valorApostado to set
	 */
	public void setValorApostado(Double valorApostado) {
		this.valorApostado = valorApostado;
	}

	/**
	 * @return the cuota
	 */
	public Double getCuota() {
		return cuota;
	}

	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	/**
	 * @return the opcionSeleccionada
	 */
	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	/**
	 * @param opcionSeleccionada the opcionSeleccionada to set
	 */
	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaApuesta
	 */
	public Date getFechaApuesta() {
		return fechaApuesta;
	}

	/**
	 * @param fechaApuesta the fechaApuesta to set
	 */
	public void setFechaApuesta(Date fechaApuesta) {
		this.fechaApuesta = fechaApuesta;
	}
	
	/**
	 * @return the opcionCorrecta
	 */
	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}

	/**
	 * @param opcionCorrecta the opcionCorrecta to set
	 */
	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}
	
}
