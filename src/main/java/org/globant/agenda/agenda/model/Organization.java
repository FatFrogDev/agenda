package org.globant.agenda.agenda.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3,max = 20)
    @NotNull
    private String enterpriseName;
    @Size(min = 3,max = 20)
    @NotNull
    private String city;
    @Size(min = 3,max = 20)
    @NotNull
    private String domain;
    
    // person/manager fk id
    private long manager;
}
