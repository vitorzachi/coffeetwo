package br.com.camtwo.cafe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.LocalDate;

import com.db4o.query.Predicate;

import br.com.camtwo.cafe.model.Pessoa;
import br.com.camtwo.cafe.model.Sorteado;
import br.com.camtwo.cafe.service.ManutencaoService;
import br.com.camtwo.cafe.service.Sorteador;
import br.com.camtwo.cafe.service.SorteioService;

@ManagedBean
@ViewScoped
public class SorteioController implements Serializable {
	private static final long serialVersionUID = -8472328992207977662L;
	@ManagedProperty("#{manutencaoService}")
	private ManutencaoService manutencaoService;
	@ManagedProperty("#{sorteioService}")
	private SorteioService sorteioService;
	@ManagedProperty("#{sorteador}")
	private Sorteador sorteador;
	
	private Collection<Pessoa> pessoasPresentes;
	private Collection<Pessoa> sorteadas;
	private Collection<Pessoa> todasPessoas;
	private Collection<Sorteado> sorteadosHoje;
	private Sorteado sorteado;
	
	@PostConstruct
	public void novo() {
		pessoasPresentes = manutencaoService.findDisponiveis(LocalDate.now().getDayOfWeek());
		sorteadas = sorteioService.findPessoasSorteadasNaSemana(LocalDate.now());
		sorteadosHoje = sorteioService.find(new Predicate<Sorteado>() {
			private static final long serialVersionUID = 2627155435553009264L;
			@Override
			public boolean match(Sorteado arg0) {
				return LocalDate.fromDateFields(arg0.getDataHoraSorteio()).isEqual(LocalDate.now());
			}
		});
		todasPessoas = manutencaoService.findAll();
	}
	
	public void sorteiaStrict(){
		List<Pessoa> baseSorteio = new ArrayList<Pessoa>(pessoasPresentes);
		baseSorteio.removeAll(sorteadas);
		sorteado=new Sorteado();
		Pessoa pessoa= manutencaoService.find(sorteador.sorteada(baseSorteio).uniquePredicate()).iterator().next();
		sorteado.setPessoa(pessoa);
		sorteioService.inserir(sorteado);
		novo();
	}
	
	public void sorteiaAll(){
		sorteado=new Sorteado();
		Pessoa pessoa= manutencaoService.find(sorteador.sorteada(todasPessoas).uniquePredicate()).iterator().next();
		sorteado.setPessoa(pessoa);
		sorteioService.inserir(sorteado);
		novo();
	}

	public boolean isSorteiaStrict() {
		List<Pessoa> baseSorteio = new ArrayList<Pessoa>(pessoasPresentes);
		baseSorteio.removeAll(sorteadas);
		return baseSorteio.size()>0;
	}
	
	public boolean isPresente(Pessoa pessoa) {
		return pessoasPresentes.contains(pessoa);
	}

	public boolean isSorteada(Pessoa pessoa) {
		return sorteadas.contains(pessoa);
	}

	public void setManutencaoService(ManutencaoService manutencaoService) {
		this.manutencaoService = manutencaoService;
	}

	public void setSorteioService(SorteioService sorteioService) {
		this.sorteioService = sorteioService;
	}

	public void setSorteador(Sorteador sorteador) {
		this.sorteador = sorteador;
	}

	public Collection<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	
	public Sorteado getSorteado() {
		return sorteado;
	}
	public Collection<Sorteado> getSorteadosHoje() {
		return sorteadosHoje;
	}
}
