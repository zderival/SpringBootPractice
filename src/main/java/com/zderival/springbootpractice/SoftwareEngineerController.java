package com.zderival.springbootpractice;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/* @RestController - Marks this class as a REST API controller.
Spring automatically return values into JSON responses */
@RestController
/* @RequestMapping -  Base URL for all endpoints in this controller.
Every route must start with: /api/v1/software-engineers */
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    // Service dependency (your access to business logic)
    // Controller does NOT handle logic directly. It goes to the SoftwareEngineerService.
    private final SoftwareEngineerService softwareEngineerService;

    // Constructor
    // Spring automatically initializes the SoftwareEngineerService
    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    // Retrieves all SoftwareEngineer records from the database
    // URL: GET /api/v1/software-engineers
    @GetMapping
    public List<SoftwareEngineer> getEngineers(){
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    // Retrieves a single SoftwareEngineer by ID
    // URL: GET /api/v1/software-engineers/{id}
    // @PathVariable - Retrieves the variable specified from (Insert command)Mapping.
    // In this case, id is the variable.
    @GetMapping("{id}")
    public SoftwareEngineer getEngineersById(@PathVariable Integer id ){
        return softwareEngineerService.getSoftwareEngineersById(id);
    }

    // Creates a new SoftwareEngineer in the database
    // @RequestBody - turns JSON request body into a Java object
    // URL: POST /api/v1/software-engineers
    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    // Deletes a SoftwareEngineer by ID
    // URL: DELETE /api/v1/software-engineers/{id}
    @DeleteMapping("{id}")
    public void deleteSoftwareEngineer(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineer(id);
    }

    // Updates an existing SoftwareEngineer by ID
    // Takes updated data from request body and passes it to service layer
    // URL: PUT /api/v1/software-engineers/{id}
    @PutMapping("{id}")
    public void updateSoftwareEngineer(
            @PathVariable Integer id,
            @RequestBody SoftwareEngineer softwareEngineer
    ){
        softwareEngineerService.updateSoftwareEngineer(id,
                softwareEngineer.getName(),
                softwareEngineer.getTechStack()
        );
    }
}