package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import fr.almeri.beerboard.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class IndexController {

    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private BiereRepository biereRepository;
    @Autowired
    private MarqueRepository marqueRepository;
    @Autowired
    private BrasserieRepository brasserieRepository;

    @GetMapping("/index")
    public String home(Model pModel, HttpSession pSession) {
        if (pSession.getAttribute("infoConnexion") != null) {
            pModel.addAttribute("bieres", 328);
            pModel.addAttribute("brasseries", 99);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

            //pieChart
            ArrayList<String> labelsPieChart = brasserieRepository.getRegion();
            ArrayList<Integer> datasPieChart = brasserieRepository.getNbBrasserieRegion();
            pModel.addAttribute("labelsPieChart", labelsPieChart);
            pModel.addAttribute("datasPieChart", datasPieChart);

            //AreaChart
            ArrayList<String> labelsAreaChart = biereRepository.getAlcool();
            ArrayList<Integer> datasAreaChart = biereRepository.getNbBiereAlcool();
            pModel.addAttribute("labelsAreaChart", labelsAreaChart);
            pModel.addAttribute("datasAreaChart", datasAreaChart);

            //Consommation de bière par pays
            ArrayList<String> labelsBarChart = paysRepository.getListNomPays();
            ArrayList<Integer> datasConsommation = paysRepository.getListConsPays();
            ArrayList<Integer> datasProduction = paysRepository.getListProdPays();
            pModel.addAttribute("labelsBarChart", labelsBarChart);
            pModel.addAttribute("datasConsommation", datasConsommation);
            pModel.addAttribute("datasProduction", datasProduction);

            ArrayList<String> labelsBarChart1 = marqueRepository.getBrasserie();
            ArrayList<Integer> datasBarChart1 = marqueRepository.getNbMarqueBrasserie();
            pModel.addAttribute("labelsBarChart1", labelsBarChart1);
            pModel.addAttribute("datasBarChart1", datasBarChart1);

            ArrayList<String> labelsBarChart2 = biereRepository.getMarque();
            ArrayList<Integer> datasBarChart2 = biereRepository.getNbVersionBiere();
            pModel.addAttribute("labelsBarChart2", labelsBarChart2);
            pModel.addAttribute("datasBarChart2", datasBarChart2);

            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession) {
        pSession.invalidate();
        return "redirect:/";
    }
}
