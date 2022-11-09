package com.blo;

import java.util.List;

import com.dto.Ville;

public interface IVilleBLO {
	
	public List<Ville> getInfoVilles(String codePostal);

}
