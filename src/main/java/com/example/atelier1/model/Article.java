package com.example.atelier1.model;

public class Article {
    private int id;
    private String designation;

    public int getId() {
        return id;
    }
    public String getDesignation() {
        return designation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Article(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }
    public Article() {}
}
