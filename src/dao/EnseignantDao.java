package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dal.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Enseignant;

public class EnseignantDao implements DAOInterface<Enseignant, String> {

	static Connection con = DatabaseConnection.getConnection();

	@Override
	public int add(Enseignant item) throws Exception {
		try {
			String query = "insert into enseignants(matricule, nom, contact) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, item.getMatricule());
			ps.setString(2, item.getNom());
			ps.setString(3, item.getContact());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			if (ex.getMessage().contains("PRIMARY"))
				throw new Exception("L'enseignant avec la matricule " + item.getMatricule() + " existe deja");
			throw ex;
		}
	}

	@Override
	public void delete(String id) throws SQLException {
		String query = "delete from enseignants where matricule =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, id);
		ps.executeUpdate();
	}

	@Override
	public Enseignant get(String id) throws SQLException {
		String query = "select * from enseignants where matricule= ?";
		PreparedStatement ps = con.prepareStatement(query);

		ps.setString(1, id);
		Enseignant ens = new Enseignant();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
			check = true;
			ens.setMatricule(rs.getString("matricule"));
			ens.setNom(rs.getString("nom"));
			ens.setContact(rs.getString("contact"));
		}

		if (check == true) {
			return ens;
		} else
			throw new SQLException();
	}

	@Override
	public ObservableList<Enseignant> getAll() throws SQLException {
		String query = "select * from enseignants";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ObservableList<Enseignant> ls = FXCollections.observableArrayList();

		while (rs.next()) {
			Enseignant ens = new Enseignant();
			ens.setMatricule(rs.getString("matricule"));
			ens.setNom(rs.getString("nom"));
			ens.setContact(rs.getString("contact"));
			ls.add(ens);
		}
		return ls;
	}

	@Override
	public void update(Enseignant ens) throws SQLException {
		String query = "update enseignants set nom=?, " + " contact=? where matricule = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, ens.getNom());
		ps.setString(2, ens.getContact());
		ps.setString(3, ens.getMatricule());
		ps.executeUpdate();
	}

	@Override
	public int count() {
		try {
			String query = "select count(*) from enseignants";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			int n = 0;
			while (rs.next()) {
				n = rs.getInt(1);
			}
			return n;
		} catch (Exception ex) {
			return 0;
		}
	}

	public ObservableList<Enseignant> filter(String matricule, String nom, String contact) {
		try {

			String query = "select * from enseignants where matricule like ? and nom like ? and contact like ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + matricule + "%");
			ps.setString(2, "%" + nom + "%");
			ps.setString(3, "%" + contact + "%");
			ResultSet rs = ps.executeQuery();
			ObservableList<Enseignant> ls = FXCollections.observableArrayList();

			while (rs.next()) {
				Enseignant ens = new Enseignant();
				ens.setMatricule(rs.getString("matricule"));
				ens.setNom(rs.getString("nom"));
				ens.setContact(rs.getString("contact"));
				ls.add(ens);
			}
			return ls;
		} catch (Exception exd) {
			return FXCollections.observableArrayList();
		}
	}

}
