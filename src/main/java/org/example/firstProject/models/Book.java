package org.example.firstProject.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class Book {

    private int id;
    @Size(min = 3, max = 100, message = "Длина имени должна быть больше 3 и меньше 100")
    @NotNull
    private String name;
    @Size (min = 3, max = 100, message = "Длина имени должна быть больше 3 и меньше 100")
    private String author;

    @Min(value = 1, message = "Введите корректный год")
    private int year;

    public Book(String name, String author, int year, int id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
    }


    public Book() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
