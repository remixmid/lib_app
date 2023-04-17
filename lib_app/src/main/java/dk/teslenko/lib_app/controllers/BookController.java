package dk.teslenko.lib_app.controllers;

import dk.teslenko.lib_app.dao.BookDAO;
import dk.teslenko.lib_app.models.Book;
import dk.teslenko.lib_app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,@ModelAttribute("person") Person person, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> personOptional = bookDAO.getBookOwnerId(id);

        if (personOptional.isPresent()) {
            model.addAttribute("owner", personOptional.get());
        } else {
            model.addAttribute("personList", bookDAO.getPersonList());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id")int id, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String patchMethod(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.setOwner(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") int id) {
        bookDAO.setNull(id);
        return "redirect:/books/" + id;
    }

}
