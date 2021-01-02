//[COMPLETED]

package p007;

public class P007 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("123: " + s.reverse(123));
        System.out.println("-123: " + s.reverse(-123));
        System.out.println("120: " + s.reverse(120));
        System.out.println("0: " + s.reverse(0));
        System.out.println("0: " + s.reverse(1));
        System.out.println("1534236469: " + s.reverse(1534236469));
        System.out.println("-2147483648: " + s.reverse(-2147483648));
        
        int x = -2147483648;
        int y = Math.abs(-2147483648);
        System.out.println(x);
        System.out.println(y);
        System.out.println(x-1);
    }
    
private static class Solution {
    public int reverse(int x) {

        long absNum = Math.abs((long) x);
        String str = "" + absNum;

        String reversed = "";
        while (str != null && str.length() > 0) {
            reversed = reversed + str.charAt(str.length() - 1);
            str = str.substring(0, str.length() -1);
        }

        while (reversed.length() > 0 && reversed.charAt(0) == '0')
            reversed = reversed.substring(1);

        if (x < 0)
            reversed = '-' + reversed;

        if (reversed == null || reversed.equals(""))
            return 0;
        
        long ans = Long.parseLong(reversed);
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE)
            return 0;
        else 
            return (int) ans;
    }
}

}
