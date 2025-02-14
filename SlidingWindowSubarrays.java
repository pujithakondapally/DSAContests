// Question:
// Given an array arr of positive integers and an integer limit, return the number of contiguous subarrays where the score of the subarray is strictly less than limit.

// Definition:
// The score of a subarray is calculated as:
// score
// =
// sum of subarray
// ×
// length of subarray
// score=sum of subarray×length of subarray
// The goal is to find the count of all valid contiguous subarrays where score < limit.


// Input:
// arr = [5, 10, 15, 20]
// limit = 200

// Output:
// 9

// Explanation:
// The valid subarrays are:
// [5], [10], [15], [20], [5,10], [10,15], [5,10,15], [15,20], [5,10,15,20]


// Input:
// arr = [1, 2, 3, 4]
// limit = 10

// Output:
// 6

// Explanation:
// The valid subarrays are:
// [1], [2], [3], [4], [1,2], [2,3]


import java.util.*;

public class SlidingWindowSubarrays {
    /**
     * Function to count contiguous subarrays where (sum * length) < limit
     */
    public static int countSubarrays(int[] arr, int limit) {
        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            // Shrink window if score exceeds limit
            while ((sum * (right - left + 1)) >= limit) {
                sum -= arr[left];
                left++;
            }

            // Count of valid subarrays ending at 'right'
            count += (right - left + 1);
        }

        return count;
    }

    /**
     * Main function to test the solution
     */
    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {5, 10, 15, 20};
        int limit1 = 200;
        System.out.println("Test Case 1 Output: " + countSubarrays(arr1, limit1)); // Expected Output: 9

        // Test Case 2
        int[] arr2 = {1, 2, 3, 4};
        int limit2 = 10;
        System.out.println("Test Case 2 Output: " + countSubarrays(arr2, limit2)); // Expected Output: 6

        // Test Case 3 (Edge case with small numbers)
        int[] arr3 = {2, 2, 2};
        int limit3 = 5;
        System.out.println("Test Case 3 Output: " + countSubarrays(arr3, limit3)); // Expected Output: 3

        // Test Case 4 (Large numbers)
        int[] arr4 = {100, 200, 300};
        int limit4 = 100000;
        System.out.println("Test Case 4 Output: " + countSubarrays(arr4, limit4)); // Expected Output: 6
    }
}
