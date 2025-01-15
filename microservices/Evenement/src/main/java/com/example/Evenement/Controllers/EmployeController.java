package com.example.Evenement.Controllers;

import com.example.Evenement.Queries.findcolleaguesWithSameInterets;
import com.example.Evenement.QueryModels.Employe;
import com.example.Evenement.commandes.CreateEmployeCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

        private final CommandGateway commandGateway;
        private final QueryGateway queryGateway;

        @Autowired
        public EmployeController(CommandGateway commandGateway, QueryGateway queryGateway) {
            this.commandGateway = commandGateway;
            this.queryGateway = queryGateway;
        }

        // Endpoint to create a new employee
        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        public String createEmploye(@RequestBody CreateEmployeCommand createEmployeCommand){
            String employeId = String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE); // Generate unique ID
             createEmployeCommand.setEmployeId(employeId);
             commandGateway.sendAndWait(createEmployeCommand);
            return "Employé created with ID: " + employeId;
        }


    @GetMapping("/colleagues")
    public List<Employe> findColleaguesWithSameInterests(
            @RequestParam (value="id") String employeId,
            @RequestParam(value="interests") List<String> interests) {
        return queryGateway.query(new findcolleaguesWithSameInterets(employeId, interests), ResponseTypes.multipleInstancesOf(Employe.class)).join();
    }
    }

