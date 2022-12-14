package org.example.firstProject.models;

import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class Person {
    private int id;
    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Длина имени должна быть более 2 и менее 100 символов")
    private String name;
    @NotNull
    @Min(value = 1900)
    private int year;


    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Person() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
