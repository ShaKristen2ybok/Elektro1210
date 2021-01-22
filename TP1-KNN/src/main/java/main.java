import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandre on 19/10/2016.
 */


public class main {
    public static void main(String[] args){
        try {
            CSVReader reader = new CSVReader(new FileReader("data/TP1/iris.data"));
            List myEntries = reader.readAll();
            System.out.println("Chargé !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
