package Models;


import java.util.Date;

public class Projets {
    int id;
    String description;
    Date dateDebut;
    Date dateFin;
    int idEquipe;
    String nom;

    public Projets(int id, String description, Date dateDebut, Date dateFin, int idEquipe, String nom) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idEquipe = idEquipe;
        this.nom = nom;
    }



    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public String getNom() { return nom; }

    public Date getDateFin() {
        return dateFin;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setNom(String nom) { this.nom = nom; }
}


