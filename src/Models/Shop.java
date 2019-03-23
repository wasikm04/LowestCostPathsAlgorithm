package Models;

public class Shop {

    public Shop(int id, String nazwa, int dzienne_zapotrzebowanie) {
        this.id = id;
        this.nazwa = nazwa;
        this.dzienne_zapotrzebowanie = dzienne_zapotrzebowanie;
    }
    private int id;
    private String nazwa;
    private int dzienne_zapotrzebowanie;

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getDzienne_zapotrzebowanie() {
        return dzienne_zapotrzebowanie;
    }

    public void setDzienne_zapotrzebowanie(int dzienne_zapotrzebowanie) {
        this.dzienne_zapotrzebowanie = dzienne_zapotrzebowanie;
    }
       
    @Override
    public String toString(){
       return String.format("%d %s %d",id,nazwa,dzienne_zapotrzebowanie);
    }
}
