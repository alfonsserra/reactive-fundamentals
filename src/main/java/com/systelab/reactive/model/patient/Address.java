package com.systelab.reactive.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String coordinates;
    private String street;
    private String city;
    private String zip;
}
