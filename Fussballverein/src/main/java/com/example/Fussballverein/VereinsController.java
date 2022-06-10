package com.example.Fussballverein;

import java.util.ArrayList;


import com.example.Fussballverein.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VereinsController {


//Neues Mapping für die HTML Seite "money"
    @GetMapping("/money")
    public String money(@RequestParam(name="activePage", required = false, defaultValue = "money") String activePage, Model model){
        model.addAttribute("activePage", "money");
        



        DBPersonalController dbp = new DBPersonalController();
        ArrayList<Personal> personal = dbp.getAllPersonal();

        DBSpielerController dbs = new DBSpielerController();
        ArrayList<Spieler> spieler = dbs.getAllSpieler();
// Variable für die Ausgaben einführen und auf diese dann das jeweilige Einkommen drauf rechnen
        int gesamtausgaben = 0;

        for(Personal pers : personal){
            gesamtausgaben += pers.getEinkommen();
        }

        for(Spieler spiel : spieler){
            gesamtausgaben += spiel.getEinkommen();
        }
        System.out.println(gesamtausgaben);



//Als Attribute für die HTML Seite hinzufügen
        model.addAttribute("ausgaben", gesamtausgaben);

        DBStadionController dbst = new DBStadionController();
        ArrayList<Stadion> stadion = dbst.getAllStadion();
// Variable für die Einnahmen einführen und auf diese dann das jeweilige Einkommen drauf rechnen
        int gesamteinnahmen = 0;


        for(Stadion stad : stadion){
            gesamteinnahmen += stad.getGesamteinkommen();
        }
        System.out.println(gesamteinnahmen);



//Als Attribute für die HTML Seite hinzufügen
        model.addAttribute("einnahmen", gesamteinnahmen);


// Die Ausgaben von den Einnahmen abziehen
        int gesamtgeld = gesamteinnahmen - gesamtausgaben;
//Als Attribute für die HTML Seite hinzufügen
        model.addAttribute("gesamt", gesamtgeld);


        return "index.html";
    } 
}