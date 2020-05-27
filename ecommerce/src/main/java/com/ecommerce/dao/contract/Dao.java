package com.ecommerce.dao.contract;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	public Optional<T> get(String id);

	public List<T> getAll();

	public boolean save(T t);

	public boolean delete(T t);
}
