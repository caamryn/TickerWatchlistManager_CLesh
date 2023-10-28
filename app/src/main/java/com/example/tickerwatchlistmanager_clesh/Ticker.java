package com.example.tickerwatchlistmanager_clesh;

public class Ticker {

    private String name;
    private String link;

    public Ticker(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
