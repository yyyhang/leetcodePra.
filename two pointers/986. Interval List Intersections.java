/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty,
or can be represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */

// for this question, we need to consider three situations of these two interval
// and we need to consider the interval as element in 1D array

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i =0, j = 0;
        while (i < A.length && j < B.length){
            // compare the start
            int lo = Math.max(A[i][0], B[j][0]);
            // compare the end
            int hi = Math.min(A[i][1], B[j][1]);
            // if they have intersection, we add it to the answer
            if (lo <= hi) ans.add(new int[]{lo,hi});
            // then we move to another interval
            if (A[i][1] < B[j][1]) i ++;
            else j++;
        }
        // return a 2d array
        return ans.toArray(new int[ans.size()][]);
    }
}

// 3ms, 78.13%