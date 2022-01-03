package com.semer.projet_android;

public class Event {
    private int id;
    private String nom_event;
    private String local_event;
    private String date_event;

    public Event(int id, String nom_event, String local_event, String date_event) {
        this.id = id;
        this.nom_event = nom_event;
        this.local_event = local_event;
        this.date_event = date_event;
    }

    public int getId() {
        return id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getLocal_event() {
        return local_event;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setLocal_event(String local_event) {
        this.local_event = local_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public Event() {
    }

    public Event(String nom_event, String local_event, String date_event) {
        this.nom_event = nom_event;
        this.local_event = local_event;
        this.date_event = date_event;
    }
}
