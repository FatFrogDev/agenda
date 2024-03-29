package org.globant.agenda.agenda.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Schema(description = "This model holds the person's number and its ID")
public class Cellphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Person person;

    @Column(unique = true)
    @NotEmpty
    private String number;

    public Cellphone() {        
    }

    public Cellphone(Integer id, Person person, @Size(min=1) String number) {
        this.id = id;
        this.person = person;
        this.number = number;
    }

    public Cellphone(Person person, @Size(min=1) String number) {
        this.person = person;
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
