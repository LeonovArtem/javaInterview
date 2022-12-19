package org.aleonov.javainteview.leetCode;

public class N_11_ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            var currentArea =  Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
}
