package com.example.newDemo.dao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.newDemo.model.Person;
@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int insertPerson(UUID id, Person person) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
//        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        // TODO Auto-generated method stub
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql,new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        // TODO Auto-generated method stub
        return 0;
    }
}
