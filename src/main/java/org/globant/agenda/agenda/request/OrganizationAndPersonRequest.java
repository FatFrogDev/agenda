package org.globant.agenda.agenda.request;

import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.model.Person;

public class OrganizationAndPersonRequest {

    private Organization organization;
    private Person person;

    
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

    
}
