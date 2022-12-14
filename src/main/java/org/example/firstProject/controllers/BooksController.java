package org.example.firstProject.controllers;

import org.example.firstProject.dao.BookDao;
import org.example.firstProject.dao.PersonDao;
import org.example.firstProject.models.Book;
import org.example.firstProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;

    private final PersonDao personDao;


    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String getBooks(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.getBookById(id));
        model.addAttribute("person_name", bookDao.getPersonByIdIfExist(id));
        model.addAttribute("people", personDao.indexPerson());
        return "book-page";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "new-book";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookDao.create(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/appoint")
    public String appointBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDao.appoint(id, person);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/disappoint")
    public String disppointBook(@PathVariable("id") int id) {
        bookDao.disappoint(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getBookById(id));
        return "book-edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDao.update(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.remove(id);
        return "redirect:/books";
    }


}
