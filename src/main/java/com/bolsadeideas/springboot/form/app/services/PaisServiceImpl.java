package com.bolsadeideas.springboot.form.app.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;
	
	
	public PaisServiceImpl() {
		this.lista = java.util.Arrays.asList( 
				new Pais(1,"CO","Colombia"),
				new Pais(2,"MXC","Mexico"),
				new Pais(3,"VZ","Venezuela"),
				new Pais(4,"ES","España"),
				new Pais(5,"PU","Peru"),
				new Pais(6,"Br","Brasil"),
				new Pais(7,"CA","Canada"));
	}

	@Override
	public List<Pais> listar() {
		
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for (Pais pais : this.lista) {
			if (id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		
		return resultado;
	}

	
	
}
