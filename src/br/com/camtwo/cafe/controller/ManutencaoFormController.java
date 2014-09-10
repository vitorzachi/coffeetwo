package br.com.camtwo.cafe.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.camtwo.cafe.model.DiaSemana;
import br.com.camtwo.cafe.model.Pessoa;
import br.com.camtwo.cafe.service.ManutencaoService;

@ManagedBean
public class ManutencaoFormController {
	@ManagedProperty("#{manutencaoService}")
	private ManutencaoService service;
	@ManagedProperty("#{param.id}")
	private String id;
	private Pessoa pessoa;
	private DiaSemana[] dias;

	public void salvar() {
		pessoa.addDias(dias);
		service.inserirOuAlterar(pessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("teste", "testt"));
	}

	public void excluir() {
		service.remover(pessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("teste", "testt"));
		novo();
	}

	public String editar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(id);
		if (service.contains(pessoa)) {
			this.pessoa = service.find(pessoa.uniquePredicate()).iterator()
					.next();
			this.dias = this.pessoa.getDias().toArray(this.dias);
		}

		return "manutencao";
	}

	@PostConstruct
	public void novo() {
		pessoa = new Pessoa();
		dias = new DiaSemana[] {};
		if (id != null) {
			editar();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setDias(DiaSemana[] dias) {
		this.dias = dias;
	}

	public DiaSemana[] getDias() {
		return dias;
	}

	public void setService(ManutencaoService service) {
		this.service = service;
	}

	public void setId(String id) {
		this.id = id;
	}
}
