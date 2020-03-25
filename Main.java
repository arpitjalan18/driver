package driverProj;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 class Main
{
   public static void main(String args[]){
    LinkedList drivers = readFile.read();
    System.out.println(drivers.get(0) + "\n" + drivers.get(1));
    simple.run(drivers);
   }
}
