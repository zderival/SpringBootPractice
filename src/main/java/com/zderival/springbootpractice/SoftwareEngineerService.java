package com.zderival.springbootpractice;

import org.springframework.stereotype.Service;

import java.util.List;
/* Services are what does the business logic and method handling.
// They converse information given from the user,
// and works alongside repositories and API's to generate information for the
 Controller to give back to the user */

// @Serivce - Lets Spring know that the class is a Service and
// is meant to be used for business logic and Spring manages it.
@Service
public class SoftwareEngineerService {
    // Your access to the Entity's Repository.
    private final SoftwareEngineerRepository softwareEngineerRepository;

    // Constructor injection (preferred over field injection)
    // Spring passes the repository bean here when creating this service
    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    // Removes a SoftwareEngineer from the database using their ID
    public void deleteSoftwareEngineer(Integer id){
        softwareEngineerRepository.deleteById(id);
    }

    // Retrieves all SoftwareEngineer records from the database
    public List<SoftwareEngineer> getAllSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    // Saves a new SoftwareEngineer entity into the database
    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    // Finds a SoftwareEngineer by ID
    // If not found, throws a custom exception handled globally
    public SoftwareEngineer getSoftwareEngineersById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new SoftwareEngineerNotFoundException(id));
    }

    // First retrieves the existing entity from the database
    // Then updates its fields and saves the modified version back
    public void updateSoftwareEngineer(Integer id, String name, String techStack){
        SoftwareEngineer softwareEngineer = getSoftwareEngineersById(id);

        // Updating fields of the existing entity
        softwareEngineer.setName(name);
        softwareEngineer.setTechStack(techStack);

        // Saving updated entity back to the database
        softwareEngineerRepository.save(softwareEngineer);
    }
}