package com.systelab.reactive.controller;

import com.systelab.reactive.model.patient.Patient;
import com.systelab.reactive.repository.PatientNotFoundException;
import com.systelab.reactive.repository.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        List<Patient> patients=patientRepository.findAll();
        Flux<Patient> fluxPatients=Flux.fromIterable(patients);
        return fluxPatients;
    }

    @ApiOperation(value = "Get Patient", notes = "")
    @GetMapping("patients/{uid}")
    public Mono<Patient> getPatient(@PathVariable("uid") UUID patientId) {
        Optional<Patient> patient= patientRepository.findById(patientId);
        return Mono.just(patient.orElse(Patient.empty()));

    }

    @ApiOperation(value = "Create a Patient", notes = "")
    @PostMapping("patients/patient")
    public Mono<Boolean> createPatient(@RequestBody @ApiParam(value = "Patient", required = true) @Valid Patient p) {
        Patient patient = this.patientRepository.save(p);
        return Mono.just(Boolean.TRUE);
    }


    @ApiOperation(value = "Create or Update (idempotent) an existing Patient", notes = "")
    @PutMapping("patients/{uid}")
    public  Mono<Boolean> updatePatient(@PathVariable("uid") UUID patientId, @RequestBody @ApiParam(value = "Patient", required = true) @Valid Patient p) {
        return this.patientRepository
                .findById(patientId)
                .map(existing -> {
                    p.setId(patientId);
                    Patient patient = this.patientRepository.save(p);
                    return Mono.just(Boolean.TRUE);
                }).orElseThrow(() -> new PatientNotFoundException(patientId));
    }


    @ApiOperation(value = "Delete a Patient", notes = "")
    @DeleteMapping("patients/{uid}")
    public  Mono<Boolean> removePatient(@PathVariable("uid") UUID patientId) {
        return this.patientRepository.findById(patientId)
                .map(c -> {
                    patientRepository.delete(c);
                    return Mono.just(Boolean.TRUE);
                }).orElseThrow(() -> new PatientNotFoundException(patientId));
    }
}