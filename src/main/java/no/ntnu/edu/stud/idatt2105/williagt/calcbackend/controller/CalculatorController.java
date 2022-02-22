package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.controller;

import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Receive requests from user and calls into the service class
@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService service; //Dependency injection

    Logger logger = LoggerFactory.getLogger(CalculatorController.class); //Setting up logger for calculator controller

    @RequestMapping("/")
    public String helloWorld(){
        //logger.trace("Tracing");
        logger.info("Sending hello message to user");
        /*logger.debug("Debugging");
        logger.info("Info");
        logger.warn("Warning");
        logger.error("Error lol");*/

        return this.service.getHelloMessage();
    }
}
