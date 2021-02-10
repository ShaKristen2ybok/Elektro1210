import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alexandre on 19/10/2016.
 */


public class main {
    public static void main(String[] args){
        AlgoKnn k = new AlgoKnn();
        System.out.println(k.getResponse(5.1,3.8,1.6,0.2,1));
        System.out.println(k.getResponse(6.3, 2.5, 5.0, 1.9,1));
        System.out.println(k.getResponse(5.5, 2.4, 3.7, 1.0,80));
;    }
}
