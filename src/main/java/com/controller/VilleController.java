package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.blo.VilleBloImpl;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
    VilleBloImpl villeBloImplService;
	
	@RequestMapping(value = "/villes", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> getVilles(@RequestParam (required = false, value = "codePostal") String codePostal) {
		return villeBloImplService.getVilles(codePostal);
	}

	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public Ville getVille(@RequestParam(value = "codeCommune") String codeCommune) {
		return villeBloImplService.getVille(codeCommune);
	}

	@RequestMapping(value = "/addVille", method = RequestMethod.POST)
	@ResponseBody
	public String addVille(@RequestBody Ville ville) {
		return villeBloImplService.addVille(ville);
	}

	@RequestMapping(value = "/updateVille", method = RequestMethod.PUT)
	@ResponseBody
	public String updateVille(@RequestBody Ville ville) {
		return villeBloImplService.updateVille(ville);
	}

	@RequestMapping(value = "/deleteVille", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteVille(@RequestParam(value = "codeCommune") String codeCommune) {
		return villeBloImplService.deleteVille(codeCommune);
	}

}