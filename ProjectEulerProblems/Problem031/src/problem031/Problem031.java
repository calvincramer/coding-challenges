package problem031;

public class Problem031 {

    /*
    In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
    It is possible to make £2 in the following way:

    1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
    How many different ways can £2 be made using any number of coins?
    */
    public static void main(String[] args) {
        
        System.out.println("p1\tp2\tp5\tp10\tp20\tp50\tp100\tp200");

        long ways = 0;
        for (int p1 = 0; p1 <= 200/1; p1++) { //one pence coin
        for (int p2 = 0; p2 <= 200/2; p2++) { //two pence coin
        for (int p5 = 0; p5 <= 200/5; p5++) { //five pence coin
        for (int p10 = 0; p10 <= 200/10; p10++) { //ten pence coin
        for (int p20 = 0; p20 <= 200/20; p20++) { //twenty pence coin
        for (int p50 = 0; p50 <= 200/50; p50++) { //fifty pence coin
        for (int p100 = 0; p100 <= 200/100; p100++) { //one pound coin
        for (int p200 = 0; p200 <= 200/200; p200++) { //two pound coin
            int total = (1*p1)+(2*p2)+(5*p5)+(10*p10)+(20*p20)+(50*p50)+(100*p100)+(200*p200);
            if (total == 200) {
                ways++;
                System.out.println(p1 + "\t" + p2 + "\t" + p5 + "\t" + p10 + "\t" + p20 + "\t" + p50 + "\t" + p100 + "\t" + p200);
            }
        
        }}}}}}}}
        
        System.out.println("\nTotal ways: " + ways);
    }
    //answer: 73682 ways

}
