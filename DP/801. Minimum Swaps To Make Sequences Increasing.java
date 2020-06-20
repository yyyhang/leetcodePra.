/*
We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are
in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.
(A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
 */

// we can do it at first with dfs, but it will TLE

class Solution {
    // put the answer here, and do not pass it into dfs
    int ans;

    public int minSwap(int[] A, int[] B) {
        ans = Integer.MAX_VALUE;
        dfs(A, B, 0, 0);
        return ans;
    }

    private void dfs(int[] A, int[] B, int i, int cnt) {
        if (cnt >= ans) return;
        // we reach the end
        if (i == A.length) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (i == 0 || A[i] > A[i-1] && B[i] > B[i - 1])
            dfs(A, B, i + 1, cnt);

        if (i == 0 || A[i] > B[i - 1] && B[i] > A[i - 1]) {
            swap(A, B, i);
            dfs(A, B, i + 1, cnt + 1);
            swap(A, B, i);
        }
    }

    private void swap (int[] A, int[] B, int i) {
        int tmp = A[i];
        A[i] = B[i];
        B[i] = tmp;
    }
}

// 84 / 102 test cases passed

// +++++++++++++++++++++++++++++++++++++ //

// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-801-minimum-swaps-to-make-sequences-increasing/
// why we use dp? this is we find there are some sub questions of this

// for each bit, we can keep or swap. we set 2 numbers as a group

class Solution {
    public int minSwap(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0, s1 = 1;
        for (int i = 1; i < A.length; ++i) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }
}

// 2 ms, faster than 58.18%
// O(N)

class Solution {
    public int minSwap(int[] A, int[] B) {
        int keep = 0, swap = 1;
        for(int i=1;i<A.length; i++) {
            int curKeep=Integer.MAX_VALUE, curSwap=Integer.MAX_VALUE;
            if(A[i]>A[i-1]&&B[i]>B[i-1]) {
                curKeep = keep;
                curSwap = swap+1;
            }
            if(A[i]>B[i-1]&&B[i]>A[i-1]) {
                curKeep = Math.min(curKeep, swap);
                curSwap = Math.min(curSwap, keep+1);
            }
            keep = curKeep;
            swap = curSwap;
        }
        return Math.min(keep,swap);
    }
}

// 1 ms, faster than 99.90%

/*

// Author: Huahua
// Running time: 15 ms
class Solution {
public:
  int minSwap(vector<int>& A, vector<int>& B) {
    const int n = A.size();

    vector<int> keep(n, INT_MAX);
    vector<int> swap(n, INT_MAX);

    keep[0] = 0;
    swap[0] = 1;

    for (int i = 1; i < n; ++i) {
      if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        // Good case, no swapping needed.
        keep[i] = keep[i - 1];

        // Swapped A[i - 1] / B[i - 1], swap A[i], B[i] as well
        swap[i] = swap[i - 1] + 1;
      }

      if (B[i] > A[i - 1] && A[i] > B[i - 1]) {
        // A[i - 1] / B[i - 1] weren't swapped.
        swap[i] = min(swap[i], keep[i - 1] + 1);

        // Swapped A[i - 1] / B[i - 1], no swap needed for A[i] / B[i]
        keep[i] = min(keep[i], swap[i - 1]);
      }
    }

    return min(keep.back(), swap.back());
  }
};
 */