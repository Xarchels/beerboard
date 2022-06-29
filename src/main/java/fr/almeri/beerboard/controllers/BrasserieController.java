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

import javax.servlet.http.HttpSession;
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
    public String getListBrasseries(Model pModel, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            List<Brasserie> list = (List<Brasserie>) brasserieRepository.findAll();
            pModel.addAttribute("list", list);
            return "brasseries";
        }
        return "redirect:/";
    }

    @GetMapping("/see-brewery/{code}")
    public String getBrewery(Model pModel, HttpSession session, @PathVariable(required = true) String code) {
        if (session.getAttribute("infoConnexion") != null) {
            //Recupère brasserie avec findById avec ce qui est dans l'URL
            //orElseThrow --> renvoie exception si erreur
            Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
            pModel.addAttribute("brasserie", brasserie);
            List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
            pModel.addAttribute("listBiere", list);
            return "brasserieFiche";
        }
        return "redirect:/";
    }

    @GetMapping("/see-brewery1")
    public String getBreweryByCode(Model pModel, @RequestParam String code, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
            pModel.addAttribute("brasserie", brasserie);
            List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
            pModel.addAttribute("listBiere", list);
            return "brasserieFiche";
        }
        return "redirect:/";
    }

    @GetMapping("/add-brewery")
    public String AjoutBrasserie(Model pModel, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Brasserie brasserie = new Brasserie();
            pModel.addAttribute("brasserie", brasserie);
            List<Region> listRegion = (List<Region>) regionRepository.findAll();
            pModel.addAttribute("listRegion", listRegion);
            pModel.addAttribute("action", "add");
            return "brasserieAjout";
        }
        return "redirect:/";
    }

    @PostMapping("/add-brewery-confirm")
    public String AjoutBrasserieConfirm(@ModelAttribute Brasserie brasserie, @RequestParam String action, RedirectAttributes redir, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            if (brasserie.getCodeBrasserie() == ""
                    || brasserie.getNomBrasserie() == ""
                    || brasserie.getVille() == ""
                    || brasserie.getRegion() == null) {
                redir.addFlashAttribute("msg", "Aucun champ marqué de * ne peut être vide.");
                if (action == "add") {
                    return "redirect:/add-brewery";
                }
                if (action == "update") {
                    return "redirect:/modify-brewery";
                }
            } else if (action == "add" && brasserieRepository.existsById(brasserie.getCodeBrasserie())) {
                redir.addFlashAttribute("msg", "L'identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette brasserie n'existe pas déjà.");
                return "redirect:/add-brewery";
            }
            brasserieRepository.save(brasserie);
            return "redirect:/breweries";
        }
        return "redirect:/";
    }

    @GetMapping("/modify-brewery")
    public String ModifieBrasserie(Model pModel, @RequestParam String code, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
            pModel.addAttribute("brasserie", brasserie);
            List<Region> listRegion = (List<Region>) regionRepository.findAll();
            pModel.addAttribute("listRegion", listRegion);
            pModel.addAttribute("action", "update");
            return "brasserieAjout";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-brewery")
    public String SupprimerBrasserie(@RequestParam String code, RedirectAttributes redir, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            if (brasserieRepository.existsById(code)) {
                List<Biere> list = (List<Biere>) biereRepository.getBiereFromBrasserie(code);
                if (list.isEmpty()) {
                    brasserieRepository.deleteById(code);
                } else {
                    redir.addFlashAttribute("msg", "Cette brasserie comporte des bières, suppression impossible.");
                }
            }
            return "redirect:/breweries";
        }
        return "redirect:/";
    }

}
