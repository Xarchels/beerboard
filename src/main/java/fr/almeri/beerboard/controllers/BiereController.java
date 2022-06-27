package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.models.Marque;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BiereController {

    @Autowired
    private BiereRepository biereRepository;
    @Autowired
    private MarqueRepository marqueRepository;

    @GetMapping("/beers")
    public String getListBiere(Model pModel) {
        List<Biere> list = (List<Biere>) biereRepository.findAll();
        pModel.addAttribute("list", list);
        return "bieres";
    }

    @GetMapping("/see-beer")
    public String getBreweryByCode(Model pModel, @RequestParam String marque, @RequestParam String version) {
        Marque marque1 = marqueRepository.findById(marque).orElseThrow();
        BiereId biereId = new BiereId(marque1, version);
        pModel.addAttribute("biereId", biereId);
        Biere biere = biereRepository.findById(biereId).orElseThrow();
        pModel.addAttribute("biere", biere);
        return "biereFiche";
    }
}
