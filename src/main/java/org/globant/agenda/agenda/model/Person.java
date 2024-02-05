package org.globant.agenda.agenda.model;


import java.util.List;
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
    @Transient
    @OneToMany
    private List<Cellphone> cellphones;
    
    @Column(columnDefinition = "boolean default false")
    private boolean isManager;

    private String orderName(String str){
            char[] name = str.toLowerCase().toCharArray();
            boolean foundWord = false;
            for (int index = 0; index < name.length; index++) {
                if(!foundWord && Character.isLetter(name[index])){
                    name[index] = Character.toUpperCase(name[index]);
                    foundWord = true;
                }else if (Character.isWhitespace(name[index])){
                    foundWord = false;
                }
            }
            return String.valueOf(str);
    }

    public Person(){}   

    public Person(Integer id, @Size(min = 1, max = 20) @NotEmpty String name,
            @Size(min = 1, max = 20) @NotEmpty String lastname, @Size(min = 5, max = 70) @NotEmpty String address,
            @NotNull @Size(min = 1) List<Cellphone> cellphones, boolean isManager) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.cellphones = cellphones;
        this.isManager = isManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return orderName(name);
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
    
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public List<Cellphone> getCellphones() {
        return List.copyOf(cellphones);
    }

    public void setCellphones(List<Cellphone> cellphones) {
        this.cellphones = cellphones;
    }    
}
