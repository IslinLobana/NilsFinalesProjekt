package com.example.Fussballverein;

import com.example.Fussballverein.models.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DBStadionController extends DBController {

    String connectionUrl;
    String username;
    String passwort;
    
    
    // Holt alle Stadions aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Stadion> getAllStadion(){

        // Lokale Stadions-Arraylist erstellen
        ArrayList<Stadion> stadion = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllStadion = "SELECT * FROM `stadion`";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllStadion); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String name = rs.getString("name");
                int sitzplaetze = (int) rs.getLong("sitzplaetze");
                int ticketpreis = (int) rs.getLong("ticketpreis");
                stadion.add(new Stadion(id, name, sitzplaetze, ticketpreis));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return stadion;
    }

    // Füge neue Stadion hinzu
    public void addNewStadion(String stadionName, int stadionSitzplaetze, int stadionTicketpreis) {
        try{
            String sqlSelectAllStadion = "INSERT INTO stadion(name, sitzplaetze, ticketpreis) VALUES('"+stadionName+"','"+stadionSitzplaetze+"','"+stadionTicketpreis+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllStadion); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche eine Stadion
    public void delStadion(int id){
        try{

            String sqlSelectAllStadion = "DELETE FROM stadion WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllStadion); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifische Stadion
    public Stadion getStadion(int id){
        Stadion stadion = null;
        try{
            String sqlSelectStadion = "SELECT * FROM `stadion` WHERE id='"+String.valueOf(id)+"';";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectStadion); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int stadionid = (int) rs.getLong("id");
                String name = rs.getString("name");
                int sitzplaetze = (int) rs.getLong("sitzplaetze");
                int ticketpreis = (int) rs.getLong("ticketpreis");
                stadion = new Stadion(stadionid, name, sitzplaetze, ticketpreis);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return stadion;
    }

    // Hole spezifische Stadion und aktualisiere diese ab
    public Stadion updateStadion(int id, String stadionName, int stadionSitzplaetze, int stadionTicketpreis	){
        Stadion stadion = null;
        try{
            String sqlSelectAllStadion = "UPDATE stadion SET name='"+stadionName+"', sitzplaetze='"+stadionSitzplaetze+"', ticketpreis='"+stadionTicketpreis+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllStadion); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return stadion;
    }

    
}

