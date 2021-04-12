package com.tamas.springapp;

import com.tamas.codegen.controllers.PetApi;
import com.tamas.codegen.controllers.PetApiController;
import com.tamas.codegen.model.Pet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;


@RestController
public class PetController extends PetApiController{
    

    public PetController(NativeWebRequest request) {
        super(request);
        //TODO Auto-generated constructor stub
    }

    @Override
    @GetMapping("/pet/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable("petId") Long petId) {
        
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Mici");

        return new ResponseEntity<Pet>(pet,HttpStatus.OK);
    }


}
