package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import fr.almeri.beerboard.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BiereController {

    @Autowired
    private BiereRepository biereRepository;
    @Autowired
    private MarqueRepository marqueRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/beers")
    public String getListBiere(Model pModel, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            List<Biere> list = (List<Biere>) biereRepository.findAll();
            pModel.addAttribute("list", list);
            return "bieres";
        }
        return "redirect:/";
    }

    @GetMapping("/see-beer")
    public String getBreweryByCode(Model pModel, @RequestParam String marque, @RequestParam String version, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Marque marque1 = marqueRepository.findById(marque).orElseThrow();
            BiereId biereId = new BiereId(marque1, version);
            pModel.addAttribute("biereId", biereId);
            Biere biere = biereRepository.findById(biereId).orElseThrow();
            pModel.addAttribute("biere", biere);
            return "biereFiche";
        }
        return "redirect:/";
    }

    @GetMapping("/add-beer")
    public String AjoutBiere(Model pModel, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Biere biere = new Biere();
            pModel.addAttribute("biere", biere);
            List<Type> listType = (List<Type>) typeRepository.findAll();
            pModel.addAttribute("listType", listType);
            List<Marque> listMarque = (List<Marque>) marqueRepository.findAll();
            pModel.addAttribute("listMarque", listMarque);
            pModel.addAttribute("action", "add");
            return "biereAjout";
        }
        return "redirect:/";
    }

    @PostMapping("/add-beer-confirm")
    public String AjoutBiereConfirm(@ModelAttribute Biere biere, @RequestParam String action, RedirectAttributes redir, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            if (biere.getMarque() == null
                    || biere.getVersion() == ""
                    || biere.getType() == null
                    || biere.getCouleurBiere() == ""
                    || biere.getTauxAlcool() == 0) {
                redir.addFlashAttribute("msg", "Aucun champ marqué de * ne peut être vide.");
                if (action == "add") {
                    return "redirect:/add-beer";
                }
                if (action == "update") {
                    return "redirect:/modify-beer";
                }
            }
            BiereId biereId = new BiereId(biere.getMarque(), biere.getVersion());
            if (action == "add" && biereRepository.existsById(biereId)) {
                redir.addFlashAttribute("msg", "Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle version.");
                return "redirect:/add-beer";
            }
            biereRepository.save(biere);
            return "redirect:/beers";
        }
        return "redirect:/";
    }

    @GetMapping("/modify-beer")
    public String modifieBrasserie(Model pModel, @RequestParam String marque, @RequestParam String version, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Marque marque1 = marqueRepository.findById(marque).orElseThrow();
            BiereId biereId = new BiereId(marque1, version);
            pModel.addAttribute("biereId", biereId);
            Biere biere = biereRepository.findById(biereId).orElseThrow();
            pModel.addAttribute("biere", biere);
            List<Type> listType = (List<Type>) typeRepository.findAll();
            pModel.addAttribute("listType", listType);
            pModel.addAttribute("action", "update");
            return "biereAjout";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-beer")
    public String SupprimerBrasserie(@RequestParam String marque, @RequestParam String version, HttpSession session) {
        if (session.getAttribute("infoConnexion") != null) {
            Marque marque1 = marqueRepository.findById(marque).orElseThrow();
            BiereId biereId = new BiereId(marque1, version);
            if (biereRepository.existsById(biereId)) {
                biereRepository.deleteById(biereId);
            }
            return "redirect:/beers";
        }
        return "redirect:/";
    }
}
