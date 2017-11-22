package com.weather.mydomain.weatherapplication;

import android.util.Log;

/**
 * Created by azzed on 17/10/2017.
 */

// The class describing the city
public class City {

    public String ville, pays, vent, temperature, pression, date;

    public City(String ville, String pays) {
        this.ville = ville;
        this.pays = pays;
    }

    public City(String ville, String pays, String vent, String temperature, String pression, String date) {
        this.ville = ville;
        this.pays = pays;
        this.vent = vent;
        this.temperature = temperature;
        this.pression = pression;
        this.date = date;
    }

    @Override
    public String toString() {
        return "City{" +
                "ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", vent='" + vent + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pression='" + pression + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

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
