package br.com.camtwo.cafe.model;

import com.db4o.query.Predicate;

public interface ObjectDbModel<T> {

	Predicate<T> uniquePredicate();
	
}
