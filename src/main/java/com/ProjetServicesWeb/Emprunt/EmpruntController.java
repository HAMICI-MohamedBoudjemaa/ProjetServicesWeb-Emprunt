package com.ProjetServicesWeb.Emprunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {
    @Autowired
    EmpruntServiceProvider empruntServiceProvider;

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Emprunt>> findById(@PathVariable("id") Integer id){
        Optional<Emprunt> cree = empruntServiceProvider.findById(id);
        Emprunt Emprunt = cree.orElse(new Emprunt());
        Link lien = linkTo(methodOn(EmpruntController.class).findById(id)).withSelfRel();
        return new ResponseEntity<>(new EntityModel<>(Emprunt, lien), CREATED);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Emprunt>> findBy(@RequestParam(value = "isbn", required = false) String isbn,
                                                           @RequestParam(value = "idLecteur", required = false) Integer idLecteur,
                                                           @RequestParam(value = "datePret", required = false) Date datePret,
                                                           @RequestParam(value = "dateRetour", required = false) Date dateRetour
    ){
        List<Emprunt> cree = null;
        if(isbn!=null){cree = empruntServiceProvider.findByIsbn(isbn);}
        else if(idLecteur!=null){cree = empruntServiceProvider.findByIdLecteur(idLecteur);}
        else if(datePret!=null){cree = empruntServiceProvider.findByDatePret(datePret);}
        else if(dateRetour!=null){cree = empruntServiceProvider.findByDateRetour(dateRetour);}
        else{cree = empruntServiceProvider.getAll();}
        Link lien = linkTo(methodOn(EmpruntController.class).findBy(isbn, idLecteur, datePret, dateRetour)).withSelfRel();
        return new ResponseEntity<CollectionModel<Emprunt>>(new CollectionModel<>(cree, lien), CREATED);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Emprunt>> create(@RequestBody Emprunt emprunt){
        Emprunt cree = empruntServiceProvider.create(emprunt);
        Link lien = linkTo(methodOn(EmpruntController.class).findById(cree.getId())).withSelfRel();
        return new ResponseEntity<>(new EntityModel<>(cree, lien), CREATED);
    }

    @PutMapping
    public ResponseEntity<EntityModel<Emprunt>> modify(@RequestBody Emprunt emprunt){
        Emprunt cree = empruntServiceProvider.modify(emprunt);
        Link lien = linkTo(methodOn(EmpruntController.class).findById(cree.getId())).withSelfRel();
        return new ResponseEntity<>(new EntityModel<>(cree, lien), CREATED);
    }

    @PutMapping("{id,dateRetour}")
    public ResponseEntity<EntityModel<Emprunt>> retourner(@RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "dateRetour", required = true) Date dateRetour){
        Emprunt cree = empruntServiceProvider.retourner(id, dateRetour);
        Link lien = linkTo(methodOn(EmpruntController.class).findById(cree.getId())).withSelfRel();
        return new ResponseEntity<>(new EntityModel<>(cree, lien), CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Integer id){
        int deleted = empruntServiceProvider.delete(id);
        return new ResponseEntity<Integer>(deleted, CREATED);
    }

}
