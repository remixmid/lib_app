package dk.teslenko.lib_app.dao;

import dk.teslenko.lib_app.models.Book;
import dk.teslenko.lib_app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT book.id, book.name, book.author, book.year FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Person> getPersonList(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT book.id, book.name, book.author, book.year FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getBookOwnerId(int id){
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.id " + "WHERE book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(),
                updatedBook.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void setNull(int id) { jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", null, id); }

    public void setOwner(int id, int person_id) { jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", person_id, id); }
}
