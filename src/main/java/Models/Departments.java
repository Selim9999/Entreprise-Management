package Models;

public class Departments {
    int id;
    String name;
    String director;
    int budget;
    int members;

    public Departments(int id, String name, String director, int budget, int members) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.budget = budget;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getBudget() {return budget;}

    public int getMembers() {
        return members;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setBudget(int budget) {this.budget = budget;}

    public void setMembers(int members) {
        this.members = members;
    }
}

