package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.service;

import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.Calculations;
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

    public Calculations calculateExpression(String expression){
        Calculations c = Calculations.calculate(expression);
        if(!c.getResult().matches("^[0-9]\\d*(\\.\\d+)?$")){
            //TODO want to change logger directly under to warn instead of info
            logger.info("Something went wrong in service when calculating: " + c.getResult()); //TODO consider something else than letting getResult return the error message, it looks really ugly
        }else{
            logger.info("Calculated valid expression resulting in " + c.getResult());
        }
        return c;
    }
}
