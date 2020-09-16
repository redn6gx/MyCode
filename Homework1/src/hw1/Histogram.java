package hw1;
/**

 * Title: File Name
 * Abstract: Overall purpose (or functionality) of the program.
 * Author: Your name
 * Date: The date you wrote the program
 */

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class Histogram {
    
    public static final char FIRST_LETTER = 'A';
    public static final char LAST_LETTER = 'K';
    public  static final int SIZE = LAST_LETTER - FIRST_LETTER + 1;  

    CharCount[] counts;
      
    public Histogram() {
        // TODO 
        // allocate counts array of CharCount 
        // for letters 'A' to 'K'
    	int i = 0;
        counts = new CharCount[SIZE];
        for (char letter = 'A'; letter<='K'; letter++)
        {
        	counts[i] = new CharCount(letter);
        	i++;
        }
        
    }
    
    
    public CharCount[] getCounts() {
        return counts;
    }
    
    /**
     * read file and count occurrences of letters.
     * return true 
     *        false bad data in file or file cannot be read
     */
    public boolean readFile(String fileName){
        // TODO 
        // read file and count occurrences of letters 
        // using counts array.
    	Scanner file = null;
    	try {
    		file = new Scanner(new File(fileName));
    		while(file.hasNextLine())
    		{
    			
        		String s = (file.nextLine());
        		
        		for (int k = 0; k < SIZE; k++) {
        			if (s.charAt(0) == counts[k].getLetter())
        			{
        				counts[k].incrementCount();
        			}
        		}
        		
    		}
    		
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}		
        return true;
    }
    
 
    
    /**
     * return the highest count 
     */
    public int getMaxCount() {
        // TODO 
        // return max count value
    	int max = 0;
    	for (int k = 0; k < SIZE; k++) {
    		if (max < counts[k].getCount()) {
    			max = counts[k].getCount();
    		}
    	}
        return max;
    }
    
    /**
     * return the number of counts that 
     * are LESS than the given value
     */
    public int getCountLess(int value) {
        // TODO 
    	int num = 0;
    	
    	for (int k = 0; k < SIZE; k++) {
    		if (counts[k].getCount() < value) {
    			num++;
    		}
    	}
    	
        return num;
    }
    
    /**
     * return array of CharCount sorted by 
     * count in ascending order.
     * if 2 letters have same count, 
     * then sort by alphabetical order
     */
    public CharCount[] sortByCount() {
        //TODO 
        // return a copy of the counts array
        // in alphabetical order
    	// hint:  use java.util.Arrays.sort method
    	
    	CharCount[] copy = Arrays.copyOf(this.getCounts(),SIZE);
    	Arrays.sort(copy);
    	
    	
        return copy;
    }
    
}
