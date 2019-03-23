package Models;

public class Farm {

    public Farm(int id, String nazwa, int dzienna_produkcja) {
        this.id = id;
        this.nazwa = nazwa;
        this.dzienna_produkcja = dzienna_produkcja;
    }
    private int id;
    private String nazwa;
    private int dzienna_produkcja;

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getDzienna_produkcja() {
        return dzienna_produkcja;
    }

    public void setDzienna_produkcja(int dzienna_produkcja) {
        this.dzienna_produkcja = dzienna_produkcja;
    }
    

}
