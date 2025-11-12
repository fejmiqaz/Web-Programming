package mk.finki.ukim.wp.model;

public class Author {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;

    private static long COUNTER = 1L;

    public Author(String name, String surname, String country, String biography) {
        this.id = COUNTER++;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getBiography() {
        return biography;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
