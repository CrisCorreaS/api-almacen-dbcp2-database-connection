package org.feliz.almacen.api.dao;

import java.util.List;

public interface IGenericDAO<T,ID> extends IAbstractGenericDAO {
	
	public long save(T entity);
	public boolean update(T instance);
	public boolean delete(T instance);
	public List<T> findByExample(T instance);
	public List<T> findById(String id);
	public List<T> findAll();

}
