package br.com.camtwo.cafe.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.camtwo.cafe.model.Pessoa;

@ManagedBean
@ApplicationScoped
public class Sorteador implements Serializable{
	private static final long serialVersionUID = -7216724010819995260L;

	
	public Pessoa sorteada(Collection<Pessoa> baseSorteio){
		List<Pessoa> pessoas = new ArrayList<Pessoa>(baseSorteio);
		pessoas.addAll(baseSorteio);
		pessoas.addAll(baseSorteio);
		
		Collections.shuffle(pessoas);
		int index = new Random().nextInt(pessoas.size());
		
		return pessoas.get(index);
	}
	
}
