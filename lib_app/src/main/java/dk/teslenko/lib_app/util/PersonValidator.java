package dk.teslenko.lib_app.util;

import dk.teslenko.lib_app.dao.PersonDAO;
import dk.teslenko.lib_app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.getPersonByFIO(person.getFio()).isPresent())
            errors.rejectValue("fio", "", "Имя должно быть уникальным");
    }
}
