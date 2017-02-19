package com.example.simeon.firstapplication.models;

public class Superhero {
    private String id;
    private String name;
    private String secretIdentity;

    public Superhero(String id, String name, String secretIdentity) {
        this.setId(id);
        this.setName(name);
        this.setSecretIdentity(secretIdentity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }
}
