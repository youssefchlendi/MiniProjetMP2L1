package application.miniprojetmp2l1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.miniprojetmp2l1.dal.DatabaseConnection;
import application.miniprojetmp2l1.models.Matiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MatieresDao implements DAOInterface<Matiere, String> {

	static Connection con = DatabaseConnection.getConnection();

	@Override
	public int add(Matiere item) throws Exception {
		try {
			String query = "insert into matieres(id, nom) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, item.getId());
			ps.setString(2, item.getNom());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			if (ex.getMessage().contains("PRIMARY"))
				throw new Exception("La matiere avec la id " + item.getId() + " existe deja");
			throw ex;
		}
	}

	@Override
	public void delete(String id) throws SQLException {
		String query = "delete from matieres where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, id);
		ps.executeUpdate();
	}

	@Override
	public Matiere get(String id) throws SQLException {
		String query = "select * from matieres where id= ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, id);
		Matiere cls = new Matiere();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
			check = true;
			cls.setId(rs.getString("id"));
			cls.setNom(rs.getString("nom"));
		}

		if (check == true) {
			return cls;
		} else
			throw new SQLException();
	}

	@Override
	public ObservableList<Matiere> getAll() throws SQLException {
		String query = "select * from matieres";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ObservableList<Matiere> ls = FXCollections.observableArrayList();

		while (rs.next()) {
			Matiere cls = new Matiere();
			cls.setId(rs.getString("id"));
			cls.setNom(rs.getString("nom"));
			ls.add(cls);
		}
		return ls;
	}

	@Override
	public void update(Matiere cls) throws SQLException {
		String query = "update matieres set nom=? where id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, cls.getNom());
		ps.setString(2, cls.getId());
		ps.executeUpdate();
	}

	@Override
	public int count() {
		try {
			String query = "select count(*) from matieres";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception ex) {
			return 0;
		}
	}

	public ObservableList<Matiere> filter(String id, String nom) {
		try {

			String query = "select * from matieres where id like ? and nom like ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + nom + "%");
			ResultSet rs = ps.executeQuery();
			ObservableList<Matiere> ls = FXCollections.observableArrayList();

			while (rs.next()) {
				Matiere cls = new Matiere();
				cls.setId(rs.getString("id"));
				cls.setNom(rs.getString("nom"));
				ls.add(cls);
			}
			return ls;
		} catch (Exception ex) {
			return FXCollections.observableArrayList();
		}
	}

}
