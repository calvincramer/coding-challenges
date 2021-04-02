package problem039;

import java.util.ArrayList;
import java.util.List;

public class Problem039 {

    /*
    If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

    {20,48,52}, {24,45,51}, {30,40,50}

    For which value of p ≤ 1000, is the number of solutions maximised?
    */
    public static void main(String[] args) {
        
        MathFunctions.startTimer();
        
        int greatestP = 0;
        List<Triple> greatestTriples = null;
        
        for (int p = 1; p <= 1000; p++) {
            //find integer right triangles
            List<Triple> triples = new ArrayList<>();
            
            for (int a = 1; a < p; a++) {
            for (int b = 1; b < p; b++) {
                    int c = p - a - b;
                    if (a + b + c == p && (a*a)+(b*b)==(c*c) )
                        triples.add(new Triple(a,b,c));
            }}
            if (greatestTriples == null)
                greatestTriples = triples;
            if (triples.size() > greatestTriples.size()) {
                greatestTriples = triples;
                greatestP = p;
            }
        }
        
        System.out.println("Max perimeter: " + greatestP);
        for (Triple tri : greatestTriples)
            System.out.println("{" + tri.a + ",\t" + tri.b + ",\t" + tri.c + "}");
        
        System.out.println("Time: " + MathFunctions.getElapledTime());
        
    }
    /*
    answer:
    Max perimeter: 840
    {40,    	399,	401}
    {56,        390,	394}
    {105,	360,	375}
    {120,	350,	370}
    {140,	336,	364}
    {168,	315,	357}
    {210,	280,	350}
    {240,	252,	348}
    {252,	240,	348}
    {280,	210,	350}
    {315,	168,	357}
    {336,	140,	364}
    {350,	120,	370}
    {360,	105,	375}
    {390,	56,	394}
    {399,	40,	401}
    Time: 562
    */
    
    private static class Triple {
        public int a;
        public int b;
        public int c;
        public Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

}
