package leetCode;

public class N_3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        var s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        else if (str.length() == 1) {
            return 1;
        }

        int maxLength = -1;
        StringBuilder tmp = new StringBuilder();
        for (char currentElement : str.toCharArray()) {
            String current = String.valueOf(currentElement);

            // If string already contains the character
            // Then substring after repeating character
            if (tmp.toString().contains(current)) {
                tmp = new StringBuilder(
                        tmp.substring(tmp.indexOf(current) + 1)
                );
            }
            tmp.append(currentElement);
            maxLength = Math.max(tmp.length(), maxLength);
        }

        return maxLength;
    }
}
