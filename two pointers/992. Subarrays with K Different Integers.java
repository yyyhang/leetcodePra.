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
        // it has at most arr.length different elements
        short[] count = new short[arr.length+1];
        // distinct elements in the sliding windows
        short unique=0;
        int i=0, total=0, good=0;
        for(int j=0; j<arr.length; j++){
            // if it appears at the first time
            if(count[arr[j]]++==0) unique++;
            // bigger than K elements in the sliding windows
            if(unique>k){
                // we move the left
                count[arr[i++]]--;
                unique--;
                good=0;
            }
            //calcu number of the subarrays
            // if the left more than twice, move left
            // we cumulate the good, bcz even we move the pointer, but those arrays still is a sub array of new
            // in another words, left stands for how many subs from zero/prev to left
            // the reason why we change it when it appear than once bcz we want to keep the same number of elements
            // in the array. otherwise, we can use this way to calculate all arrays as well
            while(count[arr[i]]>1){
                count[arr[i++]]--;
                // in another words, even the left moves, the length is still same as it never moves
                good++;
            }
            // the sub arrays of this length
            if(unique==k) total += good+1;
        }
        return total;
    }
}

5 ms, faster than 84.19%

/*
just == k ==> atMost(K elements) - atMost(K-1 elements)
 */

// C++
/*
class Solution {
public:
    int subarraysWithKDistinct(vector<int>& A, int K)
    {
        return atMostK(A,K)-atMostK(A,K-1);
    }

    int atMostK(vector<int>&A, int K)
    {
        unordered_map<int,int>Map;
        int i=0;
        int count=0;
        for (int j=0; j<A.size(); j++)
        {
            Map[A[j]]++;
            if (Map.size()<=K)
                count+=j-i+1;
            else
            {
                while (Map.size()>K)
                {
                    Map[A[i]]--;
                    if (Map[A[i]]==0)
                        Map.erase(A[i]);
                    i++;
                }
                // as same ass the previous one
                count+=j-i+1;
            }
        }
        return count;
    }
};
 */