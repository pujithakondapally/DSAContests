// An Emirp Number is defined as a number for which the sum of all its prime divisors (including multiplicity) is equal to the number itself.

// For example:

// The number 4 has prime divisors 2 and 2 → sum = 2 + 2 = 4 → it's an Emirp Number.

// The number 6 has prime divisors 2 and 3 → sum = 2 + 3 = 5 ≠ 6 → not an Emirp Number.

// Your task is:
// Given a positive integer n, count how many Emirp Numbers exist strictly less than n.

// Note:

// A prime divisor is included as many times as it appears in the factorization.

// For example, for 8 → prime factorization is 2 × 2 × 2 → sum = 6 ≠ 8 → not an Emirp Number.

// Examples:

// Input: n = 6
// Output: 4
// Explanation: Emirp Numbers less than 6 are: 2, 3, 4, 5

// Input: n = 8
// Output: 5
// Explanation: Emirp Numbers less than 8 are: 2, 3, 4, 5, 7

// Input: n = 14
// Output: 7
// Explanation: Emirp Numbers less than 14 are: 2, 3, 4, 5, 7, 11, 13

import java.util.*;

public class EmirpNo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Input n
        System.out.println(countEmirp(n));
    }

    static int countEmirp(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isEmirp(i)) {
                count++;
            }
        }
        return count;
    }

    static boolean isEmirp(int num) {
        int original = num;
        int sum = 0;
        int i = 2;
        while (num > 1) {
            while (num % i == 0) {
                sum += i;
                num /= i;
            }
            i++;
            if (i * i > num) {
                if (num > 1) {
                    sum += num;
                    break;
                }
            }
        }
        return sum == original;
    }
}
