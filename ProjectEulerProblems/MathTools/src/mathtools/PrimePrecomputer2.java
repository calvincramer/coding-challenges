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

//random access file with each number exactly 64 bytes (long)
//can make it more space efficient by limiting size of number
//more space efficient by using unsigned integer
//using 48 bits per number (unsigned), 281,474,976,710,655 is the maximum number (6 bytes per number)
public class PrimePrecomputer2 {
    
    private static File numberFile = new File("C:\\Users\\Calvin\\primes2.txt");
    private static RandomAccessFile raf;
    
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private static Date currentDateTime = new Date();
    private static final Date startDate = new Date();
    
    private static double timeComputing = 0;
    private static double timeIO = 0;
    
    private static final int BUFFER_SIZE = 10000;
    private static final int BYTES_PER_NUM = 6;
    
    private static final long LARGEST_48_UNSIGNED = 281474976710655L;   //281 trillion
    
    private static final long STOP_AT = LARGEST_48_UNSIGNED - 10000000;  //only checks after buffer fills, so need wiggle room
    private static long saves = 0;
    
    
    /**
     * Main method to start processing
     * @param args not used
     */
    public static void main(String[] args) {
        continueCalculating();
    }

    
    /**
     * Continues processing
     */
    private static void continueCalculating() {
        System.out.println("STARTING PRIME PRECOMPUTER 2");
        System.out.println("location of prime file: ");
        System.out.println(numberFile.getAbsolutePath());
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
       
        //if file doesnt exist, create
        if (!numberFile.exists()) {
            System.out.println("primes.txt not found, creating file");
            createFile();
        }
        
        //get last prime computed
        long lastPrime = getLastPrimeComputed();
        System.out.println("starting at : " + lastPrime);
        
        //calcutation loop
        long[] primeBuffer = new long[BUFFER_SIZE];
        while (true) {
            MF.startTimer();    //time compute start
            long n = lastPrime + 1;
            for (int i = 0; i < primeBuffer.length; i++) {
                while (!MF.isPrimeByPrimes4(n))
                    n++;
                primeBuffer[i] = n;
                n++;
            }
            timeComputing += MF.getElapsedSeconds();
            
            //write primeBuffer to file
            MF.startTimer();    //IO time
            writeToEndOfFile(primeBuffer);
            timeIO += MF.getElapsedSeconds();
            
            //last prime saved
            currentDateTime = new Date();  //get current time and date
            double secondsFromStart = (currentDateTime.getTime() - startDate.getTime()) / 1000.0;
            System.out.println(saves + "\tlast saved: " + primeBuffer[primeBuffer.length-1] + "\t" 
                    + dateFormat.format(currentDateTime) + "\ttotal: " + MF.fixedWidthStringLeftJustified(MF.round(secondsFromStart, 3) + "", 10) 
                    + "\tcompute " + MF.round(timeComputing, 3) + "\tIO: " + MF.round(timeIO, 3));
            
            //update last prime
            lastPrime = primeBuffer[primeBuffer.length-1];    
            
            //testing
            saves++;
            if (n > STOP_AT)
                break;
        }
        
        System.out.println("STOPPING PRIME PRECOMPUTER 2");
        
    }
    
    
    /**
     * Creates a file to write to
     */
    private static void createFile() {
        numberFile = new File("C:\\Users\\Calvin\\primes2.txt");
        raf = null;
        try {
            raf = new RandomAccessFile(numberFile, "rw");
            //int[] bytes = longTo6Bytes(2);
            //for (int by : bytes)
            //    raf.writeShort(by);
            int[] bytes = longTo6Bytes(2);
            for (int by : bytes)
                raf.writeByte(by);
            
        } catch (Exception e) {
            System.out.println("error");
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
                raf = new RandomAccessFile(numberFile, "rw");
            
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
    public static long getLastPrimeComputed() {
        try {
            if (raf == null)
                raf = new RandomAccessFile(numberFile, "rw");
            
            //seek to end of file - 6 bytes
            raf.seek(raf.length() - BYTES_PER_NUM);
            
            //read
            byte[] data = new byte[6];
            raf.read(data);
            return bytesToLong(data);
            
            
        } catch (IOException e) {
            System.out.println("io exception writing to end of file");
            e.printStackTrace();
        }
        
        return -1;
    }
    
    
    /**
     * Returns a list of all primes under a maximum value (exclusive)
     * @param max input maximum
     * @return list of primes under maximum
     */
    public static List<Long> getPrimesUnder(long max) {
        
        if (max > getLastPrimeComputed()) {
            System.out.println("max is greater than the largest saved prime");
            return new ArrayList<>();
        }
        
        List<Long> primes = new ArrayList<>();
        
        try {
            if (raf == null) 
                raf = new RandomAccessFile(numberFile, "rw");
            
            raf.seek(0);
            long guessPn = (long) (max / Math.log(max));
            long end = guessPn * BYTES_PER_NUM;
            
            byte[] data = new byte[(int)end];
            raf.read(data);
            
            for (int i = 0; i < data.length; i += BYTES_PER_NUM)
                primes.add(bytesToLong(data, i, i + BYTES_PER_NUM));
            
            //check for under shooting on maximum
            long start = end;
            end += 100 * BYTES_PER_NUM;
            data = new byte[100 * BYTES_PER_NUM];
            while (primes.get(primes.size()-1) < max) {
                raf.seek(start);
                raf.read(data);
                
                for (int i = 0; i < data.length; i += BYTES_PER_NUM)
                    primes.add(bytesToLong(data, i, i + BYTES_PER_NUM));
                
                start = end;
                end += 100 * BYTES_PER_NUM;
            }
            
            
        } catch (IOException e) {
            System.out.println("ioexception in getPrimesUnder()");
            e.printStackTrace();
        }
        
        //check for overshooting
        if (primes.get(primes.size()-1) >= max) {
            while (primes.get(primes.size()-1) >= max)
                primes.remove(primes.size()-1);
            return primes;
        }
        
        return primes;
    }
    
    
    /**
     * Returns a list of all primes within a range (inclusive min, exclusive max)
     * @param min input minimum
     * @param max input maximum
     * @return primes in the range
     */
    public static List<Long> getPrimesInRange(long min, long max) {
        if (min < 2)
            min = 2;
        if (max > getLastPrimeComputed()) {
            System.out.println("max is greater than the largest saved prime");
            return new ArrayList<>();
        }
        
        List<Long> primes = new ArrayList<>();
        
        try {
            if (raf == null) 
                raf = new RandomAccessFile(numberFile, "rw");
            
            long guessStart = (long) (min / Math.log(min)) * BYTES_PER_NUM;
            if (guessStart < 0)
                guessStart = 0;
            long guessEnd = (long) (max / Math.log(max)) * BYTES_PER_NUM;
            if (guessEnd > raf.length())
                guessEnd = raf.length();
            
            //refine guesses
            byte[] tempNum = new byte[6];
            raf.seek(guessStart);
            raf.read(tempNum);
            while (bytesToLong(tempNum) < min) {
                guessStart += 6;
                raf.seek(guessStart);
                raf.read(tempNum);
            }
            while (bytesToLong(tempNum) >= min) {
                guessStart -= 6;
                raf.seek(guessStart);
                raf.read(tempNum);
            }
            guessStart += 6;
            
            raf.seek(guessEnd);
            raf.read(tempNum);
            while (bytesToLong(tempNum) < max) {
                guessEnd += 6;
                raf.seek(guessEnd);
                raf.read(tempNum);
            }
            while (bytesToLong(tempNum) >= max) {
                guessEnd -= 6;
                raf.seek(guessEnd);
                raf.read(tempNum);
            }
            guessEnd += 6;
            
            
            byte[] data = new byte[(int) (guessEnd - guessStart)];
            raf.seek(guessStart);
            raf.read(data);
            
            for (int i = 0; i < data.length; i += BYTES_PER_NUM)
                primes.add(bytesToLong(data, i, i + BYTES_PER_NUM));
            
        
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception in getPrimesInRange");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io exception in getPrimesInRange");
            e.printStackTrace();
        }
        
        
        
        return primes;
    }
    
    /**
     * Returns the nth prime
     * Note: the first prime is 2
     * @param n input number
     * @return nth prime
     */
    public static Long getNthPrime(long n) {
        
        if (n < 1)
            return -1L;
        
        n--;        //because the first prime is at the 0th byte
        long seekTo = n * BYTES_PER_NUM;

        try {
            if (raf == null)
                raf = new RandomAccessFile(numberFile, "rw");
            
            if (seekTo > raf.length())
                return -2L;
            
            raf.seek(seekTo);
            
            //read
            byte[] data = new byte[6];
            raf.read(data);
            return bytesToLong(data);
            
            
        } catch (IOException e) {
            System.out.println("io exception writing to end of file");
            e.printStackTrace();
        }
        
        return -3L;
    }
    
    
    /**
     * Returns the number of primes saved in the file
     * @return the number of primes in the file
     */
    
    public static Long getNumberOfPrimes() {
        if (!numberFile.exists()) 
            return 0L;
        
        try {
            if (raf == null)
                raf = new RandomAccessFile(numberFile, "rw");
            return raf.length() / BYTES_PER_NUM;
        } catch (FileNotFoundException e) {
            System.out.println("file not found excpetion while getting number of primes");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io exception while getting number of primes");
            e.printStackTrace();
        }
        return -2L;
    }
    
    
    /**
     * Prints the whole number file byte by byte, in binary, and calculates the value of each 48 bit unsigned integer
     */
    private static void printWholeFile() {
        try {
            if (raf == null)
                raf = new RandomAccessFile(numberFile, "rw");
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
//10 million from scratch
//5.411s
//3.926 MB