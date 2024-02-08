package org.globant.agenda.agenda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Organization;
import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class CellphoneController {

    @Autowired
    CellphoneRepository cellphoneRepository;

    @GetMapping("/cellphones")
    public ResponseEntity<List<Cellphone>> listCellphones() {
        List<Cellphone> cellphones = cellphoneRepository.findAll();

        if (cellphones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cellphones, HttpStatus.OK);
        }
    }
    
    @SuppressWarnings("null")
    @GetMapping("/cellphones/{id}")
    public ResponseEntity<Optional<Cellphone>> listCellphonesById(@PathVariable @NotNull Integer id) {
        Optional<Cellphone> cellphone = cellphoneRepository.findById(id);

        if (cellphone.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(cellphone);
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/cellphone/{id}")
    public ResponseEntity<Cellphone> updateCellphone(@PathVariable @NonNull Integer id, @RequestBody Cellphone cellphoneUpdate) {
        Cellphone existingCellphone = cellphoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La organización con el siguiente ID no existe: " + id));

        // Update the atributes necessaries without the "Person"
        existingCellphone.setNumber(cellphoneUpdate.getNumber());

        // Save the entity updated
        Cellphone cellphoneUpdated = cellphoneRepository.save(existingCellphone);        

        return ResponseEntity.ok(cellphoneUpdated);
    }
}
