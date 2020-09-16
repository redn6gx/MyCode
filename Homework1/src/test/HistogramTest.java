package test; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hw1.*;


public class HistogramTest {
    

    @Test
    public void testConstructor() {
        CharCount[] expected = new CharCount['K'-'A'+1];
        for (int k =0; k<expected.length; k++){
            expected[k] = new CharCount((char)('A'+k));
        }
        Histogram h = new Histogram();
        assertArrayEquals( expected, h.getCounts());
    }
    @Test
    public void testFileNotFound() {
        Histogram h = new Histogram();
        boolean result = h.readFile("t.t");
        assertFalse(result);
    }
    
    @Test
    public void read_test1_txt() {   
        CharCount[] expected = { 
            new CharCount('A',1),
            new CharCount('B',3),
            new CharCount('C',1),
            new CharCount('D',0),
            new CharCount('E',0),
            new CharCount('F',0),
            new CharCount('G',0),
            new CharCount('H',0),
            new CharCount('I',0),
            new CharCount('J',0),
            new CharCount('K',0),
        };
        Histogram h = new Histogram();
        h.readFile("test1.txt");
        CharCount[] counts = h.getCounts();
        assertArrayEquals(expected, counts);
    }
    
    @Test
    public void read_test2_txt() {  
        CharCount[] expected = { 
            new CharCount('A',1),
            new CharCount('B',2),
            new CharCount('C',0),
            new CharCount('D',2),
            new CharCount('E',0),
            new CharCount('F',0),
            new CharCount('G',0),
            new CharCount('H',4),
            new CharCount('I',0),
            new CharCount('J',0),
            new CharCount('K',1),
        };
        Histogram h = new Histogram();
        h.readFile("test2.txt");
        CharCount[] counts = h.getCounts();
        assertArrayEquals(expected, counts);
    }
    
    @Test
    public void testMaxCount() {
        Histogram h = new Histogram();
        h.readFile("test2.txt");
        int m = h.getMaxCount();
        assertEquals(m, 4);
        int a = h.getCountLess(4);
        assertEquals(a, 10);
        a = h.getCountLess(2);
        assertEquals(a, 8);
    }
    
    @Test
    public void testSortByCount() {
        CharCount[] expected = { 
            new CharCount('C',0),
            new CharCount('E',0),
            new CharCount('F',0),
            new CharCount('G',0),
            new CharCount('I',0),
            new CharCount('J',0),
            new CharCount('A',1),
            new CharCount('K',1),
            new CharCount('B',2),
            new CharCount('D',2),
            new CharCount('H',4),
        };
        Histogram h = new Histogram();
        h.readFile("test2.txt");
        CharCount[] counts = h.sortByCount();
        assertArrayEquals(expected, counts);   
    }
}
