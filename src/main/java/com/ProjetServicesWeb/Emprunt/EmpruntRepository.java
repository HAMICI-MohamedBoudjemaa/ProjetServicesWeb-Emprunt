package com.ProjetServicesWeb.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer>{
    List<Emprunt> findByIsbn(final String isbn);
    List<Emprunt> findByIdLecteur(final Integer idLecteur);
    List<Emprunt> findByDatePret(final Date datePret);
    List<Emprunt> findByDateRetour(final Date dateRetour);

}
