package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.Calculations;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model.User;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.repository.CalculationRepository;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.repository.UserRepository;
import no.ntnu.edu.stud.idatt2105.williagt.calcbackend.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Receive requests from user and calls into the service class
@CrossOrigin(origins = "*")
@RestController
public class CalculatorController {
    ObjectMapper mapper = new ObjectMapper(); //TODO is this allowed?

    @Autowired
    private CalculatorService service; //Dependency injection

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CalculationRepository calcRepo;

    Logger logger = LoggerFactory.getLogger(CalculatorController.class); //Setting up logger for calculator controller

    @RequestMapping("/")
    public String helloWorld(){
        logger.info("Sending hello message to user");
        return this.service.getHelloMessage();
    }

    @GetMapping("/calculate/{expression}")
    public Calculations calculateTakeStringSendJSON(@PathVariable("expression") String expression){
        logger.info("User sent sent expression " + expression);
        return this.service.calculateExpression(expression);
    }

    @GetMapping("/users-by-id/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = userRepo.findById(id);
        if(user == null) logger.error("No user in db with id " + id);
        else logger.info("Returning user with id " + id);
        return user;
    }

    @GetMapping("/users-by-email/{email}")
    public User getUserById(@PathVariable("email") String email){
        User user = userRepo.findByMail(email);
        if(user == null) logger.error("No user in db with email " + email);
        else logger.info("Returning user with email " + email);
        return user;
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            userRepo.registerUser(user);
            return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/login-request/{username}/{password}")
    public User getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){ //This user should only contain username and password
        User returnUser = userRepo.findByUsernameAndPassword(username, password);
        if(returnUser == null) logger.error("No such user in db");
        else logger.info("Returning user");
        return returnUser;
    }

    @GetMapping("/calculations-by-id/{user-id}")
    public List<Calculations> getCalculationsByUserId(@PathVariable("user-id") int userId){
        return calcRepo.getAllACalculationsByUserId(userId);
    }

    @GetMapping("/calculations-by-email/{user-email}")
    public List<Calculations> getCalculationsByUserEmail(@PathVariable("user-email") String userEmail){
        User user = userRepo.findByMail(userEmail);
        if(user == null){
            logger.error("No user with mail '" + userEmail + "' is registered.");
            return null;
        }
        return calcRepo.getAllACalculationsByUserId(user.getId());
    }

    @PostMapping("/calculations/add-no-user")
    public ResponseEntity<String> addCalculationNoUser(@RequestBody Calculations calculation){
        try{
            calcRepo.insertCalculationNoUser(calculation);
            return new ResponseEntity<>("Calculation was successfully added.", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/calculations/add-with-user")
    public ResponseEntity<String> addCalculationWithUser(@RequestBody Object userAndCalculation){ //This is actually an ArrayList
        try{
            ArrayList list = (ArrayList) userAndCalculation;
            if(list.size() != 2){
                logger.error("Illegal amount of objects");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Converting objects
            User user = mapper.convertValue(list.get(0), User.class);
            Calculations calc = mapper.convertValue(list.get(1), Calculations.class);
            user = userRepo.findByMail(user.getEmail()); //Checking to see if user is registered
            if(user == null){
                logger.error("No such user was found in the database");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Putting calculation into db
            calcRepo.insertCalculationWithUser(user, calc);
            logger.info("Managed to post calculation with user");
            return new ResponseEntity<>("Calculation was successfully added.", HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Exception while posting calculation with user: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

/*{
    "fullName": "Hanne Banne",
    "email": "hanne@mail.com"
}*/

/*{
    "expression": "2+2",
    "result": "4",
    "userId": 1
},*/

/*

    [
            {
            "fullName": "Hanne Banne",
            "email": "hanne@mail.com"
            },
            {
            "expression": "6+4",
            "result": "10"
            }
            ]*/
