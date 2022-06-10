package com.example.Fussballverein.models;

public class Stadion {

    int id;
    String name;
    int sitzplaetze;
    int ticketpreis;





    public Stadion(int id,String name, int sitzplaetze, int ticketpreis){
        setId(id);
        setName(name);
        setSitzplaetze(sitzplaetze);
        setTicketpreis(ticketpreis);
    }

    public int getGesamteinkommen(){
        return getSitzplaetze()*getTicketpreis();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getSitzplaetze() {
        return sitzplaetze;
    }
    public int getTicketpreis() {
        return ticketpreis;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }
    public void setTicketpreis(int ticketpreis) {
        this.ticketpreis = ticketpreis;
    }
    
}
