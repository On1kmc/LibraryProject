package org.example.firstProject.dao;

import org.example.firstProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> indexPerson() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getFromId(int id) {

        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class), new Object[]{id}).stream().findAny().orElse(null);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?", person.getName(), person.getYear(), id);
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, year) VALUES (?, ?)", person.getName(), person.getYear());
    }

}
