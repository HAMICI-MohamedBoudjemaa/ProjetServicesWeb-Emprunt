import java.util.Date;
import java.util.List;

public class Emprunt {
    private static int nbEmprunts = 0;
    private int id;
    private String isbn;
    private int idLecteur;
    private Date datePret;
    private Date dateRetour;

    public Emprunt(String isbn, int idLecteur, Date datePret, Date dateRetour) {
        nbEmprunts++;
        this.id = nbEmprunts;
        this.isbn = isbn;
        this.idLecteur = idLecteur;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
    }

    public static int getNbEmprunts() {
        return nbEmprunts;
    }

    public static void setNbEmprunts(int nbEmprunts) {
        Emprunt.nbEmprunts = nbEmprunts;
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

    public void ajouterEmprunt(String isbn, int idLecteur, Date datePret, Date dateRetour){
        Emprunt emprunt = new Emprunt(isbn, idLecteur, datePret, dateRetour);
    }

    public Emprunt recupererEmprunt(String isbn){

    }

    public List<Emprunt> listerEmprunt(){

    }

    public void modifierEmprunt(String isbn, int idLecteur, Date datePret, Date dateRetour){

    }

    public void supprimerEmprunt(){

    }
}
