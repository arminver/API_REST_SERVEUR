package com.blo;

import java.util.List;

import com.dto.Ville;

public interface IVilleBlo {
	
	List<Ville> getVilles(String codePostal);

	Ville getVille(String codeCommune);

	String addVille(Ville ville);

	String updateVille(Ville ville);

	String deleteVille(String codeCommune);

}
