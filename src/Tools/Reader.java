package Tools;

import Models.Farm;
import Models.Path;
import Models.Shop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Reader {

    public void read(String filepath, Map farms, Map shops, Map paths) throws IOException, LackOfDataSectionException, InvalidAmountOfConnectionsException {
        int counterline = 0;
        int sumOfproduction = 0;
        int sumOforders = 0;
        int size;
        int check = 0;
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        String line;
        int[] tab = new int[2];
        String[] secondTab = new String[4];
        File plik = new File(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(plik), "UTF8"));
        

        while ((line = br.readLine()) != null) {
            counterline++;
            if (line.length() == 0) {
                System.err.println("Pusta linia: " + counterline);
                continue;
            }
            StringBuffer name = new StringBuffer("");
            StringTokenizer tokenizer = new StringTokenizer(line);
            size = tokenizer.countTokens();

            if (line.contains("# Fermy")) {
                check = 1;
                continue;
            }
            if (line.contains("# Sklep")) {
                check = 2;
                continue;
            }
            if (line.contains("# Połączenia")) {
                check = 3;
                continue;
            }
            if (check == 0) {
                throw new LackOfDataSectionException();
            }

            if (check == 1) {
                tab[0] = Integer.parseInt(tokenizer.nextToken());
                for (int i = 1; i < size - 1; i++) {
                    name.append(tokenizer.nextToken()).append(" ");
                }
                tab[1] = Integer.parseInt(tokenizer.nextToken());
                Farm farm = new Farm(tab[0], name.toString(), tab[1]);
                farms.put(tab[0], farm);
                counter1++;
                sumOfproduction += tab[1];
            }
            if (check == 2) {
                tab[0] = Integer.parseInt(tokenizer.nextToken());
                for (int i = 1; i < size - 1; i++) {
                    name.append(tokenizer.nextToken()).append(" ");
                }
                tab[1] = Integer.parseInt(tokenizer.nextToken());
                Shop shop = new Shop(tab[0], name.toString(), tab[1]);
                shops.put(tab[0], shop);
                counter2++;
                sumOforders += tab[1];
            }
            if (check == 3) {
                secondTab = line.split(" ");
                Path path;
                path = new Path(Integer.parseInt(secondTab[0]), Integer.parseInt(secondTab[1]), Integer.parseInt(secondTab[2]), Integer.parseInt(secondTab[3]));
                paths.put(counter3, path);
                counter3++;
            }
        }
        if (counter3 != counter1 * counter2) {
            throw new InvalidAmountOfConnectionsException();
        }

        Farm superźródło = new Farm(farms.size(), "Superźródło", sumOfproduction);
        farms.put(farms.size(), superźródło);

        Shop superujście = new Shop(shops.size(), "Superujście", sumOforders);
        shops.put(shops.size(), superujście);

        br.close();
    }
}
