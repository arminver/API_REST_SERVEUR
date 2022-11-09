package com.dao;

import java.util.List;

import com.dto.Ville;

public interface IVilleDAO {
	
	public List<Ville> findVilles(String codePostal);

}
