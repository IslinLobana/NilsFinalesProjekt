package com.example.Fussballverein;

import java.util.ArrayList;


import com.example.Fussballverein.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpielerController {

    ArrayList<Spieler> spieler;

    public SpielerController(){
        setSpieler(new ArrayList<Spieler>());

        //createDemoData();
        loadSpielerFromDB();
    }


    private void loadSpielerFromDB(){
        DBSpielerController db = new DBSpielerController();
        setSpieler(db.getAllSpieler());
    }

    @GetMapping("/spieler")
    public String spieler(@RequestParam(name="activePage", required = false, defaultValue = "spieler") String activePage, Model model){
        loadSpielerFromDB();
        model.addAttribute("activePage", "spieler");
        model.addAttribute("spieler", getSpieler());


        model.addAttribute("personen", getSpieler());
        return "index.html";


    }

    @RequestMapping("/delspieler")
    public String delspieler(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "spieler") String activePage, Model model){
        DBSpielerController db = new DBSpielerController();
        db.delSpieler(id);
        return "redirect:/spieler";
    }

    @RequestMapping("/changespieler")
    public String changespieler(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changespieler") String activePage, Model model){
        // Spieler zur Bearbeitung laden
        DBSpielerController db = new DBSpielerController();
        model.addAttribute("spieler", db.getSpieler(id));
        model.addAttribute("spielerid", id);

        System.out.println(spieler);
        
        model.addAttribute("activePage", "spielerUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatespieler")
    public String updatespieler(@RequestParam(name="spielerId", required = true, defaultValue = "null") int spielerId, @RequestParam(name="spielerVorname", required = true, defaultValue = "null") String spielerVorname, @RequestParam(name="spielerNachname", required = true, defaultValue = "null") String spielerNachname, @RequestParam(name="spielerAge", required = true, defaultValue = "null") int spielerAge, @RequestParam(name="spielerPosition", required = true, defaultValue = "null") String spielerPosition, @RequestParam(name="spielerEinkommen", required = true, defaultValue = "null") int spielerEinkommen, @RequestParam(name="activePage", required = false, defaultValue = "spieler") String activePage, Model model){
        DBSpielerController db = new DBSpielerController();
        db.updateSpieler(spielerId, spielerVorname, spielerNachname, spielerAge, spielerPosition, spielerEinkommen);
        return "redirect:/spieler";
    }
    
    @RequestMapping("/addspieler")
    public String addspieler(@RequestParam(name="spielerVorname", required = true, defaultValue = "null") String spielerVorname,@RequestParam(name="spielerNachname", required = true, defaultValue = "null") String spielerNachname, @RequestParam(name="spielerAge", required = true, defaultValue = "null") int spielerAge, @RequestParam(name="spielerPosition", required = true, defaultValue = "null") String spielerPosition, @RequestParam(name="spielerEinkommen", required = true, defaultValue = "null") int spielerEinkommen, @RequestParam(name="activePage", required = false, defaultValue = "spieler") String activePage, Model model){
        DBSpielerController db = new DBSpielerController();
        db.addNewSpieler(spielerVorname, spielerNachname, spielerAge, spielerPosition, spielerEinkommen);
        return "redirect:/spieler";
    }
    
    
    public void setSpieler(ArrayList<Spieler> spieler) {
        this.spieler = spieler;
    }

    public ArrayList<Spieler> getSpieler() {
        return spieler;
    }
    
    
}
