package com.ecommerce.dao.contract;

import java.util.Map;

public interface Dao<T> {

	public T get(String id);

	public Map<String, T> getAll();

	public void save(T t);

	public void delete(T t);
}
