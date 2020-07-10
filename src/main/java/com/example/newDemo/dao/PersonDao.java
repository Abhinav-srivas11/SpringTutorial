package com.example.newDemo.dao;
import java.util.*;

import com.example.newDemo.model.Person;
/**
 * @author Abhinav
 *
 * */
public interface PersonDao {
    //since this is an interface so no function definition is provided
    //we will implement all these functions in classes where PersonDao interface will be implemented by the class
    //make a function to create a new person using a id and Person class object
    int insertPerson(UUID id , Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);

    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
