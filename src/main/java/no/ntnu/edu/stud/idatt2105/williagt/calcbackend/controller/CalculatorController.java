package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.controller;

import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.Calculations;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  /*  @GetMapping("/calculate/{expression}")
    public String calculate(@PathVariable("expression") String expression){
        return this.service.calculateExpression(expression).toString();
    }*/

    @CrossOrigin(origins = "*")
    @GetMapping("/calculate/{expression}")
    public Calculations calculateTakeStringSendJSON(@PathVariable("expression") String expression){
        logger.info("User sent sent expression " + expression);
        return this.service.calculateExpression(expression);
    }

    //{"expression":"2+3","result":"5.0"}
    @GetMapping("/calculate/total-json/{expression}")
    public Calculations calculateAllJSON(@PathVariable("expression") Calculations expression){ //TODO dont really need this
        return new Calculations("2+2");
    }
}
