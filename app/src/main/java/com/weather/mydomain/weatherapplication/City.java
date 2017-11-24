package com.weather.mydomain.weatherapplication;

/**
 * Created by azzed on 17/10/2017.
 */

// The class describing the city
public class City {

    public int id;
    public String ville, pays, vent, temperature, pression, date;

    public City(String ville, String pays) {
        this.ville = ville;
        this.pays = pays;
    }

    public City(int id, String ville, String pays,String date, String vent, String pression, String temperature) {
        this.id = id;
        this.ville = ville;
        this.pays = pays;
        this.date = date;
        this.vent = vent;
        this.pression = pression;
        this.temperature = temperature;
    }

    public City() {

    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                "ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", date='" + date + '\'' +
                ", vent='" + vent + '\'' +
                ", pression='" + pression + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVent() {
        return vent;
    }
    public void setVent(String vent) {
        this.vent = vent;
    }

    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPression() {
        return pression;
    }
    public void setPression(String pression) {
        this.pression = pression;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
