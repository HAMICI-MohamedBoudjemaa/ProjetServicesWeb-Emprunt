package com.ProjetServicesWeb.Emprunt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpruntServiceProvider {
    Emprunt create(Emprunt emprunt);
    Optional<Emprunt> findById(Integer id);
    List<Emprunt> findByIsbn(String isbn);
    List<Emprunt> findByIdLecteur(Integer id);
    List<Emprunt> findByDatePret(Date datePret);
    List<Emprunt> findByDateRetour(Date dateRetour);
    Emprunt modify(Emprunt emprunt);
    int delete(Integer id);

}
