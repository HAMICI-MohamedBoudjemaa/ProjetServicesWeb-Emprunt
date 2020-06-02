package com.ProjetServicesWeb.Emprunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntServiceProviderImpl implements EmpruntServiceProvider{

    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public Emprunt create(Emprunt emprunt) {
        String lecteurApi = "http://192.168.1.81:8080/lecteurs/";
        RestTemplate restTemplateLecteur = new RestTemplate();
        String uriLecteur = String.format("%s%s", lecteurApi, emprunt.getIdLecteur());
        String livreApi = "http://192.168.1.81:8090/livres/";
        RestTemplate restTemplateLivre = new RestTemplate();
        String uriLivre = String.format("%s%s", livreApi, emprunt.getIsbn());

        ResponseEntity<Lecteur> responseEntityLecteur = restTemplateLecteur.exchange(uriLecteur, HttpMethod.GET, null, Lecteur.class);
        ResponseEntity<Livre> responseEntityLivre = restTemplateLivre.exchange(uriLivre, HttpMethod.GET, null, Livre.class);
        if (responseEntityLecteur.getBody().getId()!=0 && responseEntityLivre.getBody().getIsbn()!=null) {
            return empruntRepository.save(emprunt);
        }
        return (new Emprunt());
    }

    @Override
    public Emprunt retourner(Integer id, Date dateRetour) {
        Optional<Emprunt> emprunt = findById(id);
        if(emprunt.isPresent())
        {
            Emprunt myEmprunt = emprunt.get();
            myEmprunt.setDateRetour(dateRetour);
            myEmprunt.setRendu("oui");
            return empruntRepository.save(myEmprunt);
        }
        return (new Emprunt());
    }

    @Override
    public Optional<Emprunt> findById(Integer id) {
        return empruntRepository.findById(id);
    }

    @Override
    public List<Emprunt> findByIsbn(String isbn) {
        return empruntRepository.findByIsbn(isbn);
    }

    @Override
    public List<Emprunt> findByIdLecteur(Integer id) {
        return empruntRepository.findByIdLecteur(id);
    }

    @Override
    public List<Emprunt> findByDatePret(Date datePret) {
        return empruntRepository.findByDatePret(datePret);
    }

    @Override
    public List<Emprunt> findByDateRetour(Date dateRetour) {
        return empruntRepository.findByDateRetour(dateRetour);
    }

    @Override
    public List<Emprunt> getAll() {
        return empruntRepository.findAll();
    }

    @Override
    public Emprunt modify(Emprunt emprunt) {
        if(findById(emprunt.getId())!=null)
        {
            return empruntRepository.save(emprunt);
        }
        return new Emprunt();
    }

    @Override
    public int delete(Integer id) {
        Optional<Emprunt> emprunt = findById(id);
        if(emprunt.isPresent())
        {
            empruntRepository.delete(emprunt.get());
            return 1;
        }
        return 0;
    }

    @Override
    public List<Emprunt> findByRendu(String rendu) {
        return empruntRepository.findByRendu(rendu);
    }
}
