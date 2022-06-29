package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.models.Region;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BrasserieController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/breweries")
    public String getListBrasseries(Model pModel) {
        List<Brasserie> list = (List<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("list", list);
        return "brasseries";
    }

    @GetMapping("/see-brewery/{code}")
    public String getBrewery(Model pModel, @PathVariable(required = true) String code) {
        //Recupère brasserie avec findById avec ce qui est dans l'URL
        //orElseThrow --> renvoie exception si erreur
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", brasserie);
        List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
        pModel.addAttribute("listBiere", list);
        return "brasserieFiche";
    }

    @GetMapping("/see-brewery1")
    public String getBreweryByCode(Model pModel, @RequestParam String code) {
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", brasserie);
        List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
        pModel.addAttribute("listBiere", list);
        return "brasserieFiche";
    }

    @GetMapping("/add-brewery")
    public String AjoutBrasserie(Model pModel) {
        Brasserie brasserie = new Brasserie();
        pModel.addAttribute("brasserie", brasserie);
        List<Region> listRegion = (List<Region>) regionRepository.findAll();
        pModel.addAttribute("listRegion", listRegion);
        return "brasserieAjout";
    }

    @PostMapping("/add-brewery-confirm")
    public String AjoutBrasserieConfirm(@ModelAttribute Brasserie brasserie, RedirectAttributes redir) {
        if (brasserie.getCodeBrasserie() == ""
                || brasserie.getNomBrasserie() == ""
                || brasserie.getVille() == ""
                || brasserie.getRegion() == null) {
            redir.addFlashAttribute("msg", "Aucun champ marqué de * ne peut être vide.");
            return "redirect:/add-brewery";
        } else if (brasserieRepository.existsById(brasserie.getCodeBrasserie())) {
            redir.addFlashAttribute("msg", "L'identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette brasserie n'existe pas déjà.");
            return "redirect:/add-brewery";
        }
        brasserieRepository.save(brasserie);
        return "redirect:/breweries";
    }

    @PostMapping("/modify-brewery")
    public String ModifieBrasserie(@ModelAttribute Brasserie brasserie) {
        if (!brasserieRepository.existsById(brasserie.getCodeBrasserie())) {
            brasserieRepository.save(brasserie);
            return "redirect:/breweries";
        }
        return "redirect:/add-brewery";
    }

}
