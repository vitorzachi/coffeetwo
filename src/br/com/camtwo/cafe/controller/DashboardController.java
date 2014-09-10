package br.com.camtwo.cafe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.joda.time.LocalDate;

import com.db4o.query.Predicate;

import br.com.camtwo.cafe.model.Sorteado;
import br.com.camtwo.cafe.service.SorteioService;

@ManagedBean
@RequestScoped
public class DashboardController {
	@ManagedProperty("#{sorteioService}")
	private SorteioService sorteioService;

	private List<Sorteado> ultimosSorteados;
	private Sorteado daVez;

	@PostConstruct
	private void load() {
		ultimosSorteados = new ArrayList<Sorteado>(
				sorteioService.find(new Predicate<Sorteado>() {
					private static final long serialVersionUID = -7946137981566852782L;

					@Override
					public boolean match(Sorteado arg0) {
						return LocalDate.fromDateFields(
								arg0.getDataHoraSorteio()).isAfter(
								LocalDate.now().minusDays(7));
					}
				}));

		Collections.sort(ultimosSorteados, new Comparator<Sorteado>() {
			@Override
			public int compare(Sorteado o1, Sorteado o2) {
				return o2.getDataHoraSorteio().compareTo(
						o1.getDataHoraSorteio());
			}
		});
		
		if (ultimosSorteados.size() > 0
				&& LocalDate.fromDateFields(
						ultimosSorteados.get(0).getDataHoraSorteio()).isEqual(
						LocalDate.now())) {
			daVez = ultimosSorteados.get(0);
		}
	}

	public Collection<Sorteado> getUltimosSorteados() {
		return ultimosSorteados;
	}

	public Sorteado getDaVez() {
		return daVez;
	}

	public void setSorteioService(SorteioService sorteioService) {
		this.sorteioService = sorteioService;
	}
}
