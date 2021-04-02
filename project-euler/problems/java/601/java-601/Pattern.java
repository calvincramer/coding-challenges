package problem601;

/**
* Represents a linear pattern of integers (strictly increasing) such as 3,5,7,9,...
*/
public class Pattern {

    long startNum;
    long step;

    public Pattern (long start, long step) {
        this.startNum = start;
        this.step = Math.abs(step);
        if (this.step == 0)
            this.step = 1;
    }

    /**
     * Numbers of numbers in the pattern than are in the range startNum (inclusive) to maximum (exclusive)
     * @param maximum
     * @return 
     */
    public long numbersInPattern(long maximum) {
        if (maximum < this.startNum)
            return 0;
        long guess = (long) Math.ceil((maximum - this.startNum) * 1.0 / step);
        long endMatch = this.startNum + (this.step * guess);

        if (endMatch + step >= maximum)
            return guess;

        if (endMatch >= maximum) {  //overshot
            while (endMatch >= maximum) {
                endMatch -= step;
                guess--;
            }
            return guess;
        }
        //undershot
        while (endMatch < maximum) {
            endMatch += step;
            guess++;
        }

        return guess-1;     //overshoots maximum one step
    }
}

