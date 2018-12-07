package com.systelab.reactive.controller;

import com.systelab.reactive.model.patient.Patient;
import com.systelab.reactive.repository.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Api(value = "Patient", description = "API for patient management", tags = {"Patient"})
@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization", allowCredentials = "true")
@RequestMapping(value = "/reactive/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "Get all Patients", notes = "")
    @GetMapping("patients")
    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @ApiOperation(value = "Get Patient", notes = "")
    @GetMapping("patients/{uid}")
    public Mono<Patient> getPatient(@PathVariable("id") String patientId) {
        return patientRepository.findById(patientId);

    }

    @ApiOperation(value = "Create a Patient", notes = "")
    @PostMapping("patients/patient")
    public Mono<Patient> createPatient(@RequestBody @ApiParam(value = "Patient", required = true) @Valid Patient p) {
        return this.patientRepository.save(p);
    }


    @ApiOperation(value = "Create or Update (idempotent) an existing Patient", notes = "")
    @PutMapping("patients/{id}")
    public Mono<Patient> updatePatient(@PathVariable("id") String patientId, @RequestBody @ApiParam(value = "Patient", required = true) @Valid Patient p) {
        return this.patientRepository.findById(patientId).doOnSuccess(findPatient -> {
            findPatient.setName(p.getName());
            findPatient.setSurname(p.getSurname());
            findPatient.setEmail(p.getEmail());
            this.patientRepository.save(findPatient).subscribe();
        });
    }

    @ApiOperation(value = "Delete a Patient", notes = "")
    @DeleteMapping("patients/{uid}")
    public Mono<Boolean> removePatient(@PathVariable("id") String patientId) {
        return this.patientRepository.findById(patientId).doOnSuccess(patient -> {
            patient.setDelete(true);
            this.patientRepository.save(patient).subscribe();
        }).flatMap(blog -> Mono.just(Boolean.TRUE));
    }
}