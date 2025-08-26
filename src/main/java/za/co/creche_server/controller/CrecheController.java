package za.co.creche_server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.model.Person;
import za.co.creche_server.service.PostgresService;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/creche_service/v1")
public class CrecheController {
    private final PostgresService service;

    public CrecheController(PostgresService service) {
        this.service = service;
    }


    @GetMapping("/creche/names")
    public List<String> getNames() {
        return service.findAllNames();
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        log.info("User Called Hello World Controller");
        return "Hello there Sunshine";
    }

    @PostMapping("/addNewClient")
    public ResponseEntity<Object> addNewClient(@RequestBody Person person){
        log.info("User Called Add New Client Controller");
        /**
         * Add your logic here
         */
        log.info("Replied with successful");
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(person);
    }

    @PostMapping("/addNewClient2")
    public ResponseEntity<String> addNewClient2(@RequestBody Person person){
        log.info("User Called Add New Client 2 Controller");
        /**
         * Add your logic here
         */

        String returnObject = "New Client "+person.getFirstName()+" with ID number: "+person.getIdNumber()+" successfully Added";
        log.info("Replied with: "+returnObject);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(returnObject);
    }
}
