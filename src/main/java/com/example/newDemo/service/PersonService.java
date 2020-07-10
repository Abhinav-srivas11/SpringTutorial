package com.example.newDemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.newDemo.dao.*;
import com.example.newDemo.model.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;//since it is a interface it is not instantiated like normal
    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id,Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }
}
