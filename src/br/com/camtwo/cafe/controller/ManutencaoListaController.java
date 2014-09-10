package br.com.camtwo.cafe.controller;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.camtwo.cafe.model.Pessoa;
import br.com.camtwo.cafe.service.ManutencaoService;

@ManagedBean
public class ManutencaoListaController {
	@ManagedProperty("#{manutencaoService}")
	private ManutencaoService service;

	public Collection<Pessoa> getPessoas() {
		return service.findAll();
	}
	
	public void setService(ManutencaoService service) {
		this.service = service;
	}
}
