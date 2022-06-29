package za.co.programme.jurrius.recyclerviewvideo;

public class Person {

    private String name;
    private String surname;
    private String perference;

    public Person(String name, String surname, String perference) {
        this.name = name;
        this.surname = surname;
        this.perference = perference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPerference() {
        return perference;
    }

    public void setPerference(String perference) {
        this.perference = perference;
    }

}
