package org.globant.agenda.agenda.request;

import java.util.Collection;

import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Person;

public class PersonAndCellphoneRequest {
        
    private Person person;
    private Collection<Cellphone> cellphones;

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Collection<Cellphone> getCellphones() {
        return cellphones;
    }
    public void setCellphones(Collection<Cellphone> cellphones) {
        this.cellphones = cellphones;
    }

}
