package leetCode;

public class N_9_PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        var str = Integer.toString(x);
        var reverse = new StringBuilder(str).reverse().toString();

        return str.equals(reverse);
    }
}
