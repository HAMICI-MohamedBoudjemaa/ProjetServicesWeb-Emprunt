package com.ProjetServicesWeb.Emprunt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Livre {
    @Id
    private String isbn;
    private String auteur;
    private String titre;
    private String editeur;
    private String edition;

    public Livre(){}

    public Livre(String isbn, String auteur, String titre, String editeur, String edition) {
        this.isbn = isbn;
        this.auteur = auteur;
        this.titre = titre;
        this.editeur = editeur;
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }



    @Override
    public int hashCode() {
        return Objects.hash(isbn, auteur, titre, editeur, edition);
    }

    @Override
    public String toString() {
        return "com.ProjetServicesWeb.Livre.Livre{" +
                "isbn='" + isbn + '\'' +
                ", auteur='" + auteur + '\'' +
                ", titre='" + titre + '\'' +
                ", editeur='" + editeur + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }
}
