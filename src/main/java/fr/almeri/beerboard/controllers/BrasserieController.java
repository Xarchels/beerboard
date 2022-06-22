package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BrasserieController {

    @Autowired
    private BrasserieRepository brasserieRepository;

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
        return "brasseries";
    }

    @GetMapping("/see-brewery1")
    public String getBreweryByCode(Model pModel, @RequestParam String code){
        return "brasseries";
    }
}
