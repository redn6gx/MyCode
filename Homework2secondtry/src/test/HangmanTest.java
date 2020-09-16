package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hw2.Hangman;


public class HangmanTest {

    @Test
    public void test1() {
        Hangman h = new Hangman("difficult", 4);
        assertEquals(4, h.getIncorrectGuessesRemaining());
        assertEquals("_ _ _ _ _ _ _ _ _", h.getDisplay());
        assertEquals("D I F F I C U L T", h.getFullDisplay());
        assertFalse(h.won());
    }

    @Test
    public void test2() {
        Hangman h = new Hangman("it is easy", 4);
        assertEquals(4, h.getIncorrectGuessesRemaining());
        assertEquals("_ _ # _ _ # _ _ _ _", h.getDisplay());
        assertEquals("I T # I S # E A S Y", h.getFullDisplay());
        assertFalse(h.won());
    }

    @Test
    public void test3() {
        Hangman h = new Hangman("difficult", 4);
        assertEquals(4, h.getIncorrectGuessesRemaining());
        h.guess('i');
        assertEquals("_ I _ _ I _ _ _ _", h.getDisplay());
        assertEquals("D I F F I C U L T", h.getFullDisplay());
        assertFalse(h.won());
    }

    @Test
    public void test4() {
        Hangman h = new Hangman("difficult", 4);
        h.guess('i');
        h.hint();
        assertEquals("D I _ _ I _ _ _ _", h.getDisplay());
        assertEquals(3, h.getIncorrectGuessesRemaining());
        assertFalse(h.won());
    }

    @Test
    public void test5() {
        Hangman h = new Hangman("difficult", 4);
        h.guess('i');
        // guessing again a correct letter should return -1
        assertEquals(-1, h.guess('i'));
        assertEquals(4, h.getIncorrectGuessesRemaining());

        // guess an incorrect letter return -2
        // if you guess the same letter again return -1
        assertEquals(-2, h.guess('a'));
        assertEquals(-1, h.guess('a'));
        assertEquals(2, h.getIncorrectGuessesRemaining());
    }

    @Test
    public void test6() {
        Hangman h = new Hangman("good bye", 4);
        h.guess('g');
        h.guess('o');
        h.guess('d');
        h.guess('b');
        h.guess('y');
        h.guess('e');
        assertTrue(h.won());
        assertEquals("G O O D # B Y E", h.getDisplay());
    }

    @Test
    public void test7() {
        Hangman h = new Hangman("good bye", 4);
        h.guess('a');
        h.guess('c');
        h.guess('f');
        h.guess('a');
        assertFalse(h.won());
        assertEquals(0, h.getIncorrectGuessesRemaining());
        assertEquals("G O O D # B Y E", h.getFullDisplay());
    }

    @Test
    public void test8() {
        Hangman h = new Hangman("good bye", 4);
        h.guess('e');
        assertEquals('G', h.hint());
        assertEquals(3, h.getIncorrectGuessesRemaining());
        assertEquals(2, h.getIncorrectGuessesRemaining());
        assertEquals("G O O _ # _ _ E", h.getDisplay());
    }
}

