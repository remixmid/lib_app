package dk.teslenko.lib_app.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Это обязательное поле")
    @Size(min=5, max = 100, message = "Введите корректное название(от 5 до 100 символов)")
    private String name;

    @NotEmpty(message = "Это обязательное поле")
    private String author;

    @NotEmpty(message = "Это обязательное поле")
    private int year;
    private int personId;

    public Book() {

    }

    public Book(int id, String name, String author, int year, int personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
