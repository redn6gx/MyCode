package hw1;

public class CharCount implements Comparable {
    
    char letter;
    int  count;
    
    public CharCount(char letter){
        this.letter = letter;
        this.count = 0;
    }
    
    public CharCount(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }
    
    public char getLetter() {
    	return letter;
    }
    
    public int getCount() {
    	return count;
    }
    
    public void incrementCount() {
    	count++;
    }
    
    public void decrementCount() {
    	count--;
    }
    
    @Override
    public String toString() {
        return String.format(" %c      %3d", this.letter, this.count);
    }
    
    @Override
    public int compareTo(Object object) {
        CharCount other = (CharCount)object;
        if (this.count < other.count) return -1;
        else if (this.count > other.count) return +1;
        else if (this.letter < other.letter) return -1;
        else if (this.letter > other.letter) return +1;
        else return 0;
    }
    
    @Override
    public boolean equals(Object object) {
    	CharCount other = (CharCount) object;
    	if (this.count == other.count && this.letter == other.letter) 
    		return true;
    	else 
    		return false;
    }
}
