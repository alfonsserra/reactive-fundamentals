package com.systelab.reactive.model.patient;

import com.systelab.reactive.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;

@Document

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends ModelBase {

    private static final Patient empty = new Patient();

    @Size(min = 1, max = 255)
    @TextIndexed
    private String surname;

    @Size(min = 1, max = 255)
    private String name;

    private String email;

    private Date dob;

    private Address address;

    public static Patient empty() {
        return empty;
    }
}