/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */


/*
we use hold[] and unhold[] to state the cash we have now;
for hold, it can only from 1. same as yesterday, we do nothing, but still hold the stock
       2. from the day before yesterday, that day hold nothing, and yesterday is possible cooldown
for unhold, 1. same as yesterday, still unhold;
            2. sell the stock yesterday held

            5   2  1  0  3
   hold   0 -5 -2 -1  0 -3
 unhold   0 0   0  0  0  3


for each day, we calculate the max cash we can get

why not change from unhold of yesterday to hold of today? for yesterday, it maybe cooldown, or same as the day before yesterday
 */

// https://www.youtube.com/watch?v=5mcq_V2-JlU

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] hold = new int[prices.length + 1];
        int[] unhold = new int[prices.length + 1];
        hold[1] = 0 - prices[0];
        for (int i = 2; i <= prices.length; i++) {
            hold[i] = Math.max(hold[i-1], unhold[i-2] - prices[i-1]);
            unhold[i] = Math.max(unhold[i-1], hold[i-1] + prices[i-1]);
        }
        return unhold[prices.length];
    }
}

// 1 ms, faster than 62.90%
//O(n)