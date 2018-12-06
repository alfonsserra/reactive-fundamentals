package com.systelab.reactive.model.patient;

import com.systelab.reactive.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient extends ModelBase implements Serializable {

    private static final Patient empty = new Patient();

    @Size(min = 1, max = 255)
    private String surname;

    @Size(min = 1, max = 255)
    private String name;

    private String email;

    private Date dob;

    @Embedded
    private Address address;

    public static Patient empty() {
        return empty;
    }
}