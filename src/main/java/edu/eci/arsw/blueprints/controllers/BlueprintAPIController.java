/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PageRanges;

/**
 *
 * @author hcadavid
 */
@Service
@RestController
@RequestMapping(value = "/v1/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bp= null;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrints() {
        try {;
            return new ResponseEntity<>(bp.getAllBlueprints(),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error in the method getAllBluePrints", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getAuthor(@PathVariable ("author") String author) {
        try {
            return new ResponseEntity<>(bp.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No author found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<?> getBlueprintAuthor(@PathVariable ("author") String author, @PathVariable String bpname) {
        try {
            return new ResponseEntity<>(bp.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No author or Blueprint found", HttpStatus.NOT_FOUND);
        }
    }


}

