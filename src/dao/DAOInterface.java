package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T,TID> {
	
	public int add(T item) throws SQLException;

	public void delete(TID id) throws SQLException;

	public T get(TID id) throws SQLException;

	public List<T> getAll() throws SQLException;

	public void update(T emp) throws SQLException;
	
}
