package Tools;


import Models.Farm;
import Models.Path;
import Models.Shop;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ReaderTest {

    public Map<Integer, Farm> farms = null;
    public Map<Integer, Shop> shops = null;
    public Map<Integer, Path> paths = null;
    Reader in=null;
    
    
    @Before
    public void setUp() {
        farms = new LinkedHashMap();
        shops = new LinkedHashMap();
        paths = new LinkedHashMap();       
        in = new Reader();
    }

    @Test(expected = IOException.class)
    public void Reader_should_catch_IOException_when_file_doesnt_exist_or_is_inaccessible() throws LackOfDataSectionException, IOException, InvalidAmountOfConnectionsException {
        System.out.println("Test when file doesnt exist or is inaccessible");
        String filepath = "wrongfile.txt";
        in.read(filepath, farms, shops, paths);
    }

    @Test(expected = LackOfDataSectionException.class)
    public void Reader_should_throw_Exception_when_format_of_input_file_is_inappropriate() throws LackOfDataSectionException, IOException, InvalidAmountOfConnectionsException {
        System.out.println("Test when format of file is inappriopriate");
        String filepath = "INtest.txt";
        in.read(filepath, farms, shops, paths);
    }

    @Test(expected = InvalidAmountOfConnectionsException.class)
    public void Reader_should_throw_Exception_when_there_is_lack_of_connections() throws InvalidAmountOfConnectionsException, IOException, LackOfDataSectionException {
        System.out.println("Test when file has lack of connections");
        String filepath = "INtestconnections.txt";
        in.read(filepath, farms, shops, paths);
        System.out.println(farms);
    }
    
    @Test
    public void checks_if_lines_are_read_and_splitted_as_they_should_be() throws Exception {
        System.out.println("Test if lines are the same");
        String filepath = "IN.txt";
        in.read(filepath, farms, shops, paths);
        String line = "3 Iskierka – sklep rodzinny  200";
        String line2 = shops.get(3).toString();
        assertEquals(line, line2);

    }
    
    @Test
    public void checks_if_different_data_are_stored_in_proper_containers() throws Exception {
        String filepath = "IN.txt";
        in.read(filepath, farms, shops, paths);
        assertEquals(shops.get(1).getNazwa(),"Pszczółka ");
        assertEquals(paths.get(1).getId_sklepu(),1);
    }
    
    

}
