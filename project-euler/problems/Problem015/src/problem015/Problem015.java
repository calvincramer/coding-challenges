package problem015;

import java.math.BigInteger;
import mathtools.MF;

public class Problem015 {

        /*
    Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
    How many such routes are there through a 20×20 grid?
    */
    public static void main(String[] args) {
        new Problem015().try2();
    }
    
    private void try2() {
        //try two
        //20x20 grid, so 40 moves
        //order of moves doesn't matter, so 40! (factorial) / (20!*20!)
        BigInteger fact40 = new BigInteger("1");
        for (int i = 1; i <= 40; i++)
            fact40 = fact40.multiply(new BigInteger(i + ""));
        BigInteger fact20 = new BigInteger("1");
        for (int i = 1; i <= 20; i++)
            fact20 = fact20.multiply(new BigInteger(i + ""));
        BigInteger fact20sqr = fact20.multiply(fact20);
        BigInteger result = fact40.divide(fact20sqr);
        System.out.println("Total moves: " + result);
    }
    
    private void try1() {

        //try 1
        MF.startTimer();
        int dim = 20;
        long numberOfMoves = 0;
        int outputCount = 0;
        boolean[] arr = new boolean[dim*2];
        
        for (int i = 0; i < arr.length; i++)
            arr[i] = false;
        
        for (int i = 0; i < arr.length; i++) { 
            if (arr[i])
                System.out.print('1');
            else
                System.out.print('0');
        }
        System.out.println();
            
        boolean done = false;
        while (!done) {
            
            int index = 0;              //increment array
            while(arr[index] == true) {    //carry over 1
                arr[index] = false;
                index++;
            }
            arr[index] = true;
            
            //check if valid move
            int zeroes = 0;
            int ones = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == false) zeroes++;
                if (arr[i] == true) ones++;
            }
            done = (zeroes == 0); //no zeroes left

            if (zeroes == ones) numberOfMoves++;
            
            
            outputCount++;
            if (outputCount % 100000000 == 0) {
                for (int i = 0; i < arr.length; i++) { 
                    if (arr[i])
                        System.out.print('1');
                    else
                        System.out.print('0');
                }
                System.out.println("\t" + numberOfMoves + " moves\tt=" + MF.getElapsedSeconds());
                outputCount = 0;
            }
                    
                    
        }
        
        System.out.println("Num moves: " + numberOfMoves);
        

        
    } 
    
    private boolean isAllFixed(Element[] elems) {
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].color == State.NOT_FIXED) return false;
        }
        return true;
    }
    
    private class Element {
        protected State color = State.NOT_FIXED;
        protected char ch;
        
        public Element(char c, State s) {
            this.color = s;
            this.ch = c;
        }
    }
    
    private enum State {
        NOT_FIXED, FIXED;
    }

}
//answer : 137846528820