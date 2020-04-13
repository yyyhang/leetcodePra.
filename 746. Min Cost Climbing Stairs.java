/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps.
You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0,
or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
(from 15, you can go to 20 or the end or the array, i.e. top)
but my question is, if there only one element in the array, do i need to return 0 or the index 1?
I tried the testcase. in this problem, we don't need to consider this situation.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 */

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int [] arr = new int[cost.length];
        arr[0] = cost[0];
        arr[1] = cost[1];
        // arr[0] = 0;
        // arr[1] = 0;
        // this is wrong. if we have two steps, we have to step at least one

        //min cost to i-th step including i, i.e. top
        for (int i=2;i<cost.length;i++){
            //why here is i< rather than i<= ??? bcz it starts from 0. otherwise, cost[length] will out of bounds
            arr[i] = Math.min(arr[i-1],arr[i-2]) + cost[i];
            // this def includes this step (but the issue is the top no needs to caculate its cost)
            // therefore, we can't just return arr[i]
        }
        return Math.min(arr[cost.length-1],arr[cost.length-2]);
        // the top can be reached from l-1 or l-2
        // l is the top
        // i = cost.length - 1
    }
}

// be careful about boundary, especially the length of array

/*
                               ___________
                          ___ | Final step
                     ___ | 20
                ___| 15
    _________ | 10
    First step

 */