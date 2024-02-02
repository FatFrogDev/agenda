package org.globant.agenda.agenda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @NotNull //i change "NotEmpty" for "NotNull"
    private Person person;

    public Organization() {
    }

    public Organization(Integer id, @Size(min = 3, max = 20) @NotEmpty String enterpriseName,
            @Size(min = 3, max = 30) @NotEmpty String city, @Size(max = 30) @NotEmpty String domain,
            @NotEmpty Person person) {
        this.id = id;
        this.enterpriseName = enterpriseName;
        this.city = city;
        this.domain = domain;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
