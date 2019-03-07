package com.systelab.seed.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.systelab.seed.model.allergy.Allergy;
import com.systelab.seed.service.AllergyService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Api(value = "Allergy", description = "API for allergy management", tags = {"Allergy"})
@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization", allowCredentials = "true")
@RequestMapping(value = "/seed/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllergyController {

    private final AllergyService allergyService;

    @Autowired
    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @ApiOperation(value = "Get all Allergies", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("allergies")
    public ResponseEntity<Page<Allergy>> getAllAllergies(Pageable pageable) {
        return ResponseEntity.ok(this.allergyService.getAllAllergies(pageable));
    }

    @ApiOperation(value = "Get Allergy", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("allergies/{uid}")
    public ResponseEntity<Allergy> getAllergy(@PathVariable("uid") UUID id) {
        return ResponseEntity.ok(this.allergyService.getAllergy(id));
    }

    @ApiOperation(value = "Create an Allergy", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping("allergies/allergy")
    public ResponseEntity<Allergy> createAllergy(@RequestBody @ApiParam(value = "Allergy", required = true) @Valid Allergy a) {
        Allergy allergy = this.allergyService.createAllergy(a);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/allergies/{id}").buildAndExpand(allergy.getId()).toUri();
        return ResponseEntity.created(uri).body(allergy);
    }


    @ApiOperation(value = "Create or Update (idempotent) an existing Allergy", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("allergies/{uid}")
    public ResponseEntity<Allergy> updateAllergy(@PathVariable("uid") UUID id, @RequestBody @ApiParam(value = "Allergy", required = true) @Valid Allergy a) {
        Allergy allergy = this.allergyService.updateAllergy(id, a);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(allergy);
    }


    @ApiOperation(value = "Delete a Allergy", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("allergies/{uid}")
    public ResponseEntity removeAllergy(@PathVariable("uid") UUID id) {
        this.allergyService.removeAllergy(id);
        return ResponseEntity.noContent().build();
    }
}