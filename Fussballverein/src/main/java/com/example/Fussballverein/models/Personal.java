package com.example.Fussballverein.models;

public class Personal {

    int id;
    String vorname;
    String nachname;
    int age;
    String aufgabe;
    int einkommen;


    public Personal(int id, String vorname, String nachname, int age, String aufgabe, int einkommen){
        setId(id);
        setVorname(vorname);
        setNachname(nachname);
        setAge(age);
        setAufgabe(aufgabe);
        setEinkommen(einkommen);
    }


    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEinkommen(int einkommen) {
        this.einkommen = einkommen;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public String getAufgabe() {
        return aufgabe;
    }
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public int getEinkommen() {
        return einkommen;
    }
    public String getNachname() {
        return nachname;
    }
    public String getVorname() {
        return vorname;
    }

    
}
