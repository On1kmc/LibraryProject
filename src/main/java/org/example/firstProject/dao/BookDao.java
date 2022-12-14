package org.example.firstProject.dao;

import org.example.firstProject.models.Book;
import org.example.firstProject.models.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksForId(int id) {
        return jdbcTemplate.query("SELECT id, name, author, year FROM Book WHERE person_id = ?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class),
                new Object[] {id}).stream().findAny().orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public String getPersonByIdIfExist(int book_id) {
        try {
            return jdbcTemplate.queryForObject("SELECT Person.name FROM Book JOIN Person ON Person.id=Book.person_id WHERE book.id = ?", String.class, book_id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void appoint(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", person.getId(), id);
    }

    public void disappoint(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE id = ?", id);
    }

    public void update(Book book, int book_id) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE id = ?",
                book.getName(), book.getAuthor(), book.getYear(), book_id);
    }

    public void remove(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }


}
