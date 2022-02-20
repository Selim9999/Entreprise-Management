package Models;

public class Equipe {
    int id;
    String nom;
    int nbrEmp;
    int sumSalaire;
    int idDepartement;
    String name;

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbrEmp=" + nbrEmp +
                ", sumSalaire=" + sumSalaire +
                ", idDepartement=" + idDepartement +
                ", nameDepartment='" + name + '\'' +
                '}';
    }

    public Equipe(int id, String nom, int nbrEmp, int sumSalaire, int idDepartement, String name) {
        this.id = id;
        this.nom = nom;
        this.nbrEmp = nbrEmp;
        this.sumSalaire = sumSalaire;
        this.idDepartement = idDepartement;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNbrEmp() {
        return nbrEmp;
    }

    public int getSumSalaire() {
        return sumSalaire;
    }

    public String getName() {
        return name;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbrEmp(int nbrEmp) {
        this.nbrEmp = nbrEmp;
    }

    public void setSumSalaire(int sumSalaire) {
        this.sumSalaire = sumSalaire;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public void setName(String name) {
        this.name = name;
    }
}
