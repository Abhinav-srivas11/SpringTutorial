package com.example.newDemo.dao;
import com.example.newDemo.model.*;
import java.util.*;

import org.springframework.stereotype.Repository;
/*We just need ArrayList for all persons, List and UUID for generation of ids*/
/**
 * @author Abhinav
 *
 */

@Repository("FakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {

        return (DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst());
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;

    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToDelete = DB.indexOf(person);
                    if(indexOfPersonToDelete >= 0) {//we are setting aside the person that is present aside using mapping
                        DB.set(indexOfPersonToDelete,new Person(id , update.getName()));//now updating the set aside person with the new person that we received in the arguments above
                        return 1;
                    }
                    return 0;
                }).orElse(0);//return zero if selectPersonById is not present in db

    }

    @Override
    public List<Person> selectAllPeople(){
        return DB;
    }
}
