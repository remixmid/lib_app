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
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (fio, dateofbirth) VALUES (?, ?)", person.getFio(), person.getDateOfBirth());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET fio=?, dateofbirth=? WHERE id=?", updatedPerson.getFio(), updatedPerson.getDateOfBirth(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from person WHERE id=?", id);
    }

    public List<Book> getBooks(int id){
        return jdbcTemplate.query("SELECT book.id, book.name, book.author, book.year from book where person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public Optional<Person> getPersonByFIO(String fio){
        return jdbcTemplate.query("SELECT * FROM person WHERE fio=?", new Object[]{fio}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
