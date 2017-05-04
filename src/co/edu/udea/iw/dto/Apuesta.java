/**
 * 
 */
package co.edu.udea.iw.dto;

import java.util.Date;

/**
 * Clase DTO para la tabla "apuesta"
 * @author Andres Ceballos Sanchez - andres.ceballoss@udea.edu.co
 * @version 1.0
 *
 */
public class Apuesta {
	
	/*Atributos de la clase*/
	private Long id;	
	private String evento;
	private Date fechaEvento;
	private Double valorApostado;	
	private Double cuota;
	private String opcionSeleccionada;
	private String estado;
	private Date fechaApuesta;
	private Simulacion periodoSimulacion;
	
	/**
	 * @return the periodoSimulacion
	 */
	public Simulacion getPeriodoSimulacion() {
		return periodoSimulacion;
	}
	/**
	 * @param periodoSimulacion the periodoSimulacion to set
	 */
	public void setPeriodoSimulacion(Simulacion periodoSimulacion) {
		this.periodoSimulacion = periodoSimulacion;
	}
	private String opcionCorrecta;
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
