package problem211;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;


public class Try2 {
    
    public static void main(String[] args) {
        long[] sizes = {1000000, 2000000, 4000000, 8000000, 16000000, 32000000, 64000000};
        for (long max : sizes) {
            MF.startTimer();
            System.out.print(max + ":\t");
            
            //build factors of all numbers under max
            List<Long>[] factorList = new ArrayList[(int)max];
            for (int i = 0; i < factorList.length; i++)
                factorList[i] = new ArrayList<>();
            
            for (long i = 1; i < factorList.length; i++) {
                long step = i;
                do {
                    factorList[(int)step].add(step / i);
                    step += i;
                } while (step < factorList.length);
                //Collections.sort(factorList[(int)i]);
            }
            
            //compute sigma2 s
            long total = 0;
            
            for (int i = 0; i < factorList.length; i++) {
                long sumSquares = 0;
                for (long div : factorList[i])
                    sumSquares += div*div;
                if (MF.isPerfectSquare(sumSquares))
                    total += i;
            }
            
            System.out.println(total + "\t\tt= " + MF.getElapsedSeconds());
            


        }
    }
    


}
/*
1000000:    	9890738		t= 8.397050294
2000000:    	19188103		t= 22.420080256
4000000:    	48307997		t= 64.210449274
8000000:    	158222609		t= 180.685046881
16000000:	355907527		t= 510.017035302
32000000:	894676517		t= 1439.050922017
64000000:	1922364685		t= 4056.053654623
*/

//try 3:
//an array of size 64 million, dont keep a list of divisors, jusst sum them along the way.