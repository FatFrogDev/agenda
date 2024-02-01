package org.globant.agenda.agenda.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1,max = 20)
    @NotNull
    private String name;
    @Size(min = 1,max = 20)
    @NotNull
    private String lastname;
    @Size(min = 5,max = 70)
    @NotNull
    private String address;
    @NotNull
    private String[] cellphone;
    @NotNull
    @Column(columnDefinition = "default false")
    private boolean isManager;
    
    public Person(){}

    public Person(String name, String lastname, String address, String[] cellphone, boolean isManager) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.cellphone = cellphone;
        this.isManager = isManager;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
