/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

// we record the min price of the ith day before, and use prices[i] - min_price to see the profit. we only need the max profit
// bcz we only use curr day to minus the min_price, we will not use 7 - 1.
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length < 1) return 0;
        int min_price = prices[0];
        int max_profit = 0;
        // if we get a negative one, which less than 0, we will not record it.

        for (int i = 1; i < length; i++){
            min_price = Math.min(min_price, prices[i]);
            max_profit = Math.max(max_profit, prices[i]-min_price);
        }

        return max_profit;
    }
}

/*
another solution is to transfer the price into gain, and then we need to calculate the largest sub array
it is samilar to the question of 53

read more:
https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-121-best-time-to-buy-and-sell-stock/
 */