/**
 * 
 */
package co.edu.udea.iw.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase implementa todos los atributos de la tabla "periodo_simulacion" con sus getters y setters. 
 * @author Sergio Llanos Garcia
 * @version 1.0
 */
@XmlRootElement
public class Simulacion {
	/**
	 * Atributos de la clase
	 */
	private Long id;
	private Usuario usuario;
	private Double saldo;
	private Date fechaInicio;
	private Date fechaFin;
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
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
