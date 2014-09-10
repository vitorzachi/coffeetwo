package br.com.camtwo.cafe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.db4o.query.Predicate;

public class Pessoa implements ObjectDbModel<Pessoa> {

	private String email;
	private String nome;
	private Set<DiaSemana> dias = new HashSet<DiaSemana>(7);

	public String getEmail() {
		return email;
	}

	public String getEmailNullSafe() {
		return email == null ? "" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addDia(DiaSemana diaSemana) {
		dias.add(diaSemana);
	}

	public void removeDia(DiaSemana diaSemana) {
		dias.remove(diaSemana);
	}

	public Set<DiaSemana> getDias() {
		return dias;
	}

	public String getDiasToString() {
		List<DiaSemana> dias = new ArrayList<DiaSemana>(this.dias);
		Collections.sort(dias);
		return Arrays.toString(dias.toArray());
	}

	@Override
	public String toString() {
		return String.format("[%s %s ]", nome, email);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public Predicate<Pessoa> uniquePredicate() {
		return new Predicate<Pessoa>() {
			private static final long serialVersionUID = 7780372334125709818L;

			@Override
			public boolean match(Pessoa arg0) {
				return email.equalsIgnoreCase(arg0.email);
			}
		};
	}

	public void addDias(DiaSemana[] dias) {
		for (DiaSemana diaSemana : dias) {
			addDia(diaSemana);
		}
	}

}
