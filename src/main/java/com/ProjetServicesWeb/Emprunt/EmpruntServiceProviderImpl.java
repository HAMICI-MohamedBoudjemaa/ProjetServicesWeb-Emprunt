package com.ProjetServicesWeb.Emprunt;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmpruntServiceProviderImpl implements EmpruntServiceProvider{

    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public Emprunt create(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
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
    public Emprunt modify(Emprunt emprunt) {
        if(findById(emprunt.getId())!=null)
        {
            return empruntRepository.save(emprunt);
        }
        return null;
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
}
