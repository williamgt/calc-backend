package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.repository;

import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByMail(String mail){
        try{
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email=?",
                    BeanPropertyRowMapper.newInstance(User.class), mail);
            return user;
        }catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    public User findById(int id){
        try{
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        }catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    public int registerUser(User user){
        return jdbcTemplate.update("INSERT INTO user (email, fullName) VALUES (?, ?)", user.getEmail(), user.getFullName() );
    }
}
