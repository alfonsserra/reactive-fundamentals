package com.systelab.reactive.repository;

import com.systelab.reactive.model.patient.Patient;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PatientRepository extends ReactiveCrudRepository<Patient, String> {

    Mono<Patient> findById(@Param("id") String id);

}