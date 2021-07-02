package de.fiducia.langermann.langer_mann.repositories.entities;

public class TinyPerson {

    private final String id;
    private final String nachname;

    public TinyPerson(String id, String nachname) {
        this.id = id;
        this.nachname = nachname;
    }

    public String getId() {
        return id;
    }

    public String getNachname() {
        return nachname;
    }

    @Override
    public String toString() {
        return "TinyPerson{" +
                "id='" + id + '\'' +
                ", nachname='" + nachname + '\'' +
                '}';
    }
}
