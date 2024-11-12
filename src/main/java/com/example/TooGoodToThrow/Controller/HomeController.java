package com.example.TooGoodToThrow.Controller;

import com.example.TooGoodToThrow.Model.Madvare;
import com.example.TooGoodToThrow.Service.MadvareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


//Dette fortæller Spring boot at her ligger home controlleren
@Controller
public class HomeController {
    //Der oprettes en service instans
    private final MadvareService madvareService;
    //Konstruktør laver en instans af home controller
    public HomeController(MadvareService madvareService) {
        this.madvareService = madvareService;
    }
    //GetMapping definerer metode der køres ved den givne get-request
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("madvarer", madvareService.fetchAll());
        return "home/index";
    }
    @GetMapping("/createVirksomhed")
    public String createVirksomhed(){
        return "home/createVirksomhed";
    }

    @GetMapping("/createOrganisation")
    public String createOrganisation(){
        return "home/createOrganisation";
    }
    @GetMapping("/listFood")
    public String listFood(Model model){
        model.addAttribute("madvarer", madvareService.fetchAll());
        return "home/listFood";
    }
    @GetMapping("/create")
    public String create(){
        return "create";
    }
    //Postrequest definerer metoden der skal gemme noget til databasen
    //ModelAttribute tager parametre fra htmlsiden og kalder dem Madvarer m.
    @PostMapping("/create")
    public String create(@ModelAttribute Madvare m){
        madvareService.addMadvare(m);
        return "redirect:/";
    }
    //Når denne request modtages med en food id, returneres madvaren fra databasen og vises på html'en
    //PathVariable gør det muligt at finde noget bestem (i dette tilfælde id) fra url'en.
    @GetMapping("/viewOneFood/{id}")
    public String viewOneFood(@PathVariable("id") int id, Model model){
        model.addAttribute("madvare", madvareService.findMadvareById(id));
        return "home/viewOneFood";
    }
    //Når denne request modtages slettes en madvare fra tabellen og forsiden genindlæses.
    //Ved redirect sikres at bruges ikke ved en fejl køber to gange hvis han går tilbage i browseren.

    @GetMapping("/deleteOneFood/{id}")
    public String deleteOneFood(@PathVariable("id") int id){
        madvareService.deleteMadvare(id);
        return "redirect:/";
    }
}
