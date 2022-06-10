package com.example.Fussballverein.models;

public class Spieler {

    int id;
    String vorname;
    String nachname;
    String position;
    int age;
    int einkommen;


    public Spieler(int id, String vorname, String nachname, int age, String position, int einkommen){
        setId(id);
        setVorname(vorname);
        setNachname(nachname);
        setPosition(position);
        setAge(age);
        setEinkommen(einkommen);
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPosition(String position) {
        this.position = position;
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
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getPosition() {
        return position;
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
