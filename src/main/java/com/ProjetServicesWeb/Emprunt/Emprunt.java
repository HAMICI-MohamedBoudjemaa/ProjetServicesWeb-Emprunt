package com.ProjetServicesWeb.Emprunt;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue
    private int id;
    private String isbn;
    private int idLecteur;

    @Temporal(TemporalType.DATE)
    private Date datePret;
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    private String rendu;

    public Emprunt(){

    }

    public Emprunt(String isbn, int idLecteur, Date datePret, Date dateRetour, String rendu) {
        this.isbn = isbn;
        this.idLecteur = idLecteur;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
        this.rendu = rendu;

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

    public String getRendu() {
        return rendu;
    }

    public void setRendu(String rendu) {
        this.rendu = rendu;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", idLecteur=" + idLecteur +
                ", datePret=" + datePret +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
