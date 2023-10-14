package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Classe;

public class ClassesDao implements DAOInterface<Classe, String> {

	static Connection con = DatabaseConnection.getConnection();

	@Override
	public int add(Classe item) throws Exception {
		try {
			String query = "insert into classes(matricule, nom) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, item.getMatricule());
			ps.setString(2, item.getNom());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			if (ex.getMessage().contains("PRIMARY"))
				throw new Exception("La classe avec la matricule " + item.getMatricule() + " existe deja");
			throw ex;
		}
	}

	@Override
	public void delete(String id) throws SQLException {
		String query = "delete from classes where matricule =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, id);
		ps.executeUpdate();
	}

	@Override
	public Classe get(String id) throws SQLException {
		String query = "select * from classes where matricule= ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, id);
		Classe cls = new Classe();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
			check = true;
			cls.setMatricule(rs.getString("matricule"));
			cls.setNom(rs.getString("nom"));
		}

		if (check == true) {
			return cls;
		} else
			throw new SQLException();
	}

	@Override
	public ObservableList<Classe> getAll() throws SQLException {
		String query = "select * from classes";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ObservableList<Classe> ls = FXCollections.observableArrayList();

		while (rs.next()) {
			Classe cls = new Classe();
			cls.setMatricule(rs.getString("matricule"));
			cls.setNom(rs.getString("nom"));
			ls.add(cls);
		}
		return ls;
	}

	@Override
	public void update(Classe cls) throws SQLException {
		String query = "update classes set nom=? where matricule = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, cls.getNom());
		ps.setString(2, cls.getMatricule());
		ps.executeUpdate();
	}

	@Override
	public int count() {
		try {
			String query = "select count(*) from classes";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception ex) {
			return 0;
		}
	}

	public ObservableList<Classe> filter(String matricule, String nom) {
		try {

			String query = "select * from classes where matricule like ? and nom like ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + matricule + "%");
			ps.setString(2, "%" + nom + "%");
			ResultSet rs = ps.executeQuery();
			ObservableList<Classe> ls = FXCollections.observableArrayList();

			while (rs.next()) {
				Classe cls = new Classe();
				cls.setMatricule(rs.getString("matricule"));
				cls.setNom(rs.getString("nom"));
				ls.add(cls);
			}
			return ls;
		} catch (Exception ex) {
			return FXCollections.observableArrayList();
		}
	}

}
