package Models;

public class Path {

    public Path(int id_farmy, int id_sklepu, int dzienny_maks_przewóz, int koszt) {
        this.id_farmy = id_farmy;
        this.id_sklepu = id_sklepu;
        this.dzienny_maks_przewóz = dzienny_maks_przewóz;
        this.koszt = koszt;
        this.kosztZmienny = koszt;
    }
    private int id_farmy;
    private int id_sklepu;
    private int dzienny_maks_przewóz;
    private int koszt;
    private int kosztZmienny;
    private int potential = 0;

    public int getId_farmy() {
        return id_farmy;
    }

    public void setId_farmy(int id_farmy) {
        this.id_farmy = id_farmy;
    }

    public int getId_sklepu() {
        return id_sklepu;
    }

    public void setId_sklepu(int id_sklepu) {
        this.id_sklepu = id_sklepu;
    }

    public int getDzienny_maks_przewóz() {
        return dzienny_maks_przewóz;
    }

    public void setDzienny_maks_przewóz(int dzienny_maks_przewóz) {
        this.dzienny_maks_przewóz = dzienny_maks_przewóz;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public int getKosztZmienny() {
        return kosztZmienny;
    }

    public void setKosztZmienny(int kosztZmienny) {
        this.kosztZmienny = kosztZmienny;
    }

    public int getPotential() {
        return potential;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    @Override
    public String toString() {
        return String.format(" %d sklep  i  %d farma koszt stary %d i nowy %d \n", id_sklepu, id_farmy,koszt,kosztZmienny);
    }

}
