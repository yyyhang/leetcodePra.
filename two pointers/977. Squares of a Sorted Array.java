/*
Given an array of integers A sorted in non-decreasing order,
return an array of the squares of each number, also in sorted non-decreasing order.

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
 */

// first, we can use brute force. we can sort this array after we get the squares.

class Solution {
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i) ans[i] = A[i] * A[i];
        Arrays.sort(ans);
        return ans;
    }
}
// nlogn
// 2ms, 64.59%

// this is the 2 pointers solution

class Solution {
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int j = 0;
        // find the first positive/ non-negative number
        while (j < N && A[j] < 0)
            j++;
        // i point at the last negative number
        int i = j-1;

        int[] ans = new int[N];
        int t = 0;
        // pointers move to different direction respectively
        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }
        // append the rest things
        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }
        return ans;
    }
}

// 100%, 1ms

