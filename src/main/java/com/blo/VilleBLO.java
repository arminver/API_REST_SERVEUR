package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLO implements IVilleBLO {
	
	@Autowired
	private VilleDAO villeDAO;

	@Override
	public List<Ville> getInfoVilles(String codePostal) {
		return villeDAO.findVilles(codePostal);
	}

}
