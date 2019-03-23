package Models;

public class Transaction {

    public Transaction(String farma, int id_farmy, String sklep, int id_sklepu, int ilość_jaj, int cena) {
        this.id_farmy = id_farmy;
        this.farma = farma;
        this.id_sklepu = id_sklepu;
        this.sklep = sklep;
        this.cena = cena;
        this.ilość_jaj = ilość_jaj;
    }
    private int id_farmy;
    private String farma;
    private int id_sklepu;
    private String sklep;
    private int suma;
    private int ilość_jaj;
    private int cena;

    public int getSuma() {
        suma = getIlość_jaj() * getCena();
        return suma;
    }
    
    public int getId_farmy() {
        return id_farmy;
    }

    
    public String getFarma() {
        return farma;
    }

    
    public int getId_sklepu() {
        return id_sklepu;
    }

    
    public String getSklep() {
        return sklep;
    }

    
    public int getIlość_jaj() {
        return ilość_jaj;
    }

   
    public int getCena() {
        return cena;
    }

    
    @Override
    public String toString() {
        return String.format("%s   >   %s     [Suma = %d * %d = %d]\n",getFarma(),getSklep(),getIlość_jaj(),getCena(),getSuma());
    }
}
