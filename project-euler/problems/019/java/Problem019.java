package problem019;

public class Problem019 {

    /*
     * You are given the following information, but you may prefer to do some research for yourself.

    1 Jan 1900 was a Monday.
    Thirty days has September,
    April, June and November.
    All the rest have thirty-one,
    Saving February alone,
    Which has twenty-eight, rain or shine.
    And on leap years, twenty-nine.
    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
    How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
     */
    
    

    
    public static void main(String[] args) {
        Date date = new Date(Day.MON, Month.JAN, 1900);
        int numSundaysOnFirst = 0;
        while (date.year < 2001) {
      
            if (date.day == Day.SUN && date.dayNumber == 1 && date.year >= 1901) {
                numSundaysOnFirst++;
                System.out.println(date);
            }
            date.incrementDay();
            
        }
        System.out.println("Total: " + numSundaysOnFirst);

    }
    //answer: 171
    
    public static enum Day {
        MON, TUES, WED, THUR, FRI, SAT, SUN;
        private static Day[] days = Day.values();
        public Day nextDay() {
            return days[ (this.ordinal()+1) % days.length ];
        }
    }
    
    public static enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;
        private static Month[] months = Month.values();
        public Month nextMonth() {
            return months[ (this.ordinal()+1) % months.length ];
        }
        
        public int daysIn(int year) {
            switch (this) {
                case JAN: return 31;
                case FEB: {
                    if (isLeapYear(year))  //leap year
                        return 29;
                    else 
                        return 28;
                }  
                case MAR: return 31;  
                case APR: return 30;  
                case MAY: return 31;  
                case JUN: return 30;  
                case JUL: return 31;  
                case AUG: return 31;  
                case SEP: return 30;  
                case OCT: return 31;  
                case NOV: return 30;  
                case DEC: return 31;  
            }
            return -1;
        }
        
        public static boolean isLeapYear(int year) {
            if (year % 4 == 0 && year % 100 != 0)
                return true;
            else if (year % 400 == 0)
                return true;
            
            return false;
        }
    }

    public static class Date {
        
        private Day day;
        private int dayNumber;
        
        private Month month;
        private int year;
        
        public Date(Day day, Month month, int year) {
            this.day = day;
            this.dayNumber = 1;
            this.month = month;
            this.year = year;
        }
        
        public void incrementDay() {
            boolean moveMonth = dayNumber == month.daysIn(year);
            boolean moveYear = (month == Month.DEC) && moveMonth;
            
            if (moveMonth && moveYear) {
                day = day.nextDay();
                dayNumber = 1;
                month = month.nextMonth();
                year++;
            } 
            else if (moveMonth) {
                day = day.nextDay();
                dayNumber = 1;
                month = month.nextMonth();
            }
            else 
            {
                day = day.nextDay();
                dayNumber++;
            }
            
            
        }
        
        @Override
        public String toString() {
            return "" + year + " " + month + " " + day + " " + dayNumber;
        }
    }
    
}
