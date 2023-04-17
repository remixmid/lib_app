package dk.teslenko.lib_app.models;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Person {

    @Valid
    @Size(max = 50, message = "Фио должно быть короче 50 символов")
    @NotEmpty(message = "Поле обязательно. Заполните пожалуйста")
    private String fio;

    @Min(value = 1900, message = "Некорректный год рождения. Укажите верный пожалуйста")
    @Max(value = 2020, message = "Некорректный год рождения. Укажите верный пожалуйста")
    private int dateOfBirth;

    private int id;

    public Person(String fio, int dateOfBirth, int id) {
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public Person() {

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
