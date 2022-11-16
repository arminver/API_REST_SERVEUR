package com.dao;

import java.util.List;

import com.dto.Ville;

public interface IVilleDao {
	
	List<Ville> findVilles(String codePostal);

	Ville findVille(String codeCommune);

	String createVille(Ville ville);

	String updateVille(Ville ville);

	String deleteVille(String codeCommune);

}
