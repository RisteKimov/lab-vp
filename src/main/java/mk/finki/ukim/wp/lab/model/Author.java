package mk.finki.ukim.wp.lab.model;

public class Author {
    private long ID;
    private String name;
    private String surname;
    private String biography;

    public Author(long ID, String name, String surname, String biography) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
    public boolean matchingID(long ID) {
        return this.ID == ID;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBiography() {
        return biography;
    }
    public String fullname()
    {
        return name + " " + surname;
    }
}

