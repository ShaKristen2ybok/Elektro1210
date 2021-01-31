import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alexandre on 21/10/2016.
 */
public class AlgoKnn {
    ArrayList<Iris> IrisList;

    public AlgoKnn() {
        try {
            CSVReader reader = new CSVReader(new FileReader("data/TP1/iris.data"));
            String [] nextLine;
            IrisList = new ArrayList();
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(nextLine[0] + ", " + nextLine[1] + ", " + nextLine[2] + ", " + nextLine[3] + ", " + nextLine[4] + ", ");
                IrisList.add(new Iris(
                        Float.parseFloat(nextLine[0]),
                        Float.parseFloat(nextLine[1]),
                        Float.parseFloat(nextLine[2]),
                        Float.parseFloat(nextLine[3]),
                        nextLine[4]
                ));
            }
            System.out.println("Apprentissage fait ! !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
