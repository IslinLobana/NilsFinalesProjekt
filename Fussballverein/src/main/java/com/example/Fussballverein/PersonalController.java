package com.example.Fussballverein;

import java.util.ArrayList;


import com.example.Fussballverein.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonalController {

    ArrayList<Personal> personal;

    public PersonalController(){
        setPersonal(new ArrayList<Personal>());

        //createDemoData();
        loadPersonalFromDB();
    }


    private void loadPersonalFromDB(){
        DBPersonalController db = new DBPersonalController();
        setPersonal(db.getAllPersonal());
    }

    @GetMapping("/personal")
    public String personal(@RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        loadPersonalFromDB();
        model.addAttribute("activePage", "personal");
        model.addAttribute("personal", getPersonal());


        model.addAttribute("personen", getPersonal());
        return "index.html";


    }

    @RequestMapping("/delpersonal")
    public String delpersonal(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        DBPersonalController db = new DBPersonalController();
        db.delPersonal(id);
        return "redirect:/personal";
    }

    @RequestMapping("/changepersonal")
    public String changepersonal(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changepersonal") String activePage, Model model){
        // Personal zur Bearbeitung laden
        DBPersonalController db = new DBPersonalController();
        model.addAttribute("personal", db.getPersonal(id));
        model.addAttribute("personalid", id);

        System.out.println(personal);
        
        model.addAttribute("activePage", "personalUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatepersonal")
    public String updatepersonal(@RequestParam(name="personalId", required = true, defaultValue = "null") int personalId, @RequestParam(name="personalVorname", required = true, defaultValue = "null") String personalVorname, @RequestParam(name="personalNachname", required = true, defaultValue = "null") String personalNachname, @RequestParam(name="personalAge", required = true, defaultValue = "null") int personalAge, @RequestParam(name="personalAufgabe", required = true, defaultValue = "null") String personalAufgabe, @RequestParam(name="personalEinkommen", required = true, defaultValue = "null") int personalEinkommen, @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        DBPersonalController db = new DBPersonalController();
        db.updatePersonal(personalId, personalVorname, personalNachname, personalAge, personalAufgabe, personalEinkommen);
        return "redirect:/personal";
    }
    
    @RequestMapping("/addpersonal")
    public String addpersonal(@RequestParam(name="personalVorname", required = true, defaultValue = "null") String personalVorname,@RequestParam(name="personalNachname", required = true, defaultValue = "null") String personalNachname, @RequestParam(name="personalAge", required = true, defaultValue = "null") int personalAge, @RequestParam(name="personalAufgabe", required = true, defaultValue = "null") String personalAufgabe, @RequestParam(name="personalEinkommen", required = true, defaultValue = "null") int personalEinkommen, @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        DBPersonalController db = new DBPersonalController();
        db.addNewPersonal(personalVorname, personalNachname, personalAge, personalAufgabe, personalEinkommen);
        return "redirect:/personal";
    }
    
    
    public void setPersonal(ArrayList<Personal> personal) {
        this.personal = personal;
    }

    public ArrayList<Personal> getPersonal() {
        return personal;
    }
    
    
}
