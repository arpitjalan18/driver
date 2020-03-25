package driverProj;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*; 
public class readFile
{
    public static LinkedList read()
    {
        String csvFile = "C:/Users/arpit/coding/twiet/driverData2.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        LinkedList rows = new LinkedList();
        LinkedList drivers = new LinkedList();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {       
                String[] rowsStart = line.split(csvSplitBy);
                LinkedList<String> elements = new LinkedList<String>();
                for (int i =0; i < rowsStart.length; i++)
                    elements.add(rowsStart[i]);
                rows.add(line);
                Driver newDriver =  new Driver(elements.get(0).toString(), elements.get(1).toString(), elements.get(2).toString(), elements.get(3).toString(), elements.get(4).toString(), elements.get(5).toString(), elements.get(6).toString(), elements.get(7).toString(), elements.get(8).toString(), elements.get(9).toString()); 
                drivers.add(elements);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return drivers;
    }
    public static void delete(){
        File file = new File("C:/Users/arpit/coding/twiet/driverData2.csv");
        file.delete();
    }
}
