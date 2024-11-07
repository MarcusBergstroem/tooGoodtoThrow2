package com.example.tooGoodToThrow.Controller;

import com.example.tooGoodToThrow.Model.Madvare;
import com.example.tooGoodToThrow.Service.MadvareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class HomeController {
    private final MadvareService madvareService;

    public HomeController(MadvareService madvareService) {
        this.madvareService = madvareService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("madvarer", madvareService.fetchAll());
        return "home/index";
    }

    @GetMapping("/create")
    public String create(){
        return "home/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Madvare m){
        madvareService.addMadvare(m);
        return "redirect:/";
    }

    @GetMapping("/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model){
        model.addAttribute("person", madvareService.findMadvareById(id));
        return "home/viewOne";
    }

    @GetMapping("/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id){
        boolean deleted = madvareService.deleteMadvare(id);
        if (deleted){
            return "redirect:/";
        }
        return "redirect:/";
    }

    /*@GetMapping("/updateOne/{id}")

    public String updateOne(@PathVariable("id") int id, Model model){
        model.addAttribute("madvare", madvareService.findMadvareById(id));
        return "home/updateOne";
    }
    */


    /*@PostMapping("/update")
    public String update(@ModelAttribute Madvare madvare){
        madvareService.updateMadvare(madvare);
        return "redirect:/";
    }
    */
}
