package com.example.apisncf;

public class Sncf {
    private String depart;
    private String arrive;
    private String dateDepart;
    private String dateArrive;

    public Sncf() {
    }

    public Sncf(String depart, String arrive, String dateDepart, String dateArrive) {
        this.depart = depart;
        this.arrive = arrive;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }
}
