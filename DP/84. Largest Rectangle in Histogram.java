/*
Given n non-negative integers representing the histogram's bar height
where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10
 */

// https://leetcode.com/problems/largest-rectangle-in-histogram/

/*
we can use the same idea in question 85, O(n^2)

but we can use stack to realise O(n)
 */

// we push the value to the stack until the val less than the top of the stack
// but how to prove it is true
// reference https://www.youtube.com/watch?v=ZmnqCZp9bBs

class Solution_1 {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        // push stack
        for(i=0; i < heights.length;){
            if(stack.isEmpty() || heights[stack.peekFirst()] <= heights[i]){
                stack.offerFirst(i++);
            }else{
                int top = stack.pollFirst();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if(stack.isEmpty()){
                    // bcz heights[top] is the smallest element in the height array so far
                    // any elements before it bigger than it have been kicked off the stack
                    area = heights[top] * i;
                }
                //if stack is not empty then everythin from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else{
                    // i is the top's right, and input.peek() is the left bound
                    area = heights[top] * (i - stack.peekFirst() - 1);
                }
                if(area > maxArea){
                    maxArea = area;
                }
            }
        }
        // pop stack
        while(!stack.isEmpty()){
            int top = stack.pollFirst();
            if(stack.isEmpty()){
                area = heights[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else{
                area = heights[top] * (i - stack.peekFirst() - 1);
            }
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
}

// 6 ms, faster than 88.42%

//-----------------------------------//
// stack_2:
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++){
            // reach to the end
            int h = (i == len ? 0 : heights[i]);
            //stack return the top element in the stack
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                // if the element bigger than the element at the top of the stack
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                // why use i--
                i--;
            }
        }
        return maxArea;
    }
}

//  8 ms, faster than 62.36%



class Solution_2 {
    public int largestRectangleArea(int[] heights) {
        int answer = 0;
        for(int i = 0; i < heights.length; i++){
            int currentHeight = heights[i];
            int left = i, right = i;
            // we scan the right and the left, find a rectangle with current height
            // we scan to left
            while(left >= 0 && heights[left] >= currentHeight) left--;
            // then scan to the right
            while(right < heights.length && heights[right] >= currentHeight) right++;
            // area
            answer = Math.max(answer,((right -1) - (left + 1) + 1 )*currentHeight );
        }
        return answer;
    }
}

// 597 ms, faster than 16.88%
// O(n^2)

//-------------------------//

public class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return getMax(heights, 0, heights.length);
    }
    int getMax(int[] heights, int s, int e) {
        if (s + 1 >= e) return heights[s];
        int min = s;
        boolean sorted = true;
        for (int i = s; i < e; i++) {
            if (i > s && heights[i] < heights[i - 1]) sorted = false;
            if (heights[min] > heights[i]) min = i;
        }
        if (sorted) {
            int max = 0;
            for (int i = s; i < e; i++) {
                max = Math.max(max, heights[i] * (e - i));
            }
            return max;
        }
        int left = (min > s) ? getMax(heights, s, min) : 0;
        int right = (min < e - 1) ? getMax(heights, min + 1, e) : 0;
        return Math.max(Math.max(left, right), (e - s) * heights[min]);
    }
}
// : 0 ms, faster than 100.00%