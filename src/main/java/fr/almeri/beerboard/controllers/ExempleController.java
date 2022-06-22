package fr.almeri.beerboard.controllers;


import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.models.Pays;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ExempleController {

    @Autowired //Instancie autmatiquement la variable.
    private PaysRepository paysRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    //localhost:8888/exemple
    @GetMapping("/exemple")
    public String getPageExemple(Model pModel){

        pModel.addAttribute("prenom", "Charles");

        /*Pays pays = new Pays();
        pays.setNomPays("France");
        pays.setConsommation(145.0);
        pays.setProduction(190.9);

        Pays pays1 = new Pays();
        pays1.setNomPays("UK");
        pays1.setConsommation(271.0);
        pays1.setProduction(369.9);

        Pays pays2 = new Pays();
        pays2.setNomPays("Espagne");
        pays2.setConsommation(128.0);
        pays2.setProduction(93.5);

        ArrayList<Pays> listPays = new ArrayList<>();
        listPays.add(pays);
        listPays.add(pays1);
        listPays.add(pays2);*/


        ArrayList<Pays> listPaysFromDatabase = (ArrayList<Pays>) paysRepository.findAll();
        pModel.addAttribute("listPays", listPaysFromDatabase);

        ArrayList<Brasserie> listBrasserieFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listBrasserie", listBrasserieFromDatabase);

        return "exemple";
    }
}
