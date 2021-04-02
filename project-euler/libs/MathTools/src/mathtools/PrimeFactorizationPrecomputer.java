package mathtools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Very fast for finding the prime factorization of a single number
 * @author Calvin
 */
public class PrimeFactorizationPrecomputer {
    private static String fileLocation = "C:\\Users\\Calvin\\primesFacorization.txt"; 
    private static File file = new File(fileLocation);
    private static RandomAccessFile raf;
    //file stores 48 bit numbers, starting from 0 to however large
    //each number represents its first factor
    //if a number is prime, then 1 will be stored
    //1 is stored for zero and one spots
    
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private static Date currentDateTime = new Date();
    private static final Date startDate = new Date();
    
    private static double timeComputing = 0;
    private static double timeIO = 0;
    
    private static final int BUFFER_SIZE = 100000;
    private static final int BYTES_PER_NUM = 6; //6*8=48 bits per number
    
    private static final long LARGEST_48_UNSIGNED = 281474976710655L;   //281 trillion
    
    private static final long STOP_AT = LARGEST_48_UNSIGNED - 10000000;  //only checks after buffer fills, so need wiggle room
    //private static final long STOP_AT = 2000;
    private static long writeCount = 0;
    
    
    /**
     * Main method to start processing
     * @param args not used
     */
    public static void main(String[] args) {
        //continueCalculating();
        testing();
    }

    
    /**
     * Continues processing
     */
    private static void continueCalculating() {
        System.out.println("STARTING PrimeFactorizationPrecomputer");
        System.out.println("location of primefactorization file: ");
        System.out.println(file.getAbsolutePath());
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
       
        //if file doesnt exist, create it
        if (!file.exists()) {
            System.out.println("primesFacorization.txt not found, creating file");
            createFile();
        }
        
        //get last prime computed
        long lastNumber = getLastNumberComputed();
        System.out.println("starting at : " + lastNumber);
        
        
        //long[] numberBuffer = new long[BUFFER_SIZE];        //stores the numbers
        long[] firstFactorBuffer = new long[BUFFER_SIZE];   //stores the first factor of the number
        //long[] combinedBuffer = new long[BUFFER_SIZE * 2];  //when we write to the file
        
        //calcutation loop
        while (true) {
            MF.startTimer();            //time compute start;     
                for (int i = 0; i < BUFFER_SIZE; i++) 
                    firstFactorBuffer[i] = MF.getFirstFactor(lastNumber + 1 + i);
            double timeComputeTemp = MF.getElapsedSeconds();
            timeComputing += timeComputeTemp;    //end computing

            //write combinedBuffer to file
            MF.startTimer();    //IO time
                writeToEndOfFile(firstFactorBuffer);
            double timeIOTemp = MF.getElapsedSeconds();
            timeIO += timeIOTemp;
            
            //print results
            if (writeCount % 32 == 0)
                System.out.println(MF.fixedWidthStringLeftJustified("save#", 10) 
                    + MF.fixedWidthStringLeftJustified("last # saved", 16)
                    + MF.fixedWidthStringLeftJustified("date and time", 26)
                    + MF.fixedWidthStringLeftJustified("total time", 16)
                    + MF.fixedWidthStringLeftJustified("single:", 8)
                    + MF.fixedWidthStringLeftJustified("compute", 8)
                    + MF.fixedWidthStringLeftJustified("IO", 8)
                    + MF.fixedWidthStringLeftJustified("total:", 8)
                    + MF.fixedWidthStringLeftJustified("compute", 8)
                    + MF.fixedWidthStringLeftJustified("IO", 8) );

            currentDateTime = new Date();  //get current time and date
            double secondsFromStart = (currentDateTime.getTime() - startDate.getTime()) / 1000.0;
            System.out.println(MF.fixedWidthStringLeftJustified(writeCount+"", 10) 
                + MF.fixedWidthStringLeftJustified((lastNumber + BUFFER_SIZE)+"", 16)
                + MF.fixedWidthStringLeftJustified(dateFormat.format(currentDateTime), 26)
                + MF.fixedWidthStringLeftJustified(MF.roundString(secondsFromStart, 3) + "", 16)
                + MF.fixedWidthStringLeftJustified("", 8)
                + MF.fixedWidthStringLeftJustified(MF.roundString(timeComputeTemp, 3), 8)
                + MF.fixedWidthStringLeftJustified(MF.roundString(timeIOTemp, 3), 8)
                + MF.fixedWidthStringLeftJustified("", 8)
                + MF.fixedWidthStringLeftJustified(MF.roundString(timeComputing, 3), 8)
                + MF.fixedWidthStringLeftJustified(MF.roundString(timeIO, 3), 8) );
            
            //update last number
            lastNumber = lastNumber + BUFFER_SIZE; 
            
            //testing
            writeCount++;
            if (lastNumber >= STOP_AT)
                break;
        }
        
        System.out.println("STOPPING PrimeFactorizationPrecomputer");
    }
    
    
    /**
     * Just for testing
     */
    private static void testing() {
        System.out.println("testing");
        /*
        for (long n = 0; n <= getLastNumberComputed(); n++) {
            long realAns = MF.getFirstFactor(n);
            long storedAns = PrimeFactorizationPrecomputer.getFirstFactor(n);
            if (realAns != storedAns) {
                System.out.println(n + ": real: " + realAns + "  stored: " + storedAns);
            }
        }
        */
        //long largest = getLastNumberComputed();
        /*
        long largest = 1000000;
        System.out.println("largest : " + MF.numberToString(largest));
        double storedTime = 0;
        double calcedTime = 0;
        for (int i = 0; i < 128000; i++) {
            Long rand = MF.randomLong(2, largest);
            
            MF.startTimer();
            List<Long> stored = getPrimeFactorization(rand);
            storedTime += MF.getElapsedSeconds();
            
            MF.startTimer();
            List<Long> calced = MF.getPrimeFactorization(rand);
            calcedTime += MF.getElapsedSeconds();
            
            if (!stored.equals(calced))
                System.out.println(rand + " : stored : " + stored + "  : calced : " + calced);
        }
        System.out.println("storedTime : " + storedTime);
        System.out.println("calcedTime : " + calcedTime);
        System.out.println("");
        System.out.println("calcTime/storedTime : " + (calcedTime/storedTime));
        
        */
        
        for (int size = 10; size <= 1000000000; size *= 10) {
            System.out.println("size = " + size);
            MF.startTimer();
            long[] precalc = getFirstFactorsUpTo(size);
            double time = MF.getElapsedSeconds();
            System.out.println("time = " + time);

            /*
            System.out.print("check? : ");
            for (int i = 0; i < precalc.length; i++) {
                if (precalc[i] != getFirstFactor(i))
                    System.out.println(i + "\t: " + precalc[i] + "\t" + getFirstFactor(i));
            }
            System.out.println("done");
            */
            
            System.out.println("");
        }
        
        
        
        System.out.println("testing done");
    }
    
    
    /**
     * WARNING -- A LOT SLOWER THAN MF.getPrimeFactorizationUnder --
     * Returned an array of lists of prime factorizations under the maximum (inclusive)
     * Indexed to the number. IE returnedList[24] = {2,2,2,3}
     * @param max maximum number inclusive
     * @return list of prime factorizations
     */
    public static List<Integer>[] getPrimeFactorizationsUnder(int max) {
        //throw new Exception("method not implemented yet, soz");
        //go from low to high
        //if a number has already been completed, copy that info
        // ie 128, = 2 * 64, 64 already has been completed, copy 64
        List<Integer>[] table = (ArrayList<Integer>[]) new ArrayList[max + 1];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i <= max; i++) {
            int current = i;
            int currentFF = (int) getFirstFactor(current);
            boolean copied = false;
            
            while (currentFF != 1 && current != 1) {
                table[i].add(currentFF);
                current = current / currentFF;
                currentFF = (int) getFirstFactor(current);
                
                if (!table[current].isEmpty()) {
                    table[i].addAll(table[current]);
                    copied = true;
                    break;
                }
            }
            if (!copied)
                table[i].add(current);
        }

