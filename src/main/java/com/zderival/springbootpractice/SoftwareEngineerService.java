package com.zderival.springbootpractice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public void deleteSoftwareEngineer(Integer id){
        softwareEngineerRepository.deleteById(id);
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }


    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineersById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(()-> new SoftwareEngineerNotFoundException(id));
    }

    public void updateSoftwareEngineer(Integer id, String name, String techStack){
        SoftwareEngineer softwareEngineer =  getSoftwareEngineersById(id);
        softwareEngineer.setName(name);
        softwareEngineer.setTechStack(techStack);
        softwareEngineerRepository.save(softwareEngineer);
    }
}

