/*
 * Provides char counts for any text file (input file used as constructor parameter). 
 * Created by David Johnson, October 8, 2017
 * for CS211 course, Bellevue College
 */
package filecharcount;

import java.io.*;
import java.util.*;

public class FileCharCount {
    // FIELD
        Map<Character, Integer> charCountMap = new TreeMap<>();
   
    // CONTRUCTOR
    public FileCharCount(Scanner data) {
        
        while(data.hasNext()) {
            String word = data.next().toLowerCase();
            for(int i = 0; i < word.length(); i++) {
                char test = word.charAt(i);
                if(this.charCountMap.containsKey(test)) {
                    int count = this.charCountMap.get(test) + 1;
                    this.charCountMap.put(test, count);
                } else {
                    this.charCountMap.put(test, 1);
                }
            }
        }
    }
    
    //METHODS
    // This one lists the top "number" of char counts
    // (bottom if negative)
    public String getCounts(int number) {
        Set<CharInt> charCountSet = new TreeSet<>();
        String output = "Please enter a non-zero number";
        
        // If number is greater than the total number of elements, we'll just
        // return the number of elements
        if(number > this.charCountMap.size()) {
            return "" + this.charCountMap.size();
        }
        
        // Load up the Set so we can sort it
        for(char character: this.charCountMap.keySet()) {
            charCountSet.add(new CharInt(this.charCountMap.get(character), character));
        }
        
        // Return the set in descending order
        if(number > 0) {
            List<CharInt> sortedList = new ArrayList<>(charCountSet);
            int finalIndex = 0;
            
            Collections.reverse(sortedList);
            
            output = "";
            for(int i = 0; i < number - 1; i++) {
                output += sortedList.get(i).toString() + ", ";
                finalIndex = i + 1;
            }
            output += sortedList.get(finalIndex);
        }
        
        // Return the set in ascending order
        if(number < 0) {
            Iterator<CharInt> itr = charCountSet.iterator();
            
            output = "";
            for(int i = -1; i > number; i--) {
                output += itr.next().toString() + ", ";
            }
            output += itr.next().toString();
        }
        
        return output;
    }
    
    // This one returns the count of the character supplied
    public int getCounts(char character) throws NullPointerException {
        try {
            return this.charCountMap.get(character);
        } catch(NullPointerException e) {
            return 0;
        }
    }
    
    // This one returns all counts of characters
    public String getCounts() {
        return this.charCountMap.toString();
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("hamlet.txt"));
        
        FileCharCount working = new FileCharCount(input);
        
        System.out.println(working.getCounts('q'));
        System.out.println(working.getCounts());
        System.out.println(working.getCounts(-3));
        System.out.println(working.getCounts(3));
    }
    
}