        return table;
    }


    /**
     * WARNING -- A LOT SLOWER THAN MF.getPrimeFactorizationUnder --
     * Returned an array of lists of prime factorizations under the maximum (inclusive)
     * Indexed to the number. IE returnedList[24] = {2,2,2,3}
     * @param max maximum number inclusive
     * @return list of prime factorizations
     */
    public static List<Integer>[] getPrimeFactorizationsUnder2(int max) {
        //go from low to high
        //if a number has already been completed, copy that info
        // ie 128, = 2 * 64, 64 already has been completed, copy 64
        long[] firstFactors = getFirstFactorsUpTo(max);
        
        List<Integer>[] table = (ArrayList<Integer>[]) new ArrayList[max + 1];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i <= max; i++) {
            int current = i;
            int currentFF = (int) firstFactors[current];
            boolean copied = false;
            
            while (currentFF != 1 && current != 1) {
                table[i].add(currentFF);
                current = current / currentFF;
                currentFF = (int) firstFactors[current];
                
                if (!table[current].isEmpty()) {
                    table[i].addAll(table[current]);
                    copied = true;
                    break;
                }
            }
            if (!copied)
                table[i].add(current);
        }

        return table;
    }

    
    
    
    
    /**
     * Gets the prime factorization of the input number such that the product of the returned numbers equals the given number, and each returned number is prime
     * If the number is less than 2, null is returned
     * If the number is greater than the largest value stored, null will be printed and an error is thrown
     * For uniformly random numbers under 1 million, its around 1.1x faster
     * For uniformly random numbers under 10 million, its around 6x faster
     * For uniformly random numbers under 100 million, its around 25x faster
     * For uniformly random numbers under 1 billion, this method is around 65x as fast as MF.getPrimeFactorization()
     * More and more gains the higher the numbers
     * @param number input number
     * @return prime factorization of number
     */
    public static List<Long> getPrimeFactorization(long number) {
        if (number < 2)
            return null;
        else if (number > getLastNumberComputed()) {
            System.err.println(number + " has not been precomputed yet. Maximum precomputed is " + getLastNumberComputed());
            return null;
        }
        
        List<Long> list = new ArrayList<>();
        long current = number;
        long factorOfCurrent = getFirstFactor(current);
        while (factorOfCurrent != 1) {
            list.add(factorOfCurrent);
            current = current / factorOfCurrent;
            factorOfCurrent = getFirstFactor(current);
        }
        list.add(current);  //last prime number factor of original number
        
        return list; 
    }
    
    
    /**
     * Reads the first factor from the file. If the number is negative then -1 is returned.
     * Does not check for reading past end of file.
     * @param number input number
     * @return First factor of number
     */
    public static long getFirstFactor(long number) {
        if (number < 0)
            return -1;
        
        try {
            if (raf == null)
                raf = new RandomAccessFile(file, "rw");

            raf.seek(number * BYTES_PER_NUM);
            byte[] data = new byte[BYTES_PER_NUM];
            int result = raf.read(data);
            if (result == -1 || result != BYTES_PER_NUM) {
                System.err.println("error reading first factor, result = " + result);
                return -2;
            }
            return bytesToLong(data);
            
        } catch (IOException e) {
            System.out.println("error getting first factor");
            e.printStackTrace();
            return Long.MIN_VALUE;
        }
    }
    
    
    /**
     * Reads all of the first factors in the file up to the max'th number
     * @param max the maximum number inclusive, must be non-negative
     * @return array of first factors, indexed starting at 0
     */
    public static long[] getFirstFactorsUpTo(int max) {
        if (max < 0)
            return null;
        
        try {
            if (raf == null)
                raf = new RandomAccessFile(file, "rw");
            
            raf.seek(0);
            
            long[] toReturn = new long[max+1];
            byte[] data = new byte[(max+1) * BYTES_PER_NUM];
            int result = raf.read(data);
            
            if (result == -1 || result != data.length) {
                System.err.println("error reading " + max + " numbers from file, result = " + result);
                return null;
            }
            
            //convert data
            for (int i = 0; i < toReturn.length; i++) {
                byte[] tempNum = new byte[BYTES_PER_NUM];
                for (int j = 0; j < BYTES_PER_NUM; j++)
                    tempNum[j] = data[i * BYTES_PER_NUM + j];
                toReturn[i] = bytesToLong(tempNum);
            }
            return toReturn;
            
        } catch (IOException e) {
            System.out.println("error getting first factor");
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    
    
    
    
    
    /**
     * Creates a file to write to
     */
    private static void createFile() {
        file = new File(fileLocation);
        raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            //write 0,1, 1,1, 2,1 into the file
            //int[] bytesZero = longTo6Bytes(0);
            int[] bytesOne = longTo6Bytes(1);
            //int[] bytesTwo = longTo6Bytes(2);
            
            //for (int by : bytesZero)    raf.writeByte(by);
            for (int by : bytesOne)     raf.writeByte(by);
            
            //for (int by : bytesOne)     raf.writeByte(by);
            for (int by : bytesOne)     raf.writeByte(by);
            
            //for (int by : bytesTwo)     raf.writeByte(by);
            for (int by : bytesOne)     raf.writeByte(by);
            
        } catch (Exception e) {
            System.out.println("error in creating file");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Returns unsigned bytes (inside of ints) from a long
     * @param num
     * @return 
     */
    private static int[] longTo6Bytes(long num) {
        return new int[] {
            (int) (num >>> 40),
            (int) (num >>> 32),
            (int) (num >>> 24),
            (int) (num >>> 16),
            (int) (num >>> 8),
            (int) num
        };
    }
    
    
    /**
     * Writes an array of number to the end of the file
     * @param data 
     */
    private static void writeToEndOfFile(long[] data) {
        try {
            if (raf == null)
                raf = new RandomAccessFile(file, "rw");
            
            //seek to end of file
            raf.seek(raf.length());
            
            //write
            /*
            for (long datum : data) {
                int[] bytes = longTo6Bytes(datum);
                for (int by : bytes)
                    raf.writeByte(by);  
            }*/
            byte[] bytes = new byte[data.length * 6];
            int i = 0;
            for (long datum : data) {
                int[] temp = longTo6Bytes(datum);
                for (int r = 0; r < temp.length; r++) {
                    bytes[i+r] = (byte) temp[r];
                }
                i += 6;
            }
            raf.write(bytes);
            
            //its way faster to make the call to write with a byte array than to write individual byte writes
            
        } catch (IOException e) {
            System.out.println("io exception writing to end of file");
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * Returns the last prime saved in the file
     * @return the last prime in the file
     */
    public static long getLastNumberComputed() {
        try {
            if (raf == null)
                raf = new RandomAccessFile(file, "rw");
            
            return raf.length() / BYTES_PER_NUM - 1;
            
            
        } catch (IOException e) {
            System.out.println("io exception writing to end of file");
            e.printStackTrace();
        }
        
        return -1;
    }
    
    
    //important, but convienence methods
    
    /**
     * Prints the whole number file byte by byte, in binary, and calculates the value of each 48 bit unsigned integer
     */
    public static void printWholeFile() {
        try {
            if (raf == null)
                raf = new RandomAccessFile(file, "rw");
            raf.seek(0);

            byte[] data = new byte[6];
            while (raf.read(data) != -1) {
                for (byte by : data)
                    System.out.print(unsignedByteToBinary(by) + " ");
                System.out.println("\t = " + bytesToLong(data));
            }

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * Returns a string version of an unsigned byte in the form "0b########"
     * @param unsbyt
     * @return 
     */
    private static String unsignedByteToBinary(int unsbyt) {
        return "0b" + String.format("%8s", Integer.toBinaryString(unsbyt & 0xFF)).replace(' ', '0');
    }
    
    
    /**
     * Converts a byte array to a long
     * @param bytes
     * @return 
     */
    private static long bytesToLong(byte[] bytes) {
        long res = 0;
        for (int i = 0; i < bytes.length; i++) {
            res <<= 8;
            res |= (bytes[i] & 0xFF);
        }
        return res;
    }
    
    
    /**
     * Converts a byte array to a long, starting at start, and going a certain length of bytes
     * @param bytes
     * @return 
     */
    private static long bytesToLong(byte[] bytes, int start, int length) {
        long res = 0;
        for (int i = start; i < bytes.length && i < length; i++) {
            res <<= 8;
            res |= (bytes[i] & 0xFF);
        }
        return res;
    }
}