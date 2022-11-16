package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDaoImpl;
import com.dto.Ville;

@Service
public class VilleBloImpl implements IVilleBlo {
	
	@Autowired
	private VilleDaoImpl villeDaoImpl;

	@Override
	public List<Ville> getVilles(String codePostal) {
		return villeDaoImpl.findVilles(codePostal);
	}

	@Override
	public Ville getVille(String codeCommune) {
		return villeDaoImpl.findVille(codeCommune);
	}

	@Override
	public String addVille(Ville ville) {
		return villeDaoImpl.createVille(ville);
	}

	@Override
	public String updateVille(Ville ville) {
		return villeDaoImpl.updateVille(ville);
	}

	@Override
	public String deleteVille(String codeCommune) {
		return villeDaoImpl.deleteVille(codeCommune);
	}

}
