package dao;

import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface DAOInterface<T,TID> {
	
	public int add(T item) throws SQLException;

	public void delete(TID id) throws SQLException;

	public T get(TID id) throws SQLException;

	public ObservableList<T> getAll() throws SQLException;

	public void update(T emp) throws SQLException;
	
}
