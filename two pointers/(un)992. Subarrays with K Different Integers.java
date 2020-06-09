/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
 */

// first i come up with sliding windows,and find the fast need to back
// but if every time the fast back to the position of slow when slow moves, it's O(n^2)

// similar to 340


class Solution {
    public int subarraysWithKDistinct(int[] arr, int k) {
        short[] count = new short[arr.length+1];
        short unique=0;
        int i=0, total=0, good=0;
        for(int j=0; j<arr.length; j++){
            if(count[arr[j]]++==0) unique++;
            if(unique>k){
                count[arr[i++]]--;
                unique--;
                good=0;
            }
            while(count[arr[i]]>1){
                count[arr[i++]]--;
                good++;
            }
            if(unique==k) total += good+1;
        }
        return total;
    }
}