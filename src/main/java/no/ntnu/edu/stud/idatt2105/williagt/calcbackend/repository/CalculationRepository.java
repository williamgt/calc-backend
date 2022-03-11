package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalculationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
