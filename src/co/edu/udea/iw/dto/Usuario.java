/**
 * 
 */
package co.edu.udea.iw.dto;

/**
 *Clase implementa todos los atributos de la tabla "usuario" junto a sus getters an setters. 
 *@author Duban Camilo Bedoya Jiménez
 *@version 1.0
 */
public class Usuario {

	/**
	 * Atributos de la clase
	 */
	private String cedula;	
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}
	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombres the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param contrasena the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
