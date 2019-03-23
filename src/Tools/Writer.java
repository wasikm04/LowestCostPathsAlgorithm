package Tools;

import Models.Transaction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Writer {

    public void write(ArrayList<Transaction> results) {
        File out = new File("Wyniki.txt");
        PrintWriter zapis = null;
        int sum = 0;
        try {
            out.createNewFile();
        } catch (FileAlreadyExistsException e) {
            System.err.println("Plik o tej ścieżce już istnieje!");          
        } catch (IOException ee) {
            System.err.println(ee);
        }

        try {
            zapis = new PrintWriter(out);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        if (results.isEmpty() == false) {
            for (Transaction i : results) {
                zapis.println(i.toString());
                sum = sum + i.getSuma();
            }
            zapis.printf("Suma kosztow %d", sum);
        }
        zapis.close();
    }

}
