package mk.finki.ukim.wp.model;

public class Author {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;

    public Author(Long id, String name, String surname, String country, String biography) {
        this.id = id;
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
}
