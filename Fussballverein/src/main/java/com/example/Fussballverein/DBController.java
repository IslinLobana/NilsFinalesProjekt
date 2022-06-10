package com.example.Fussballverein;



public class DBController {

    String connectionUrl;
    String username;
    String passwort;
    
    public DBController(){
        // Oberklasse für die DB COntroller um Code zu sparen
        // javadb ist der Name der Datenbank, kann auch bei euch anders sein!
        setConnectionUrl("jdbc:mysql://localhost:3306/javadb");
        setPasswort("root");
        setUsername("root");
    }
    

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getConnectionUrl() {
        return connectionUrl;
    }
    public String getPasswort() {
        return passwort;
    }
    public String getUsername() {
        return username;
    }

}
