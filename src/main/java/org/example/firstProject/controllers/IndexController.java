package org.example.firstProject.controllers;

import org.example.firstProject.dao.BookDao;
import org.example.firstProject.dao.PersonDao;
import org.example.firstProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public IndexController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(@ModelAttribute("person") Person person, Model model) {
        model.addAttribute("people", personDao.indexPerson());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getFromId(id));
        model.addAttribute("books", bookDao.getBooksForId(id));
        return "person";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getFromId(id));
        return "person-edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.deleteById(id);
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "person-edit";
        personDao.update(id, person);
        return "redirect:/{id}";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("person") Person person) {
        return "new-person";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "new-person";
        personDao.add(person);
        return "redirect:/";
    }



}
