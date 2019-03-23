package Tools;

import Models.Transaction;
import java.util.ArrayList;

public class Control {

    public static void main(String[] args) {
        String filepath;
        try {
            filepath = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Nie podano ścieżki z plikiem wejściowym, użyty zostanie przykładowy plik");
            filepath = "IN.txt";
        }
        ArrayList<Transaction> transactions = new ArrayList();
        Algorithm algorithm = new Algorithm(filepath);
        
        algorithm.calculate(transactions);
        
        Writer out = new Writer();
        out.write(transactions);
      //  System.out.println(transactions);
    }

}
