package com.example.Fussballverein;

import java.util.ArrayList;


import com.example.Fussballverein.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StadionController {

    ArrayList<Stadion> stadion;

    public StadionController(){
        setStadion(new ArrayList<Stadion>());

        //createDemoData();
        loadStadionFromDB();
    }


    private void loadStadionFromDB(){
        DBStadionController db = new DBStadionController();
        setStadion(db.getAllStadion());
    }

    @GetMapping("/stadion")
    public String stadion(@RequestParam(name="activePage", required = false, defaultValue = "stadion") String activePage, Model model){
        loadStadionFromDB();
        model.addAttribute("activePage", "stadion");
        model.addAttribute("stadion", getStadion());


        model.addAttribute("personen", getStadion());
        return "index.html";


    }

    @RequestMapping("/delstadion")
    public String delstadion(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "stadion") String activePage, Model model){
        DBStadionController db = new DBStadionController();
        db.delStadion(id);
        return "redirect:/stadion";
    }

    @RequestMapping("/changestadion")
    public String changestadion(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changestadion") String activePage, Model model){
        // Stadion zur Bearbeitung laden
        DBStadionController db = new DBStadionController();
        model.addAttribute("stadion", db.getStadion(id));
        model.addAttribute("stadionid", id);

        System.out.println(stadion);
        
        model.addAttribute("activePage", "stadionUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatestadion")
    public String updatestadion(@RequestParam(name="stadionId", required = true, defaultValue = "null") int stadionId, @RequestParam(name="stadionName", required = true, defaultValue = "null") String stadionName, @RequestParam(name="stadionSitzplaetze", required = true, defaultValue = "null") int stadionSitzplaetze, @RequestParam(name="stadionTicketpreis", required = true, defaultValue = "null") int stadionTicketpreis, @RequestParam(name="activePage", required = false, defaultValue = "stadion") String activePage, Model model){
        DBStadionController db = new DBStadionController();
        db.updateStadion(stadionId, stadionName, stadionSitzplaetze, stadionTicketpreis);
        return "redirect:/stadion";
    }
    
    @RequestMapping("/addstadion")
    public String addstadion(@RequestParam(name="stadionName", required = true, defaultValue = "null") String stadionName, @RequestParam(name="stadionSitzplaetze", required = true, defaultValue = "null") int stadionSitzplaetze, @RequestParam(name="stadionTicketpreis", required = true, defaultValue = "null") int stadionTicketpreis, @RequestParam(name="activePage", required = false, defaultValue = "stadion") String activePage, Model model){
        DBStadionController db = new DBStadionController();
        db.addNewStadion(stadionName, stadionSitzplaetze, stadionTicketpreis);
        return "redirect:/stadion";
    }
    
    
    public void setStadion(ArrayList<Stadion> stadion) {
        this.stadion = stadion;
    }

    public ArrayList<Stadion> getStadion() {
        return stadion;
    }
    
    
}
