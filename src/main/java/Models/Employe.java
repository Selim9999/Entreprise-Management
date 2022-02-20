package Models;

public class Employe extends Salarie {
    int nbrHeure;
    int prixHeure;
    int idEquipe;
    String nom1;

    public Employe(int id, String nom, String prenom, int nbrHeure, int prixHeure, int idEquipe, String nom1) {
        super(id, nom, prenom);
        this.nbrHeure = nbrHeure;
        this.prixHeure = prixHeure;
        this.idEquipe = idEquipe;
        this.nom1 = nom1;
    }

    public int getNbrHeure() {
        return nbrHeure;
    }

    public int getPrixHeure() {
        return prixHeure;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public String getNom1() { return nom1; }

    public void setNbrHeure(int nbrHeure) {
        this.nbrHeure = nbrHeure;
    }

    public void setPrixHeure(int prixHeure) {
        this.prixHeure = prixHeure;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setNom1(String nom1) {this.nom1 = nom1; }
}
