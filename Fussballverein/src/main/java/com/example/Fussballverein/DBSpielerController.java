package com.example.Fussballverein;

import com.example.Fussballverein.models.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DBSpielerController extends DBController {

    String connectionUrl;
    String username;
    String passwort;
    
    
    // Holt alle Spieler aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Spieler> getAllSpieler(){

        // Lokale Spieler-Arraylist erstellen
        ArrayList<Spieler> spieler = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllSpieler = "SELECT * FROM `spieler`";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllSpieler); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int age = (int) rs.getLong("age");
                String position = rs.getString("position");
                int einkommen = (int) rs.getLong("einkommen");
                spieler.add(new Spieler(id, vorname, nachname, age, position, einkommen));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return spieler;
    }

    // Füge neue Spieler hinzu
    public void addNewSpieler(String spielerVorname, String spielerNachname, int spielerAge, String spielerPosition, int spielerEinkommen) {
        try{
            String sqlSelectAllSpieler = "INSERT INTO spieler(vorname, nachname, age, position, einkommen) VALUES('"+spielerVorname+"','"+spielerNachname+"','"+spielerAge+"','"+spielerPosition+"','"+spielerEinkommen+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllSpieler); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche einen Spieler
    public void delSpieler(int id){
        try{

            String sqlSelectAllSpieler = "DELETE FROM spieler WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllSpieler); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifische Spieler
    public Spieler getSpieler(int id){
        Spieler spieler = null;
        try{
            String sqlSelectSpieler = "SELECT * FROM `spieler` WHERE id='"+String.valueOf(id)+"';";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectSpieler); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int spielerid = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int age = (int) rs.getLong("age");
                String position = rs.getString("position");
                int einkommen = (int) rs.getLong("einkommen");
                spieler = new Spieler(spielerid, vorname, nachname, age, position, einkommen);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return spieler;
    }

    // Hole spezifische Spieler und aktualisiere diese ab
    public Spieler updateSpieler(int id, String spielerVorname, String spielerNachname, int spielerAge, String spielerPosition, int spielerEinkommen){
        Spieler spieler = null;
        try{
            String sqlSelectAllSpieler = "UPDATE spieler SET vorname='"+spielerVorname+"', nachname='"+spielerNachname+"', age='"+spielerAge+"', position='"+spielerPosition+"', einkommen='"+spielerEinkommen+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllSpieler); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return spieler;
    }

    
}

