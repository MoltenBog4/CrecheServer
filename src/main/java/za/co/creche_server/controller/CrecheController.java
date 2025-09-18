package za.co.creche_server.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.model.Person;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/creche_service/v1")
public class CrecheController {

    private Logger logger;

    @PostConstruct
    private void initiate() {
        logger = LogManager.getLogger("CrecheServerApplicationControllerLogger");
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        logger.info("User Called Hello World Controller");
        return "Hello there Sunshine";
    }

    @PostMapping("/addNewClient")
    public ResponseEntity<Object> addNewClient(@RequestBody Person person){
        logger.info("User Called Add New Client Controller");
        /**
         * Add your logic here
         */
        logger.info("Replied with successful");
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(person);
    }

    @PostMapping("/addNewClient2")
    public ResponseEntity<String> addNewClient2(@RequestBody Person person){
        logger.info("User Called Add New Client 2 Controller");
        /**
         * Add your logic here
         */

        String returnObject = "New Client "+person.getFirstName()+" with ID number: "+person.getIdNumber()+" successfully Added";
        logger.info("Replied with: "+returnObject);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(returnObject);
    }
}
