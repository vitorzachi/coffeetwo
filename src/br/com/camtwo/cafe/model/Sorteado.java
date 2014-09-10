package br.com.camtwo.cafe.model;

import java.util.Date;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.db4o.query.Predicate;

public class Sorteado implements ObjectDbModel<Sorteado> {

	private Date dataHoraSorteio;
	private Pessoa pessoa;

	public Sorteado() {
		dataHoraSorteio = LocalDateTime.now().toDate();
	}

	public Sorteado(LocalDateTime dataHoraSorteio, Pessoa pessoa) {
		super();
		this.dataHoraSorteio = dataHoraSorteio.toDate();
		this.pessoa = pessoa;
	}

	public Date getDataHoraSorteio() {
		return dataHoraSorteio;
	}

	public void setDataHoraSorteio(Date dataHoraSorteio) {
		this.dataHoraSorteio = dataHoraSorteio;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getDataToString(){
		try {
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
			return LocalDateTime.fromDateFields(dataHoraSorteio).toString( fmt );
		} catch (NullPointerException e) {
			return "<vazio>";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataHoraSorteio == null) ? 0 : dataHoraSorteio.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Sorteado other = (Sorteado) obj;
		if (dataHoraSorteio == null) {
			if (other.dataHoraSorteio != null)
				return false;
		} else if (!dataHoraSorteio.equals(other.dataHoraSorteio))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	@Override
	public Predicate<Sorteado> uniquePredicate() {
		return null;
	}

}
