package org.globant.agenda.agenda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3,max = 20)
    @NotEmpty
    private String enterpriseName;
    
    @Size(min = 3, max=30)
    @NotEmpty
    private String city;
    
    @Size(max=30)
    @NotEmpty
    private String domain;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    @NotEmpty
    private Person person;
}
