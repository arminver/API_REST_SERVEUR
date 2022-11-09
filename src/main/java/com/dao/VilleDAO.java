package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAO implements IVilleDAO {

	private static final String BDD_URL = "jdbc:mysql://localhost:3306/twic";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";
	
	@Override
	public List<Ville> findVilles(String codePostal) {
		
		ArrayList<Ville> listeVilles = new ArrayList<>();

		Connection cn = null;
		Statement st = null;
		ResultSet rs;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(BDD_URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			rs = st.executeQuery(villeSelection(codePostal));

			while (rs.next()) {
				Ville ville = new Ville();
				ville.setCodeCommune(rs.getString("Code_commune_INSEE"));
				ville.setNomCommune(rs.getString("Nom_commune"));
				ville.setCodePostal(rs.getString("Code_postal"));
				ville.setLibelleAcheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne5(rs.getString("Ligne_5"));
				listeVilles.add(ville);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				assert cn != null;
				cn.close();
				assert st != null;
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listeVilles;
	}

	private String villeSelection(String codePostal) {
		String sql;
		if (codePostal != null) {
			sql = "SELECT * FROM ville_france WHERE Code_postal = " + codePostal;
		} else {
			sql = "SELECT * FROM ville_france";
		}
		return sql;
	}

}
