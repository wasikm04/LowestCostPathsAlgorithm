package Tools;

import Models.Farm;
import Models.Path;
import Models.Shop;
import Models.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Algorithm {

    private Map<Integer, Shop> shops;
    private Map<Integer, Farm> farms;
    private Map<Integer, Path> paths;
    int flow = 0;
    private int[] closestDistances;
    int[] newWages;
    Reader in = null;

    public Algorithm(String filepath) {
        this.shops = new LinkedHashMap();
        this.farms = new LinkedHashMap();
        this.paths = new LinkedHashMap();

        in = new Reader();
        try {
            in.read(filepath, farms, shops, paths);
        } catch (IOException ex) {
            System.err.println("Brak pliku o podanej nazwie lub brak uprawnień do odczytu!");
            return;
        } catch (InvalidAmountOfConnectionsException et) {
            System.err.println("Niewystarczająca ilość informacji o połączeniach ferm ze sklepami!");
            return;
        } catch (LackOfDataSectionException e) {
            System.err.println("Nieprawidłowy format pliku, brakuje sekcji danych bądź ich prawidłowych nagłówków!");
            return;
        }

        closestDistances = new int[shops.size() - 1];
        Arrays.fill(closestDistances, 1000);
    }

    public void calculate(ArrayList<Transaction> transactions) {
        long start = System.nanoTime();
        while (getShops().get(getShops().size() - 1).getDzienne_zapotrzebowanie() != 0) {           
            findLowestCosts();
            updatePathsCosts();
            Path P = findBestWay();
            flow = findFlow(P);
            Transaction toadd = new Transaction(getFarms().get(P.getId_farmy()).getNazwa(), P.getId_farmy(), getShops().get(P.getId_sklepu()).getNazwa(), P.getId_sklepu(), flow, P.getKoszt());
            transactions.add(toadd);
            updateInformations(flow, P); 
            System.out.printf("Transakcja: "+toadd);
            System.out.println("Pozostałe jaja w farmach: "+farms.get(getFarms().size() - 1).getDzienna_produkcja());
            System.out.println("Pozostałe jaja w sklepach: "+shops.get(getShops().size() - 1).getDzienne_zapotrzebowanie());
            long stop = (System.nanoTime()-start) / 10000000;
            if(stop > 50){
                System.out.println("Niepowodzenie");
                break;
            }
        }       
    }

    public void updatePathsCosts() {
        int k = 0;
        for (int i = 0; i < (getPaths().size()); i++) {
            int old = getPaths().get(i).getKosztZmienny();
            if (k == (getShops().size() - 1)) {
                k = 0;
            }
            getPaths().get(i).setKosztZmienny(old - getClosestDistances()[k]);
            k++;
        }
    }

    public Path findBestWay() {
        ArrayList<Path> bestpaths = new ArrayList();
        int sum = 0;
        Path best = null;
        int min = 100;
        for (int h = 0; h < (getPaths().size()); h++) {
            if (getPaths().get(h).getKosztZmienny() < min) {
                min = getPaths().get(h).getKosztZmienny();
            }
        }
        for (int i = 0; i < (getPaths().size()); i++) {
            if (getPaths().get(i).getKosztZmienny() == min) {
                bestpaths.add(getPaths().get(i));
            }
        }
        best = bestpaths.get(0);
        for (int j = 0; j < bestpaths.size(); j++) {
            for (int i = 0; i < (getPaths().size()); i++) {
                if (bestpaths.get(j).getId_farmy() == getPaths().get(i).getId_farmy()) {
                    sum = sum + Math.min(getPaths().get(i).getDzienny_maks_przewóz(), getShops().get(getPaths().get(i).getId_sklepu()).getDzienne_zapotrzebowanie());
                }
            }
            sum = sum - getFarms().get(bestpaths.get(j).getId_farmy()).getDzienna_produkcja();
            bestpaths.get(j).setPotential(sum);
            sum = 0;
            if (best.getPotential() > bestpaths.get(j).getPotential() && bestpaths.get(j).getDzienny_maks_przewóz() != 0) {
                best = bestpaths.get(j);
            }
        }
        return best;
    }

    public void findLowestCosts() {
        Arrays.fill(getClosestDistances(), 10);
        int k = 0;
        for (int i = 0; i < getPaths().size(); i++) {
            if (k == (getShops().size() - 1)) {
                k = 0;
            }
            if (getClosestDistances()[k] > getPaths().get(i).getKosztZmienny()) {
                closestDistances[k] = getPaths().get(i).getKosztZmienny();
            }
            k++;
        }
    }

    public int findFlow(Path best) {
        int flow = 10000;
        Farm source = getFarms().get(best.getId_farmy());
        Shop leak = getShops().get(best.getId_sklepu());
        int[] tab = new int[5];
        tab[0] = best.getDzienny_maks_przewóz();
        tab[1] = source.getDzienna_produkcja();
        tab[2] = getFarms().get(getFarms().size() - 1).getDzienna_produkcja();
        tab[3] = getShops().get(getShops().size() - 1).getDzienne_zapotrzebowanie();
        tab[4] = leak.getDzienne_zapotrzebowanie();
        for (int i = 0; i < 5; i++) {
            if (tab[i] < flow) {
                flow = tab[i];
            }
        }
        return flow;
    }

    public void updateInformations(int flow, Path P) {
        getFarms().get(getFarms().size() - 1).setDzienna_produkcja(getFarms().get(getFarms().size() - 1).getDzienna_produkcja() - flow);
        getShops().get(getShops().size() - 1).setDzienne_zapotrzebowanie(getShops().get(getShops().size() - 1).getDzienne_zapotrzebowanie() - flow);
        getFarms().get(P.getId_farmy()).setDzienna_produkcja(getFarms().get(P.getId_farmy()).getDzienna_produkcja() - flow);
        getShops().get(P.getId_sklepu()).setDzienne_zapotrzebowanie(getShops().get(P.getId_sklepu()).getDzienne_zapotrzebowanie() - flow);

        for (int j = 0; j < getPaths().size(); j++) {
            if (getPaths().get(j).getId_sklepu() == P.getId_sklepu() && getPaths().get(j).getId_farmy() == P.getId_farmy()) {
                getPaths().get(j).setDzienny_maks_przewóz(getPaths().get(j).getDzienny_maks_przewóz() - flow);
            }
        }

        if (getShops().get(P.getId_sklepu()).getDzienne_zapotrzebowanie() == 0) {
            for (int i = 0; i < getPaths().size(); i++) {
                if (getPaths().get(i).getId_sklepu() == P.getId_sklepu()) {
                    getPaths().get(i).setKosztZmienny(100);
                    getPaths().get(i).setDzienny_maks_przewóz(0);
                }
            }
        }
        if (getFarms().get(P.getId_farmy()).getDzienna_produkcja() == 0) {
            for (int k = 0; k < getPaths().size(); k++) {
                if (getPaths().get(k).getId_farmy() == P.getId_farmy()) {
                    getPaths().get(k).setKosztZmienny(100);
                    getPaths().get(k).setDzienny_maks_przewóz(0);
                }
            }
        }
    }

    public int[] getClosestDistances() {
        return closestDistances;
    }

    public Map<Integer, Shop> getShops() {
        return shops;
    }

    public Map<Integer, Farm> getFarms() {
        return farms;
    }


    public Map<Integer, Path> getPaths() {
        return paths;
    }
}
