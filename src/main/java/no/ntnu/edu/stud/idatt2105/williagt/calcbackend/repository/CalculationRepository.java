package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.repository;

import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.Calculations;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalculationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Calculations> getAllACalculationsByUserId(long userId){
        return jdbcTemplate.query("SELECT * FROM calculation WHERE userId=?",
                BeanPropertyRowMapper.newInstance(Calculations.class), userId);
    }

    public int insertCalculationWithUser(User user, Calculations calculation){
        return jdbcTemplate.update("INSERT INTO calculation (userId, expression, result) VALUES(?,?,?)",
                new Object[] {user.getId(), calculation.getExpression(), calculation.getResult()});
    }

    public int insertCalculationNoUser(Calculations calculation){
        return jdbcTemplate.update("INSERT INTO calculation (userId, expression, result) VALUES(?,?,?)",
                new Object[] {-1, calculation.getExpression(), calculation.getResult()});
    }
}
