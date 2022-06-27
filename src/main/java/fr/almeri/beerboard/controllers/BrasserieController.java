package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BrasserieController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private BiereRepository biereRepository;

    @GetMapping("/breweries")
    public String getListBrasseries(Model pModel){
        List<Brasserie> list = (List<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("list", list);
        return "brasseries";
    }

    @GetMapping("/see-brewery/{code}")
    public String getBrewery(Model pModel, @PathVariable(required = true) String code){
        //Recupère brasserie avec findById avec ce qui est dans l'URL
        //orElseThrow --> renvoie exception si erreur
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", brasserie);
        List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
        pModel.addAttribute("listBiere", list);
        return "brasserieFiche";
    }

    @GetMapping("/see-brewery1")
    public String getBreweryByCode(Model pModel, @RequestParam String code){
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", brasserie);
        List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
        pModel.addAttribute("listBiere", list);
        return "brasserieFiche";
    }

    @GetMapping("/add-brewery")
    public String AjoutBrasserie(Model pModel){
        return "add-brewery";
    }

    @PostMapping("/modify-brewery")
    public String ModifieBrasserie(@ModelAttribute Brasserie brasserie){
        if (!brasserieRepository.existsById(brasserie.getCodeBrasserie())){
            brasserieRepository.save(brasserie);
            return "redirect:/breweries";
        }
        return "redirect:/add-brewery";
    }

}
