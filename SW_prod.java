// You are given an integer array nums and two integers l and r. Your task is to 
// find the minimum positive energy produced by a sequence of operations. 
// Each operation corresponds to selecting a contiguous subarray of nums 
// whose length is between l and r (inclusive).

// The energy of a sequence is defined as the product of all the numbers in 
// the subarray. You need to find the sequence with the smallest positive energy.

// If no such sequence exists, return -1.

// Input Format:
// ---------------
// Line-1: Three space separated integers, N, L and R.
// Line-2: N space separated integers, array[].

// Output Format:
// -----------------
// An integer result, smallest positive energy.

// Sample Input-1:
// -----------------
// 4 2 3
// 2 -1 3 4

// Sample Output-1:
// -------------------
// 12

// Explanation:
// --------------
// The possible sequences of operations with lengths between l = 2 and r = 3 are:

// [2, -1] (not valid, energy = -2)
// [3, 4] (energy = 12)
// [2, -1, 3] (not valid, energy = -6)
// The sequence [3, 4] produces the smallest positive energy of 12. Hence, 
// the output is 12.

// Sample Input-2:
// -----------------
// 3 2 3
// -1 -3 2

// Sample Output-1:
// -------------------
// -1

// Explanation:
// No valid sequence produces a positive energy. Thus, the output is -1.

// Constraints:
// ============
// 1 ≤ nums.length ≤ 100
// 1 ≤ l ≤ r ≤ nums.length
// −1000 ≤ nums[i] ≤ 1000

import java.util.*;

public class SW_prod {
    public static long slide(int arr[],int k){
        long min_p = Integer.MAX_VALUE;
        long p =1;
        for(int i=0;i<k;i++){
            p*=arr[i];
        }
        if(p>0){
            min_p = Math.min(min_p,p);
        }
        int idx = 0;
        for(int j=k;j<arr.length;j++){
            if(arr[idx]!=0){
                p/=arr[idx];
            }
            idx++;
            p*=arr[j];
            if(p>0 && p<min_p){
                min_p = p;
            }
        }
        return min_p;
    }
    public static void main(String args[]){
        int n,l,r;
        long res = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        for(int i=l;i<=r;i++){
            long min_sum = slide(arr,i);
            if(min_sum<res){
                res = min_sum;
            }
        }
        if(res==Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(res);
    }
}

