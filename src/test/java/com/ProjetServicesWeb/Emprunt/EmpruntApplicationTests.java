package com.ProjetServicesWeb.Emprunt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
	public void testCreate(){

		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

		ResponseEntity<EntityModel<Emprunt>> cree = empruntController.create(new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020")));
		assertTrue(cree.getBody().getContent().getId()!=0);
		ResponseEntity<EntityModel<Emprunt>> cree2 = empruntController.create(new Emprunt("isbn152",255, new Date("24/03/2020"), null));
		assertEquals(0,cree2.getBody().getContent().getId());
	}

	@Test
	public void testFindById() {
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

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
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);
		Emprunt cree2 = new Emprunt("isbn2",2, new Date("24/03/2020"), null);
		Emprunt emprunt = empruntController.create(cree2).getBody().getContent();
		Date dateRetour = new Date("10/10/2020");
		Emprunt emprunt2 = empruntController.retourner(emprunt.getId(), dateRetour).getBody().getContent();
		assertTrue(emprunt2.getId() == emprunt.getId() && emprunt2.getDateRetour().equals(dateRetour));

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

	@Test
	public void testDelete() {
		RestTemplate restTemplateLecteur = new RestTemplate();
		String uriLecteur = "http://localhost:8080/lecteurs";
		RestTemplate restTemplateLivre = new RestTemplate();
		String uriLivre = "http://localhost:8090/livres";

		Lecteur lecteur = new Lecteur("masculin","nom1", "prenom1", new Date("22/05/1995"), "adresse1");
		Livre livre = new Livre("isbn1","auteur1", "titre1", "editeur1", "edition1");
		Lecteur lecteur2 = new Lecteur("feminin","nom2", "prenom2", new Date("24/03/1996"), "adresse2");
		Livre livre2 = new Livre("isbn2","auteur2", "titre2", "editeur2", "edition2");

		HttpEntity<Lecteur> requestEntityLecteur = new HttpEntity<>(lecteur, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre = new HttpEntity<>(livre, new HttpHeaders());
		HttpEntity<Lecteur> requestEntityLecteur2 = new HttpEntity<>(lecteur2, new HttpHeaders());
		HttpEntity<Livre> requestEntityLivre2 = new HttpEntity<>(livre2, new HttpHeaders());

		ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre, Livre.class);
		ResponseEntity<Lecteur> responseEntityLecteur2 = restTemplateLecteur.exchange(uriLecteur, HttpMethod.POST, requestEntityLecteur2, Lecteur.class);
		ResponseEntity<Livre> responseEntityLivre2 = restTemplateLivre.exchange(uriLivre, HttpMethod.POST, requestEntityLivre2, Livre.class);

		Emprunt cree = new Emprunt("isbn1",1, new Date("22/05/2020"), new Date("28/05/2020"));
		empruntController.create(cree);

		int result = empruntController.delete(1).getBody();
		assertEquals(1, result);

		//assertThat(books).extracting(com.ProjetServicesWeb.Emprunt.Emprunt::getName).containsOnly("C++");

	}

}
