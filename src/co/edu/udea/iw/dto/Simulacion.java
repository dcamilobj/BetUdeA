/**
 * 
 */
package co.edu.udea.iw.dto;

import java.util.Date;

/**
 *Clase implementa todos los atributos de la tabla "periodo_simulacion" con sus getters y setters. 
 * @author Sergio
 * @version 1.0
 */
public class Simulacion {
	/**
	 * Atributos de la clase
	 */
	private Long id;
	private String usuario_id;
	private Long saldo;
	private Date fecha_inicio;
	private Date fecha_fin;
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
	 * @return the usuario_id
	 */
	public String getUsuario_id() {
		return usuario_id;
	}
	/**
	 * @param usuario_id the usuario_id to set
	 */
	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}
	/**
	 * @return the saldo
	 */
	public Long getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the fecha_inicio
	 */
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	/**
	 * @param fecha_inicio the fecha_inicio to set
	 */
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	/**
	 * @return the fecha_fin
	 */
	public Date getFecha_fin() {
		return fecha_fin;
	}
	/**
	 * @param fecha_fin the fecha_fin to set
	 */
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	
}
