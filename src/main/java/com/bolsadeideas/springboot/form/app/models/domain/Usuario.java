package com.bolsadeideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;
import com.bolsadeideas.springboot.form.app.validation.Requerido;

public class Usuario {
	
	// Anotacion Pattern para implementar exprecion regular, que este compuestio ejemplo 48.654.541-K
	//@Pattern(regexp ="[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}" )
	@IdentificadorRegex
	private String identificador;
	
	private Integer id;
	
	//@NotEmpty
	private String nombre; 
	//@Requerido
	private String apellido; 

	@NotBlank
	@Size(min = 3, max = 8)
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;
	
	
	@NotNull
	@Past //para indicar que solo se puede escoger una fecha desde hoy hacia atras
	//@Future// Indicacion a que solo se puede escoger una fecha de mañana en adelante
	//@DateTimeFormat(pattern = "yyyy-MM-dd")// indicacion para el formato de fecha recomendado
	private Date fechaNacimiento;
	
	
	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;
	
	@NotNull
	private Pais pais;
	
	@NotEmpty
	private List<Role> roles;
	
	private boolean habilitar;
	
	private String genero;
	
	private String valorSecreto;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public boolean isHabilitar() {
		return habilitar;
	}
	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getValorSecreto() {
		return valorSecreto;
	}
	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
	
		return this.id.toString();
	}
	
	
	
	
}
