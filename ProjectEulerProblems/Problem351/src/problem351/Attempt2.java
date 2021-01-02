package problem351;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import mathtools.MF.LFraction;

public class Attempt2 {

    private static List<LFraction> uniqueFractions;
    
    public static void main(String[] args) {
        
        int[] ns = {5, 10, 1000, 10000, 100000, 1000000};
        for (int n : ns) {
            MF.startTimer();
            long res = Hn(n);
            System.out.println("H(" + n + ") = " + res + "\t\t" + MF.getElapsedSeconds());
            
            for (int i = 0; i < uniqueFractions.size() - 2; i++) {
                if (uniqueFractions.get(i).getDecimalForm() > uniqueFractions.get(i+1).getDecimalForm())
                    System.out.println("DECREASE");
            }
        }
        
    }
    
    public static long Hn(int size) {
        
        long numCollisions = 0;
        uniqueFractions = new ArrayList<>();
        
        
        for (int currentRow = 1; currentRow <= size; currentRow++) {
            for (int index = 0; index <= currentRow; index++) {
                LFraction f = new LFraction(index, currentRow);
                f.reduceFraction();
                /*
                if (listContains(uniqueFractions, f)) {
                    numCollisions++;
                } else {
                    addInOrder(uniqueFractions, f);
                }
                        */
            }
            
            
        }

        numCollisions -= (size - 1);
        
        return numCollisions * 6;
    }
    
    public static boolean listContains(List<LFraction> list, LFraction toFind) {
        
        if (list.size() ==  0)
            return false;
        
        int guessIndex = (int) ((list.size()-1) * (toFind.numerator * 1.0 / toFind.denominator));
        
        if (guessIndex >= list.size())
            return false;
        
        //three cases
        //1: guess index matches toFind
        //2: guess index greater than toFind (traverse down)
        //3: guess index less than toFind (traverse up)
        
        if (list.get(guessIndex).compareTo(toFind) == 0)
            return true;
        
        if (list.get(guessIndex).compareTo(toFind) > 0) {
            //traverse down
            while (list.get(guessIndex).compareTo(toFind) > 0 && guessIndex > 0) {
                guessIndex--;
            }
            return list.get(guessIndex).compareTo(toFind) == 0;
        }
        else {
            //traverse up
            while (list.get(guessIndex).compareTo(toFind) < 0 && guessIndex < list.size() - 1) {
                guessIndex++;
            }
            return list.get(guessIndex).compareTo(toFind) == 0;
        }
    }
    
    public static void addInOrder(List<LFraction> list, LFraction toAdd) {
        if (list.size() == 0) {
            list.add(toAdd);
            return;
        }
        
        int guessIndex = (int) (list.size() * (toAdd.numerator * 1.0 / toAdd.denominator));
        
        if (guessIndex >= list.size()) {
            list.add(toAdd);
            return;
        }
        
        if (list.get(guessIndex).compareTo(toAdd) > 0) {
            //traverse down
            while (list.get(guessIndex).compareTo(toAdd) > 0 && guessIndex > 0) {
                guessIndex--;
            }
            list.add(guessIndex + 1, toAdd);
        }
        else {
            //traverse up
            while (list.get(guessIndex).compareTo(toAdd) < 0 && guessIndex < list.size() - 1) {
                guessIndex++;
            }
            list.add(guessIndex, toAdd);
        }
    }

}
/*
What we learned:
Just calculating the reduced fractions is expensive.
Will take a VERY long time to calculate all reduced fractions below 100 000 000

H(5) = -24		3.85903E-4
H(10) = -54		4.6484E-5
H(1000) = -5994		0.047383621
H(10000) = -59994		4.611365603
H(100000) = -599994		572.457861881
*/