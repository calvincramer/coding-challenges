package mathtools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.input.ReversedLinesFileReader;

//maximum prime: (using signed long data type) 9,223,372,036,854,775,808    64 bits     (2^63)
//4,611,686,018,427,387,904     63 bits
//2,305,843,009,213,693,952     62 bits
//1,152,921,504,606,846,976     61 bits
//576,460,752,303,423,488       60 bits
//288,230,376,151,711,744       59 bits
//144,115,188,075,855,872       58 bits
//72,057,594,037,927,936        57 bits
//36,028,797,018,963,968        56 bits
//18,014,398,509,481,984        55 bits
//9,007,199,254,740,992         54 bits
//4,503,599,627,370,496         53 bits
//2,251,799,813,685,248         52 bits
//1,125,899,906,842,624         51 bits
//562,949,953,421,312           50 bits

public class PrimePrecomputer {
    
    private static File numberFile = new File("C:\\Users\\Calvin\\primes.txt");
    private static BufferedWriter writer = null;
    private static BufferedReader reader = null;
    
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private static Date currentDateTime = new Date();
    private static final Date startDate = new Date();
    
    private static final int CHARS_PER_NUM = 19;    //max long is 18 characters
    private static final int NUMS_PER_LINE = 10;
    private static final int BUFFER_SIZE = 10000;
    private static final int BYTES_PER_LINE = CHARS_PER_NUM * NUMS_PER_LINE;
    
    
    private static final long STOP_AT = (long)Math.pow(10, 9);
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
        System.out.println("STARTING PRIME PRECOMPUTER");
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
            long n = lastPrime + 1;
            for (int i = 0; i < primeBuffer.length; i++) {
                while (!MF.isPrimeByPrimes4(n))
                    n++;
                primeBuffer[i] = n;
                n++;
            }
            //write primeBuffer to file
            writeToEndOfFile(primeBuffer);
            //last prime saved
            currentDateTime = new Date();  //get current time and date
            double secondsFromStart = (currentDateTime.getTime() - startDate.getTime()) / 1000.0;
            System.out.println(saves + "\tlast saved: " + primeBuffer[primeBuffer.length-1] + "\t" + dateFormat.format(currentDateTime) + "\t" + secondsFromStart + "s");
            //update last prime
            lastPrime = primeBuffer[primeBuffer.length-1];    
            
