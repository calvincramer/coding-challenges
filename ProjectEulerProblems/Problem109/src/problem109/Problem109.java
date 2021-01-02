package problem109;

import java.util.Random;
import javafx.util.Pair;

public class Problem109 {

    public static Random rng = new Random(System.currentTimeMillis());
    public static int ways = 0;
    public static int conflicts = 0;
    
    public static void main(String[] args) {
        /*
        for (int i = 0; i <= 63; i++) {
            Pair<String, Integer> th = getThrow(i);
            System.out.println(th);
        }
        */
        
        //ways to check out with score less than 100
        long total = 0;
        for (int score = 1; score < 100; score++) {
            ways = 0;
            conflicts = 0;
            
            enumerate(score, score, new String[3], 0);
            
            System.out.println("Score: " + score);
            System.out.println(ways + "\t" + conflicts);
            System.out.println(ways - conflicts);
            System.out.println("");
            total += ways - conflicts;
        }
        System.out.println(total);
        
    }
    
    public static void enumerate(int originalScore, int scoreLeft, String[] priorThrows, int throwNumber) {
        if (scoreLeft == 0 
                && throwChecksOut(priorThrows[throwNumber-1]) 
                && passes(priorThrows)) {
            
            int tally = tallyScore(priorThrows);
            
            ways++;
            //check for possible way to double up on one move
            if (doublesUp(priorThrows))
                conflicts++;
            
            for (int i = 0; i < priorThrows.length; i++) {
                if (priorThrows[i].equals(""))
                    continue;
                System.out.print(priorThrows[i]);
                if (i < priorThrows.length - 1)
                    System.out.print(" | ");
            }
            //check tally for mistakes
            //havent added logic to deal with, just notification
            if (tally != originalScore)
                System.out.println("\t" + tally + " != " + originalScore);
            else
                System.out.println("");
            
            return;
        }
        else if (scoreLeft < 0)
            return;
        
        if (throwNumber >= priorThrows.length) 
            return;
        
        for(int throwID = 0; throwID <= 63; throwID++) {
            //set throw in priorThrows
            Pair<String, Integer> thro = getThrow(throwID);
            priorThrows[throwNumber] = thro.getKey();
            enumerate(originalScore, scoreLeft - thro.getValue(), priorThrows, throwNumber+1);
        }
        
    }
    
    public static Pair<String, Integer> getThrow(int throwNumber) {
        int n = throwNumber;
        
        if (n <= 60) {
            int sdt = n % 3;
            int num = (n / 3) + 1;
            int actual = num * (sdt + 1);
            switch (sdt) {
                case 0: return new Pair("S" + num, actual);
                case 1: return new Pair("D" + num, actual);
                case 2: return new Pair("T" + num, actual);
                default: return new Pair("error", -1);
            }
        }
        switch (n) {
            case 61: return new Pair("OB", 25);
            case 62: return new Pair("IB", 50);
            case 63: return new Pair("XX", 0);  //miss
            default: return new Pair("ERROR", -2);
        }
    }
    
    public static boolean throwChecksOut(String dThrow) {
        return dThrow.charAt(0) == 'D' || dThrow.equals("IB");
    }
    
    public static boolean passes(String[] thros) {
        boolean[] misses = new boolean[thros.length];
        for (int i = 0; i < thros.length; i++)
            if (thros[i].equals("XX"))
                misses[i] = true;
        
        for (int i = 0; i < misses.length - 1; i++)
            if (misses[i] == true && misses[i+1] == false)
                return false;
        
        return true;
    }
    
    public static boolean doublesUp(String[] thros) {
        if (thros[0].charAt(1) == thros[1].charAt(1)
                && thros[0].charAt(0) != thros[1].charAt(0)
                && !thros[2].equals("XX"))
            return true;
        if (thros[1].charAt(1) == thros[2].charAt(1)
                && thros[1].charAt(0) != thros[2].charAt(0))
            return true;
        
        return false;
    }
    
    public static int tallyScore(String[] thros) {
        int score = 0;
        for (String thro : thros) {
            if (thro.equals("XX"))
                continue;
            else if (thro.equals("OB")) {
                score += 25;
            }
            else if (thro.equals("IB")) {
                score += 50;
            }
            else {
                int temp = Integer.parseInt(thro.substring(1));
                if (thro.charAt(0) == 'S')
                    score += temp;
                else if (thro.charAt(0) == 'D')
                    score += temp * 2;
                else if (thro.charAt(0) == 'T')
                    score += temp * 3;
            }
        }
        
        
        return score;
    }
    
    
    ///////nah...
    private class DThrow {
        public String stringRep;
        public int score;
        
        public DThrow(String stringRep, int score) {
            this.stringRep = stringRep;
            this.score = score;
        }
        
        
    }

}
//new approach:
//three loops for each section
//count all ways to check out
//tally score for each