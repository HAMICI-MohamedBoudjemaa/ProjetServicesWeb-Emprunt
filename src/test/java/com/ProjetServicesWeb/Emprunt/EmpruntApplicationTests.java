package com.ProjetServicesWeb.Emprunt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmpruntApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmpruntRepository repository;

	@Autowired
	private EmpruntController empruntController;

	@Test
	public void testFindById() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);

		ResponseEntity<EntityModel<Emprunt>> entityEmprunt = empruntController.findById(2);
		Emprunt emprunt = entityEmprunt.getBody().getContent();
		assertTrue(emprunt.getId()==2);

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

	@Test
	public void testFindByIsbn() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);


		List<Emprunt> emprunt = empruntController.findBy("isbn2",null,null,null).getBody().getContent().stream().collect(Collectors.toList());
		assertEquals(cree2.getIsbn(), emprunt.get(0).getIsbn());

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");
	}

	@Test
	public void testFindByIdLecteur() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);


		List<Emprunt> emprunt = empruntController.findBy(null,2,null, null).getBody().getContent().stream().collect(Collectors.toList());
		assertEquals(cree2.getIdLecteur(), emprunt.get(0).getIdLecteur());

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");
	}

	@Test
	public void testFindByDatePret() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);


		List<Emprunt> emprunt = empruntController.findBy(null,null,new Date("24/03/2020"), null).getBody().getContent().stream().collect(Collectors.toList());
		assertEquals(cree2.getDatePret(), emprunt.get(0).getDatePret());

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");
	}

	@Test
	public void testFindByDateRetour() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);


		List<Emprunt> emprunt = empruntController.findBy(null,null,null, new Date("28/05/2020")).getBody().getContent().stream().collect(Collectors.toList());
		assertEquals(cree.getDateRetour(), emprunt.get(0).getDateRetour());

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");
	}

	@Test
	public void testFindAll() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);


		List<Emprunt> emprunt = empruntController.findBy(null,null,null,null).getBody().getContent().stream().collect(Collectors.toList());
		assertTrue(emprunt.size()>=2);

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

	@Test
	public void testModify() {
		ResponseEntity<EntityModel<Emprunt>> cree = empruntController.create(new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020")));
		assertTrue(cree.getBody().getContent().getIdLecteur()!=125);
		Emprunt cree2 = cree.getBody().getContent();
		cree2.setIdLecteur(125);
		Emprunt emprunt = empruntController.modify(cree2).getBody().getContent();
		assertTrue(cree.getBody().getContent().getIdLecteur()==125);

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

	@Test
	public void testRetourner() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		empruntController.create(cree2);
		Date dateRetour = new Date("10/10/2020");
		Emprunt emprunt = empruntController.retourner(2, dateRetour).getBody().getContent();
		assertEquals(dateRetour, emprunt.getDateRetour());

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

	@Test
	public void testDelete() {
		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);

		int result = empruntController.delete(1).getBody();
		assertEquals(1, result);

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

}
