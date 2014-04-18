package com.cartodb.dao;

import java.io.Serializable;

import com.cartodb.CartoDBException;

public interface CartoDBGenericDAOIF<Entity, PK extends Serializable> {
	
	void save(Entity t) throws IllegalArgumentException, IllegalAccessException;

	void update(Entity t);
	

	Entity find(PK id) throws CartoDBException;
	
	String findAll() throws CartoDBException;
	
	void delete(PK cartodb_id) throws CartoDBException;
}
