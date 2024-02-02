package org.globant.agenda.agenda.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Size(min = 1,max = 20)
    @NotEmpty
    private String name;

    @Size(min = 1,max = 20)
    @NotEmpty
    private String lastname;
    @Size(min = 5,max = 70)

    @NotEmpty
    private String address;
    
    @NotNull
    @Size(min = 1)
    private String[] cellphone;
    
    @Column(columnDefinition = "boolean default false")
    private boolean isManager;
    
    public Person(){}
    

    public Person(Integer id, @Size(min = 1, max = 20) @NotEmpty String name,
            @Size(min = 1, max = 20) @NotEmpty String lastname, @Size(min = 5, max = 70) @NotEmpty String address,
            @NotEmpty String[] cellphone, boolean isManager) {
        this.id = id;
        this.name = name.toLowerCase();
        this.lastname = lastname.toLowerCase();
        this.address = address;
        this.cellphone = cellphone;
        this.isManager = isManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name.toLowerCase();
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname.toLowerCase();
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String[] getCellphone() {
        return cellphone;
    }
    public void setCellphone(String[] cellphone) {
        this.cellphone = cellphone;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }    
}