            //testing
            saves++;
            if (n > STOP_AT)
                break;
        }
        
        System.out.println("STOPPING PRIME PRECOMPUTER");
        
    }
    
    
    /**
     * Creates a file to write to
     */
    private static void createFile() {
        numberFile = new File("C:\\Users\\Calvin\\primes.txt");
        BufferedWriter writer = null;
        try {
            long[] primes = {2,3,5,7,11,13,17,19,23,29};
            writer = new BufferedWriter(new FileWriter(numberFile));
            for (long prime : primes) {
                writer.write(MF.fixedWidthStringLeftJustified(prime+"", CHARS_PER_NUM));
            }
            writer.newLine();
            
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Instantiates a reader
     */
    private static void createReader() {
        try {
            if (reader != null)
                reader.close();
            reader = null;
            reader = new BufferedReader(new FileReader(numberFile));
        }
        catch (FileNotFoundException e) {
            System.out.println("number file not found");
            e.printStackTrace();
        } 
        catch (IOException e) {
            System.out.println("io exception creating reader for file");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Instantiates a writer
     */
    private static void createWriter() {
        try {
            if (writer != null)
                writer.close();
            writer = null;
            writer = new BufferedWriter(new FileWriter(numberFile));
        }
        catch (IOException e) {
            System.out.println("io exception creating writer for file");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Writes an array of number to the end of the file
     * @param data 
     */
    private static void writeToEndOfFile(long[] data) {
        try(FileWriter fw = new FileWriter(numberFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            int count = 0;
            for (long datum : data) {
                out.print(MF.fixedWidthStringLeftJustified(datum+"", CHARS_PER_NUM));
                count++;
                if (count == NUMS_PER_LINE) {
                    out.println();
                    count = 0;
                }
            }
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
        //get last prime computed
        long lastPrime = -1;
        ReversedLinesFileReader read;
        try {
             //read = new ReversedLinesFileReader(numberFile);
             read = new ReversedLinesFileReader(numberFile, Charset.defaultCharset());
             String line = read.readLine();
             String[] splitNumbers = line.split(" +");  //regex " +" is any amount of whitespace (probably)
             String lastStr = splitNumbers[splitNumbers.length - 1];
             lastPrime = Long.parseLong(lastStr);
                 
            read.close();
        } catch (IOException e) {
            System.out.println("exception reading last line of file");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("the last prime in the file was not a valid number (not parseable)");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other exception while reading last prime in file");
            e.printStackTrace();
        }
        return lastPrime;
    }
    
    
    /**
     * Reads the nth line from the file
     * The first line contains the first line of primes (no header)
     * The 0th line is the first line of primes
     * credit: http://programming.guide/java/reading-nth-line-from-file.html
     * could not get the random access file to work, even though each line is verified to by 200 bytes long :(
     * @param n input number
     * @return the nth line in the file
     */
    public static List<Long> getNthLine(long n) {
        /*
        try {
            RandomAccessFile raf = new RandomAccessFile(numberFile, "r");   //r for read
            raf.seek(n * BYTES_PER_LINE);
            byte[] bytes = new byte[BYTES_PER_LINE];
            raf.read(bytes);
            
            String line = new String(bytes);
            String[] numsStr = line.split(" +");
            
            MF.printList(numsStr);
            
            
            List<Long> nums= new ArrayList<>();
            for (String numStr : numsStr)
                if ( !(numStr.isEmpty() || numStr.equals("\n") || numStr.equals("") ) )
                    nums.add(Long.parseLong(numStr.trim()));
            return nums;
            
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io exception while reading nth line (" + n + ") line");
            e.printStackTrace();
        }
        
        return new ArrayList<>();
        */
        
        String line = "";
        try (Stream<String> lines = Files.lines(Paths.get(numberFile.getAbsolutePath()))) {
            line = lines.skip(n).findFirst().get();
        } catch (IOException e) {
            System.out.println("io exception while reading nth line (" + n + ") line");
            e.printStackTrace();
        }
        String[] numsStr = line.split(" +");
        List<Long> nums= new ArrayList<>();
        for (String numStr : numsStr)
            if ( !(numStr.isEmpty() || numStr.equals("\n") || numStr.equals("") ) )
                nums.add(Long.parseLong(numStr.trim()));
        return nums;

    }
    
    
    /**
     * Returns a list of all primes under a maximum value (exclusive)
     * As it stands, this is not much faster than does Erotothenes sieve
     * Can probably make it faster by reading into a buffer, rather than line by line.
     * @param max input maximum
     * @return list of primes under max
     */
    public static List<Long> getPrimesUnder(long max) {
        
        if (max > getLastPrimeComputed()) {
            System.out.println("max is greater than the largest saved prime");
            return new ArrayList<>();
        }
        
        List<Long> primes = new ArrayList<>();
        
        createReader(); //sets reader to start of file
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numsStr = line.split(" +");
                boolean reachedEnd = false;
                for (String numStr : numsStr) {
                    if ( !(numStr.isEmpty() || numStr.equals("\n") || numStr.equals("") ) ) {
                        long temp = Long.parseLong(numStr.trim());
                        if (temp < max)
                            primes.add(temp);
                        else {
                            reachedEnd = true;
                            break;
                        }
                    }
                }
                if (reachedEnd)
                    break;
            }
        } catch (IOException e) {
            System.out.println("ioexception in getPrimesUnder()");
            e.printStackTrace();
        }
        
        
        
        /*
        long lineNumber = 0;
        List<Long> currentLine = getNthLine(lineNumber);
        while (currentLine.get(currentLine.size()-1) < max) {
            primes.addAll(currentLine);
            lineNumber++;
            currentLine = getNthLine(lineNumber);
        }
        //add the rest of current line under max
        for (Long temp : currentLine) {
            if (temp < max)
                primes.add(temp);
            else
                break;
        }
        */
        return primes;
    }
    
    
    /**
     * Returns a list of all primes within a range (inclusive min, exclusive max)
     * @param min minimum input
     * @param max maximum input
     * @return list of primes in-between 
     */
    public static List<Long> getPrimesInRange(long min, long max) {
        if (min < 2)
            min = 2;
        if (max > getLastPrimeComputed()) {
            System.out.println("max is greater than the largest saved prime");
            return new ArrayList<>();
        }
        
        
        
        long guessStart = (long) (min * 1.0 / Math.log(min));
        if (guessStart < 0)
            guessStart = 0;
        long guessStartLine = guessStart / NUMS_PER_LINE;
        
        
                
        if (getNthLine(guessStartLine).get(0) > min) { //traverse down
            guessStartLine--;
            List<Long> currentLine = getNthLine(guessStartLine);
            while (currentLine.get(0) > min) {
                guessStartLine--;
                currentLine = getNthLine(guessStartLine);
            }

        } else if (getNthLine(guessStartLine).get(getNthLine(guessStartLine).size()-1) < min){    //traverse up to min
            guessStartLine++;
            List<Long> currentLine = getNthLine(guessStartLine);
            while (currentLine.get(currentLine.size()-1) < min) {
                guessStartLine++;
                currentLine = getNthLine(guessStartLine);
            }
        }
        //not guessStartLine starts with min or less, but contains min
        List<Long> primes = new ArrayList<>();
        long lineNumber = guessStartLine;
        
        for (Long temp : getNthLine(lineNumber)) {
            if (temp < min)
                continue;
            else if (temp < max)
                primes.add(temp);
        }
        
        //traverse up to max
        lineNumber++;
        List<Long> currentLine = getNthLine(lineNumber);
        while (currentLine.get(currentLine.size()-1) < max) {
            primes.addAll(currentLine);
            lineNumber++;
            currentLine = getNthLine(lineNumber);
        }
        //add the rest of current line under max
        for (Long temp : currentLine) {
            if (temp < max)
                primes.add(temp);
            else
                break;
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
        
        long lastPrime = numberOfLinesInFile() * NUMS_PER_LINE;
        if (n > lastPrime)
            return -2L;
        
        n--;
        
        List<Long> primesAround = getNthLine(n / NUMS_PER_LINE);
        return primesAround.get((int) (n % NUMS_PER_LINE));
    }
    
    
    /**
     * Returns the number of primes saved in the file
     * @return number of primes in the file
     */
    public static Long getNumberOfPrimes() {
        if (!numberFile.exists()) 
            return 0L;
        return numberOfLinesInFile() * NUMS_PER_LINE;
    }
    
    
    /**
     * credit: https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
     * @return the number of lines in the file
     */
    public static Long numberOfLinesInFile() {
        try {
            LineNumberReader  lnr = new LineNumberReader(new FileReader(numberFile));
            lnr.skip(Long.MAX_VALUE);
            lnr.close();
            return (long) lnr.getLineNumber();
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception in numberOfLinesInFile");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io exception in numberOfLinesInFile");
            e.printStackTrace();
        }
        return -1L;
    }
}
//to do:
//cap cpu usage at certain percentage? -- executing for 0.2 seconds, resting for 0.8 seconds?

//scratch to 10000000
//6.278s
//12.563 MB