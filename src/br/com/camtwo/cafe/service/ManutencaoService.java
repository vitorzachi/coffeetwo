package br.com.camtwo.cafe.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.camtwo.cafe.model.DiaSemana;
import br.com.camtwo.cafe.model.Pessoa;

import com.db4o.query.Predicate;

@ManagedBean
@ApplicationScoped
public class ManutencaoService implements Serializable{
	private static final long serialVersionUID = -969120258517593709L;
	@ManagedProperty("#{database}")
	private Database database;

	public void inserirOuAlterar(final Pessoa pessoa) {
		if (contains(pessoa.uniquePredicate())) {
			alterar(pessoa);
		} else {
			inserir(pessoa);
		}
	}

	private void alterar(Pessoa pessoa) {
		Pessoa pessoaAlterar = find(pessoa.uniquePredicate()).iterator().next();
		pessoaAlterar.setNome(pessoa.getNome());
		inserir(pessoaAlterar);
	}

	private void inserir(Pessoa pessoa) {
		database.get().store(pessoa);
		database.get().commit();
	}

	public void remover(Pessoa pessoa) {
		if (contains(pessoa.uniquePredicate())) {
			pessoa = find(pessoa.uniquePredicate()).iterator().next();
			database.get().delete(pessoa);
		}
	}
	
	public void removeAll() {
		for (Pessoa pessoa : findAll()) {
			database.get().delete(pessoa);
		}
	}

	public boolean contains(Pessoa pessoa) {
		return !database.get().queryByExample(pessoa).isEmpty();
	}

	public boolean contains(Predicate<Pessoa> predicate) {
		return !database.get().query(predicate).isEmpty();
	}

	public long count() {
		return findAll().size();
	}
	
	public Collection<Pessoa> findAll() {
		return database.get().query(Pessoa.class);
	}

	public long count(Predicate<Pessoa> predicate) {
		return database.get().query(predicate).size();
	}

	public Collection<Pessoa> find(Predicate<Pessoa> predicate) {
		return database.get().query(predicate);
	}
	
	public void setDatabase(Database database) {
		this.database = database;
	}
	
	public Collection<Pessoa> findDisponiveis(int diaSemana){
		final DiaSemana dia;
		try {
			dia = DiaSemana.values()[diaSemana];
		} catch (IndexOutOfBoundsException e) {
			return Collections.<Pessoa>emptyList();
		}
		
		return database.get().query(new Predicate<Pessoa>() {
			private static final long serialVersionUID = -2662569298708628640L;

			@Override
			public boolean match(Pessoa arg0) {
				return arg0.getDias().contains(dia);
			}
		});
	}
}
