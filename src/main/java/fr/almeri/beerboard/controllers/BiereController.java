package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.repositories.BiereRepository;
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

    @GetMapping("/beers")
    public String getListBiere(Model pModel) {
        List<Biere> list = (List<Biere>) biereRepository.findAll();
        pModel.addAttribute("list", list);
        return "bieres";
    }
}
