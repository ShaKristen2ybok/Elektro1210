import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alexandre on 19/10/2016.
 */


public class main {
    public static void main(String[] args){
        AlgoKnn knn = new AlgoKnn();
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("La prediction est : " + knn.getResponse(5.1, 3.8, 1.6, 0.2, 1));
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("La prediction est : " + knn.getResponse(6.3, 2.5, 5.0, 1.9, 1));
        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("La prediction est : " + knn.getResponse(5.5, 2.4, 3.7, 1.0, 40));
        System.out.println("==================================================");
        System.out.println("==================================================");

        ArrayList<Iris> IrisList_Test = null;

        // On récupère la base de test
        try {
            CSVReader reader = new CSVReader(new FileReader("data/TP1/iris-test.data"));
            String [] nextLine;
            IrisList_Test = new ArrayList<Iris>();
            while ((nextLine = reader.readNext()) != null) {
                if(nextLine.length == 5) {
                    IrisList_Test.add(new Iris(
                            Double.parseDouble(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Double.parseDouble(nextLine[2]),
                            Double.parseDouble(nextLine[3]),
                            nextLine[4]
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int k = 1; k <= 20; k++){
            double percentage = 0;
            double count = 0;
            String result = "";
            for(int i = 0; i < IrisList_Test.size(); i++){
                result = knn.getResponse(IrisList_Test.get(i).sepalLength, IrisList_Test.get(i).sepalWidth, IrisList_Test.get(i).petalLength, IrisList_Test.get(i).petalWidth, k);
                if (result.compareTo(IrisList_Test.get(i).type) == 0){
                    count += 1;
                }
            }
            percentage = Math.round((count / 75.0)*100.0);
            System.out.println("Pour k = "+k+", Le pourcentage de bonne prediction est de : "+percentage);
        }
;    }
}
