package com.bolsadeideas.springboot.form.app.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.thymeleaf.expression.Arrays;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor rolesEditor;
	
	@Autowired RoleService roleService;
	
	@InitBinder//2 opcion de validacion de manera implicita o mas trasparente
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"fechaNacimiento", new CustomDateEditor(dateFormat, true));
		
		//Aplicar la clase con el metodo NombreMayuscula
		binder.registerCustomEditor(String.class,"nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class,"pais", paisEditor);
		binder.registerCustomEditor(Role.class,"roles", rolesEditor);
	}
	
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return  paisService.listar();
				
				
				/*java.util.Arrays.asList( 
				new Pais(1,"CO","Colombia"),
				new Pais(2,"MXC","Mexico"),
				new Pais(3,"VZ","Venezuela"),
				new Pais(4,"ES","España"),
				new Pais(5,"PU","Peru"),
				new Pais(6,"Br","Brasil"),
				new Pais(7,"CA","Canada"));
			*/
	} 
	
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
		
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap(){
		Map<String, String> roles = new HashMap<String, String>();
		
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;
	} 
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}
	
	
	
	
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return  java.util.Arrays.asList( "Colombia","Mexico","Peru","Venezuela");
	} 
	
	//Metodo listar Select con Map. lo retornado se espone en formulario con un for each
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map<String, String> paises = new HashMap<String, String>();
		
		paises.put("ES", "Español");
		paises.put("MXC", "Mexico");
		paises.put("EEUU", "Estados Unidos");
		paises.put("AR", "Ergentina");
		
		return paises;
	} 
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Carlos");
		usuario.setApellido("vargas");
		usuario.setIdentificador("123.456.478-K");
		usuario.setHabilitar(true);
		model.addAttribute("titulo", "Formulario Usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	
	//Para capturar datos del formulario se indica con @RequestParam(name="username")
	
	@PostMapping("/form")
	public String procesarFomulario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		//	@RequestParam String username,
		//  @RequestParam(name = "password") String password,
		//	@RequestParam String email) {
		//  Clase pojo o Entity
		
		//  Usuario usuario = new Usuario();
		//usuario.setUsername(username);
		//usuario.setPassword(password);
		//usuario.setEmail(email);
		//validador.validate(usuario, result); //1 se valida de forma explicita usando el metodo validate
		model.addAttribute("titulo", "Resultado del formulario" );
		
		//Condicion If para detectar los errores que no cumplen anotaciones en el modelo
		if (result.hasErrors()) {	
			// Siguiente forma de encontrar los errores  por medio de las validaciones asignadas en modelo.
			/*Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores); */
			
			return "form";
		}
		//model.addAttribute("username", username );
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}
}
