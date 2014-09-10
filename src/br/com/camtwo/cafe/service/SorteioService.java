package br.com.camtwo.cafe.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.com.camtwo.cafe.model.Pessoa;
import br.com.camtwo.cafe.model.Sorteado;

import com.db4o.query.Predicate;

@ManagedBean
@ApplicationScoped
public class SorteioService implements Serializable{
	private static final long serialVersionUID = -5364946816705142504L;
	@ManagedProperty("#{database}")
	private Database database;

	public void inserir(final Sorteado sorteado) {
		sorteado.setDataHoraSorteio(LocalDateTime.now().toDate());
		database.get().store(sorteado);
		database.get().commit();
	}

	public void removeAll() {
		for (Sorteado sorteado : findAll()) {
			database.get().delete(sorteado);
		}
	}

	public boolean contains(Sorteado sorteado) {
		return !database.get().queryByExample(sorteado).isEmpty();
	}

	public boolean contains(Predicate<Sorteado> predicate) {
		return !database.get().query(predicate).isEmpty();
	}

	public long count() {
		return findAll().size();
	}

	public Collection<Sorteado> findAll() {
		return database.get().query(Sorteado.class);
	}

	public long count(Predicate<Sorteado> predicate) {
		return database.get().query(predicate).size();
	}

	public Collection<Sorteado> find(Predicate<Sorteado> predicate) {
		return database.get().query(predicate);
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Collection<Pessoa> findPessoasSorteadasNaSemana(final LocalDate date) {
		 List<Sorteado> query = database.get().query(new Predicate<Sorteado>() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean match(Sorteado arg0) {
				boolean isInicio = LocalDate.fromDateFields(arg0.getDataHoraSorteio()).withDayOfWeek(1).isEqual(date);
				boolean isFim = LocalDate.fromDateFields(arg0.getDataHoraSorteio()).withDayOfWeek(7).isEqual(date);
				boolean isEntre = date.isAfter(LocalDate.fromDateFields(arg0.getDataHoraSorteio()).withDayOfWeek(1)) 
						&& date.isBefore(LocalDate.fromDateFields(arg0.getDataHoraSorteio()).withDayOfWeek(7)) ;
				
				return isInicio||isFim||isEntre;
			}
		});
		 
		 Set<Pessoa> pessoas=new HashSet<Pessoa>();
		 for (Sorteado sorteado : query) {
			pessoas.add(sorteado.getPessoa());
		}
		 return pessoas;
	}
	
}
