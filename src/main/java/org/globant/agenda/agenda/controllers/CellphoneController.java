package org.globant.agenda.agenda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.repository.CellphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
}
