package application.miniprojetmp2l1.dao;

import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface DAOInterface<T,TID> {
	
	int add(T item) throws Exception;

	void delete(TID id) throws SQLException;

	T get(TID id) throws SQLException;

	ObservableList<T> getAll() throws SQLException;

	void update(T emp) throws SQLException;

	int count();
	
}
