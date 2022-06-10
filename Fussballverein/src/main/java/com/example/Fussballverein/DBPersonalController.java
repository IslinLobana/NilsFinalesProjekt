package com.example.Fussballverein;

import com.example.Fussballverein.models.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DBPersonalController extends DBController {

    String connectionUrl;
    String username;
    String passwort;
    
    
    // Holt alle Personals aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Personal> getAllPersonal(){

        // Lokale Personals-Arraylist erstellen
        ArrayList<Personal> personal = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllPersonal = "SELECT * FROM `personal`";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonal); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int age = (int) rs.getLong("age");
                String aufgabe = rs.getString("aufgabe");
                int einkommen = (int) rs.getLong("einkommen");
                personal.add(new Personal(id, vorname, nachname, age, aufgabe, einkommen));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personal;
    }

    // Füge neue Personal hinzu
    public void addNewPersonal(String personalVorname, String personalNachname, int personalAge, String personalAufgabe, int personalEinkommen) {
        try{
            String sqlSelectAllPersonal = "INSERT INTO personal(vorname, nachname, age, aufgabe, einkommen) VALUES('"+personalVorname+"','"+personalNachname+"','"+personalAge+"','"+personalAufgabe+"','"+personalEinkommen+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonal); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche eine Personal
    public void delPersonal(int id){
        try{

            String sqlSelectAllPersonal = "DELETE FROM personal WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonal); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifische Personal
    public Personal getPersonal(int id){
        Personal personal = null;
        try{
            String sqlSelectPersonal = "SELECT * FROM `personal` WHERE id='"+String.valueOf(id)+"';";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectPersonal); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int personalid = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int age = (int) rs.getLong("age");
                String aufgabe= rs.getString("aufgabe");
                int einkommen = (int) rs.getLong("einkommen");
                personal = new Personal(personalid, vorname, nachname, age, aufgabe, einkommen);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personal;
    }

    // Hole spezifische Personal und aktualisiere diese ab
    public Personal updatePersonal(int id, String personalVorname, String personalNachname, int personalAge, String personalAufgabe, int personalEinkommen){
        Personal personal = null;
        try{
            String sqlSelectAllPersonal = "UPDATE personal SET vorname='"+personalVorname+"', nachname='"+personalNachname+"', age='"+personalAge+"', aufgabe='"+personalAufgabe+"', einkommen='"+personalEinkommen+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonal); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personal;
    }

    /*
    // Holt alle Personals aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Person> getAllPersonen(){

        // Lokale Personals-Arraylist erstellen
        ArrayList<Person> personen = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllPersonal = "SELECT * FROM person";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonal); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String name = rs.getString("name");
                personen.add(new Person(name, id));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personen;
    }
    */
    
}

