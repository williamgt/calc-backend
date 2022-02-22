package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//Is called by the controller and actually calculates expressions with Calculations class
@Service
public class CalculatorService {

    Logger logger = LoggerFactory.getLogger(CalculatorService.class); //Setting up logger for calculator service

    public String getHelloMessage(){
        return "Hello and welcome!";
    }
}
