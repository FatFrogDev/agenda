package org.globant.agenda.agenda.request;

import java.util.ArrayList;
import java.util.Collection;

import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.model.Person;

public class OrganizationAndPersonAndCellphonesRequest {

    private Organization organization;
    private Person person;
    private Collection<Cellphone> cellphone = new ArrayList<>();
    

    
    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Collection<Cellphone> getCellphone() {
        return cellphone;
    }
    public void setCellphone(Collection<Cellphone> cellphone) {
        this.cellphone = cellphone;
    }
    
    
}
