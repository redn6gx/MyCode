package hw1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Title: File Name
 * Abstract: Overall purpose (or functionality) of the program.
 * Author: Your name
 * Date: The date you wrote the program
 */

public class H1Main {
    
    public static void main(String[] args) {
    	// prompt user for filename
    	Scanner keyboard = new Scanner(System.in);
    	System.out.print("Input filename: ");
    	String filename = keyboard.nextLine();
    	
        int max =0;
        Histogram h = null;
        
        //TODO create a Histogram object
        // use readFile method to read a test file
        // use Histogram#getCounts methods get get 
        // counts array and pass it to the 
        // printCounts method below
        
        h = new Histogram();
        h.readFile(filename);
        printCounts(h.getCounts());
        
        CharCount[] sortedByCount = Arrays.copyOf(h.sortByCount(),h.getCounts().length);
        
        System.out.println();  // blank line
        // print histogram
        String hline = "=============================";
        System.out.println(hline );
       // TODO use the Histogram getMaxCount method 
       // to get the number of histogram rows to print
        
       max = h.getMaxCount();
        
       CharCount[] copy = new CharCount[h.getCounts().length];
       
        for (int row = max; row>0; row--){
            System.out.printf("|%3d |", row);
 
            for(int k=0; k < sortedByCount.length; k++) {
            	if (row == sortedByCount[k].getCount()) {
            		System.out.print("*");
            		sortedByCount[k].decrementCount();
                }
            	else {
            		System.out.print(" ");
            	}
            }
            
            if(row==1) {
            	break;
            }
            System.out.println();
        }
            // TODO 
            // complete the code to print spaces and asterisks
            // using the Histogram getCountLess method
	    
        System.out.println();
        System.out.println(hline);
        
        // print x axis labels
        System.out.print("| No |");
        
        //TODO complete the code to print the x axis labels by 
        // using the Histogram#sortByCount method
     
        int size = h.getCounts().length;
        
        //System.out.print(" ");
        for(int i=0; i<size; i++) {	
        System.out.print(sortedByCount[i].getLetter());
        }
        System.out.println();
        System.out.println(hline);
    }
    
     /**
     * print letter and count 
     * only for letters with non-zero count
     */
    public static void printCounts(CharCount[] counts ) {
        System.out.println("Char  Occurrences");
        for (CharCount cc : counts) {
            if (cc.getCount() != 0){
                System.out.println(cc);
            }
        }
        
    }
    
}
