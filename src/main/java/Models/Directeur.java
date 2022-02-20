package Models;

public class Directeur extends Salarie {
    float salaire;
    float prime;

    public Directeur(int id, String nom, String prenom, float salaire, float prime) {
        super(id, nom, prenom);
        this.salaire = salaire;
        this.prime = prime;
    }

    public float getSalaire() {
        return salaire;
    }

    public float getPrime() {
        return prime;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }
}
