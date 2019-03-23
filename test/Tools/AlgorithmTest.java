package Tools;

import Models.Farm;
import Models.Path;
import Models.Shop;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AlgorithmTest {

    public Map<Integer, Farm> farms = null;
    public Map<Integer, Shop> shops = null;
    public Map<Integer, Path> paths = null;   
    Algorithm instance;

    @Before
    public void setUp() throws Exception {
        farms = new LinkedHashMap();
        shops = new LinkedHashMap();
        paths = new LinkedHashMap();      
    }
    
    @Test
    public void Find_Best_Way_should_return_path_from_farm_1_to_shop_3() {
        System.out.println("findBestWay");    
        String filepath = "INtestalgorithm1.txt";
        instance = new Algorithm(filepath);
        Path result = instance.findBestWay();
        assertEquals(3, result.getId_sklepu());
        assertEquals(1, result.getId_farmy());
    }

    
    @Test
    public void Lowest_costs_of_transport_from_every_farm_to_invidual_shops_should_be_1_2_3_4() {
        System.out.println("findLowestCosts");   
        String filepath = "INtestalgorithm2.txt";
        instance = new Algorithm(filepath);
        instance.findLowestCosts();           
        int[] distances = instance.getClosestDistances();
        assertEquals(1,distances[0]);
        assertEquals(2,distances[1]);
        assertEquals(3,distances[2]);
        assertEquals(4,distances[3]);              
    }

    
    @Test
    public void Flow_of_path_P_should_be_150() {
        System.out.println("findFlow");
        String filepath = "INtestalgorithm.txt";
        instance = new Algorithm(filepath);
        Path P = new Path(1,1,150,3);
        int expResult = 150;
        int result = instance.findFlow(P);
        assertEquals(expResult, result);
    }

    
    @Test
    public void Update_the_farm_and_shop_from_path_0_by_100() {
        System.out.println("updateInformations");
        String filepath = "IN.txt";
        int flow = 100;
        instance = new Algorithm(filepath);
        paths = instance.getPaths();
        farms = instance.getFarms();
        shops = instance.getShops(); 
        Path P = paths.get(0);
        instance.updateInformations(flow, P);
        assertEquals(200,farms.get(P.getId_farmy()).getDzienna_produkcja());
        assertEquals(50,shops.get(P.getId_sklepu()).getDzienne_zapotrzebowanie());
        
    }

}
