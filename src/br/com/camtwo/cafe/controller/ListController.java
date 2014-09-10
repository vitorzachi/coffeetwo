package br.com.camtwo.cafe.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.camtwo.cafe.model.DiaSemana;

@ManagedBean
@ApplicationScoped
public class ListController implements Serializable{
	private static final long serialVersionUID = -2936480641420049456L;

	
	public List<DiaSemana> getDias() {
		return Arrays.asList(DiaSemana.values());
	}
}
