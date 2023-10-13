package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import dal.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Sceance;

public class SceancesDao implements DAOInterface<Sceance, Integer> {

	static Connection con = DatabaseConnection.getConnection();

	@Override
	public int add(Sceance item) throws Exception {
		try {
			String query = "INSERT INTO `sceances` (`heure_debut`, `heure_fin`, `id_enseignant`, `id_matiere`, `id_classe`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, item.getHeureDebut().toString());
			ps.setString(2, item.getHeureFin().toString());
			ps.setString(3, item.getEnseignant().getMatricule());
			ps.setString(4, item.getMatiere().getId());
			ps.setString(5, item.getClasse().getMatricule());
			
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "delete from sceances where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public Sceance get(Integer id) throws SQLException {
		String query = "select * from sceances where id= ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		Sceance cls = new Sceance();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
			check = true;
			cls = extract(rs);
		}

		if (check == true) {
			return cls;
		} else
			throw new SQLException();
	}

	@Override
	public ObservableList<Sceance> getAll() throws SQLException {
		String query = "select * from sceances";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ObservableList<Sceance> ls = FXCollections.observableArrayList();

		while (rs.next()) {
			ls.add(extract(rs));
		}
		return ls;
	}

	private Sceance extract(ResultSet rs) throws SQLException {
		Sceance cls = new Sceance();
		cls.setId(rs.getObject("id", Integer.class));
		cls.setHeureDebut(rs.getObject("heure_debut", LocalDateTime.class));
		cls.setHeureFin(rs.getObject("heure_fin", LocalDateTime.class));
		cls.setEnseignant(new EnseignantDao().get(rs.getString("id_enseignant")));
		cls.setMatiere(new MatieresDao().get(rs.getString("id_matiere")));
		cls.setClasse(new ClassesDao().get(rs.getString("id_classe")));
		return cls;
	}

	@Override
	public void update(Sceance cls) throws SQLException {
		String query = "update sceances set heure_debut=?, heure_fin=?, id_enseignant=?, id_matiere=?, id_classe=? where id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, cls.getHeureDebut().toString());
		ps.setString(2, cls.getHeureFin().toString());
		ps.setString(3, cls.getEnseignant().getMatricule());
		ps.setString(4, cls.getMatiere().getId());
		ps.setString(5, cls.getClasse().getMatricule());
		ps.setInt(6, cls.getId());
		ps.executeUpdate();
	}
}
