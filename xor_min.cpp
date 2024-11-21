// In a distant kingdom, there lived a wise king named XORon, famous for his vast collection of magical stones, each with unique powers. Legend had it that the true power of these stones could only be harnessed if they were combined in just the right way, their secret to minimizing chaos in the kingdom. But there was a catch: each stone carried a bit of unpredictability, and when combined, they created a magical force called "The XOR".

// One day, XORon gathered his kingdom's bravest adventurers and tasked them with solving a grand puzzle. They were each given N magical stones, with powers encoded as integers (Ai). Their goal? To make the power of all the stones, when combined using the XOR operation, as stable and low as possible. However, they had one secret trick up their sleeves: they could choose to discard one stone, but only one. The adventurers were puzzled: should they remove a stone, or would they get the minimum power just by combining them all?

// XORon warned them, "The power of the stones can be tricky. Sometimes, discarding a stone will stabilize their combined energy. But be careful—removing the wrong one could leave you with more chaos."

// Now, it’s your turn to guide these adventurers. Given multiple sets of stones, each described by their power levels (integers), you must calculate the minimum possible XOR after potentially discarding just one stone. Some sets are already stable, while others may need a wise decision on which stone to discard.

// You are given T test cases, each describing a set of magical stones.

// For each test case, you need to compute:

// The minimum possible XOR of all the stones after removing at most one stone.
// Do you discard one stone for stability, or do you keep them all? Only the bravest adventurers with sharp minds can unravel this mystery!

// Input Format

// The first line contains a single integer T, the number of test cases.

// Each test case consists of two lines:

// The first line contains an integer N, the number of stones in that set.
// The second line contains N space-separated integers, representing the power of each stone.
// Constraints

// 1 <= T <= 100
// 1 <= N <= 120
// 1 <= Ai <= 105

// Output Format

// For each test case, output a single integer - the minimum XOR that can be achieved after removing at most one stone.

// Sample Input 0

// 3
// 4
// 2 4 3 6
// 2
// 4 4
// 5
// 1 3 5 17 9
// Sample Output 0

// 0
// 0
// 14
// Explanation 0

// Test Case 1:

// The XOR of all the stones {2, 4, 3, 6} is 3.

// If we remove the stone with power 3, the XOR of the remaining stones {2, 4, 6} becomes 0, which is the minimum possible XOR.

// Test Case 2:

// The XOR of the stones {4, 4} is already 0, so no stone needs to be removed. The result is 0.

// Test Case 3:

// The XOR of the stones {1, 3, 5, 17, 9} is 31.

// If we remove the stone with power 17, the XOR of the remaining stones becomes 14, which is the minimum possible XOR.


#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int t;
    cin >> t;  // Read the number of test cases
    for (int i = 0; i < t; i++) {
        int n;
        cin >> n;  // Read the number of stones in the current test case
        vector<int> nums(n);
        int totalXOR = 0;

        // Read the power of each stone and calculate the XOR of all stones
        for (int j = 0; j < n; j++) {
            cin >> nums[j];
            totalXOR ^= nums[j];
        }

        // If the total XOR is already 0, it's the minimum possible XOR
        int minXOR = totalXOR;
        if (totalXOR == 0) {
            cout << 0 << endl;
            continue;
        }

        // Try removing each stone and calculate the new XOR
        for (int j = 0; j < n; j++) {
            // Remove the j-th stone by XORing totalXOR with nums[j]
            int currentXOR = totalXOR ^ nums[j];
            minXOR = min(minXOR, currentXOR);
        }

        // Output the minimum possible XOR
        cout << minXOR << endl;
    }
    return 0;
}
