package com.ProjetServicesWeb.Emprunt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue
    private int id;
    private String isbn;
    private int idLecteur;
    private Date datePret;
    private Date dateRetour;

    public Emprunt(){

    }

    public Emprunt(String isbn, int idLecteur, Date datePret, Date dateRetour) {
        this.isbn = isbn;
        this.idLecteur = idLecteur;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getIdLecteur() {
        return idLecteur;
    }

    public void setIdLecteur(int idLecteur) {
        this.idLecteur = idLecteur;
    }

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    }
