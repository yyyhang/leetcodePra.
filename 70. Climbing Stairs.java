/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */

//overlap subquestion, dynamic programming

// choose one or two step each time
// for example if the top is 5, it can only be reached from 4 or 3 (the last step)
// if it is reached from 4, then there are f(4) ways to climn to 5, otherwise is f(3)
// note: 3->4->5 is a way in f(4), f(3) means 3->5


class Solution {
    public int climbStairs(int n) {
        if (n == 1 || n == 0) return 1;
        int a = 1;
        int b = 1;
        int cur = 1;
        // since we only need to consider the previous two, we don't neet record all ways
        for (int i=2;i<=n;i++){
            cur = a+b;
            a = b;
            b = cur;
            //update the previous two
        }
        return cur;
    }
}

// for the run time, it is O(n)