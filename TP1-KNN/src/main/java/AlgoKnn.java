import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Alexandre on 21/10/2016.
 * Passer intellij en 1.8 : http://stackoverflow.com/questions/12900373/idea-javac-source-release-1-7-requires-target-release-1-7#12900859
 */

class AlgoKnn {
    ArrayList<Iris> IrisList;

    AlgoKnn() {
        try {
            CSVReader reader = new CSVReader(new FileReader("data/TP1/iris-base.data"));
            String [] nextLine;
            IrisList = new ArrayList<Iris>();
            while ((nextLine = reader.readNext()) != null) {
                if(nextLine.length == 5) {
                    //System.out.println(nextLine[0] + ", " + nextLine[1] + ", " + nextLine[2] + ", " + nextLine[3] + ", " + nextLine[4] + ", ");
                    IrisList.add(new Iris(
                            Double.parseDouble(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Double.parseDouble(nextLine[2]),
                            Double.parseDouble(nextLine[3]),
                            nextLine[4]
                    ));
                }
            }
            //System.out.println("Apprentissage fait ! !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getResponse(double sL, double sW, double pL, double pW, int Knn) {
        //System.out.println("Calcul du tableau de distances.");
        HashMap<Iris, Double> tableauDistances = new HashMap<Iris, Double>();
        for (Iris i:IrisList) {
            tableauDistances.put(i,
                    sqrt(pow((sL - i.sepalLength),2) + pow((sW - i.sepalWidth),2) + pow((pL - i.petalLength),2) + pow((pW - i.petalWidth),2))
                    );
        }
        //Il faut le trier désormais : On le trie par la distance
        List<Map.Entry<Iris, Double>> tableauDistanceTriee = new ArrayList<Map.Entry<Iris,Double>>(tableauDistances.entrySet());
        Collections.sort(
                tableauDistanceTriee,
                new Comparator<Map.Entry<Iris, Double>>() {
                    public int compare(Map.Entry<Iris, Double> o1, Map.Entry<Iris, Double> o2) {
                        return Double.compare(o1.getValue(), o2.getValue());
                    }
                });

        //Debug : Voir si le tableau est bien trié du plus proche au plus loin (limité à trois objets)
        for (int i = 0; i < 3; i++) {
            //System.out.println(tableauDistanceTriee.get(i));
        }

        // On récupère le k premières réponses (les plus proches)
        List<String> reponsesPossibles = new ArrayList<String>();
        for (int i = 0; i<Knn; i++) {
            reponsesPossibles.add(tableauDistanceTriee.get(i).getKey().type);
        }
        // On récupère les occurences dans la liste
        HashMap<String, Integer> occurences = new HashMap<String, Integer>();

        // Je pars du princique qu'il y a que trois types de fleurs
        occurences.put("Iris-virginica",Collections.frequency(reponsesPossibles, "Iris-virginica"));
        occurences.put("Iris-setosa",Collections.frequency(reponsesPossibles, "Iris-setosa"));
        occurences.put("Iris-versicolor",Collections.frequency(reponsesPossibles, "Iris-versicolor"));

        // On affiche le tableau d'occurences !
        for (Map.Entry<String, Integer> e : occurences.entrySet()) {
            String s = e.getKey();
            Integer i = e.getValue();
            //System.out.println(s + " : " + i.toString());
        }

        //On trie le tableau selon ses valeurs
        List<Map.Entry<String,Integer>> occurencesTriee = new ArrayList<Map.Entry<String, Integer>>(occurences.entrySet());
        Collections.sort(
                occurencesTriee,
                new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return Integer.compare(o2.getValue(),o1.getValue());
                    }
                }
        );

        //Debug : Voir si le tableau est bien trié du plus grand au plus petit
        for (int i = 0; i < 3; i++) {
            //System.out.println(occurencesTriee.get(i));
        }

        //On prend le premier dans la liste ! :D
        return occurencesTriee.get(0).getKey();
        
    }

}
