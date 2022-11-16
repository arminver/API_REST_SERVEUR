package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dto.Coordonnee;
import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDaoImpl implements IVilleDao {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String BDD_URL = "jdbc:mysql://localhost:3306/twic";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "root";

	// Queries
	private static final String SQL_FIND_ALL_VILLES = "SELECT * FROM ville_france";
	private static final String SQL_FIND_VILLES_BY_CODE_POSTAL = "SELECT * FROM ville_france WHERE Code_postal = ";

	private static final String SUCCESS = "success";

	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;

	String answer = "fail";
	
	@Override
	public List<Ville> findVilles(String codePostal) {
		
		ArrayList<Ville> listeVilles = new ArrayList<>();

		try {
			Class.forName(JDBC_DRIVER);
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
				ville.setCoordonnee(new Coordonnee(rs.getString("Latitude"), rs.getString("Longitude")));
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

	@Override
	public Ville findVille(String codeCommune) {
		Ville ville = new Ville();
		try {
			Class.forName(JDBC_DRIVER);
			cn = DriverManager.getConnection(BDD_URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE = " + codeCommune;
			rs = st.executeQuery(query);

			while (rs.next()) {
				ville.setCodeCommune(rs.getString("Code_commune_INSEE"));
				ville.setNomCommune(rs.getString("Nom_commune"));
				ville.setCodePostal(rs.getString("Code_postal"));
				ville.setLibelleAcheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne5(rs.getString("Ligne_5"));
				ville.setCoordonnee(new Coordonnee(rs.getString("Latitude"), rs.getString("Longitude")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ville;
	}

	@Override
	public String createVille(Ville ville) {
		try {
			Class.forName(JDBC_DRIVER);
			cn = DriverManager.getConnection(BDD_URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String query =  "INSERT INTO `ville_france`(`Code_commune_INSEE`, " +
					"`Nom_commune`, " +
					"`Code_postal`, " +
					"`Libelle_acheminement`, " +
					"`Ligne_5`, " +
					"`Latitude`, " +
					"`Longitude`) " +
					"VALUES ('" + ville.getCodeCommune() + "','"
					+ ville.getNomCommune() + "','"
					+ ville.getCodePostal() + "','"
					+ ville.getLibelleAcheminement() + "','"
					+ ville.getLigne5() + "','"
					+ ville.getCoordonnee().getLatitude() + "','"
					+ ville.getCoordonnee().getLongitude() + "')";
			st.executeUpdate(query);
			answer = SUCCESS;
			return answer;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateVille(Ville ville) {
		try {
			Class.forName(JDBC_DRIVER);
			cn = DriverManager.getConnection(BDD_URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String query = "UPDATE ville_france SET Nom_commune='" + ville.getNomCommune() + "', Code_postal='"
					+ ville.getCodePostal() + "', Libelle_acheminement='" + ville.getLibelleAcheminement()
					+ "', Ligne_5='" + ville.getLigne5() + "', Latitude='" + ville.getCoordonnee().getLatitude() + "', Longitude='"
					+ ville.getCoordonnee().getLongitude() + "' WHERE Code_commune_INSEE='" + ville.getCodeCommune() + "'";
			st.executeUpdate(query);
			answer = SUCCESS;
			return answer;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteVille(String codeCommune) {
		try {
			Class.forName(JDBC_DRIVER);
			cn = DriverManager.getConnection(BDD_URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String query = "DELETE FROM ville_france WHERE Code_commune_INSEE = " + codeCommune;
			st.executeUpdate(query);
			answer = SUCCESS;
			return answer;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String villeSelection(String codePostal) {
		String sql;
		if (codePostal != null) {
			sql = SQL_FIND_VILLES_BY_CODE_POSTAL + codePostal;
		} else {
			sql = SQL_FIND_ALL_VILLES;
		}
		return sql;
	}

}
