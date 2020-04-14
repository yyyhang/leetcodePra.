/*
Given n non-negative integers a1, a2, ..., an ,
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
 */

/*
at the beginning, set two pointers, one points to the first, one points to the last
(left is the left index, and right is the right index)
to get the possible maxarea, the shortest one needs to move to the next, and another one keeps on hold.
otherwise, if just move the big one, the area is also limited by the short one. and buz the length becomes shorter,
the area would be smaller.
however, if they are same, they can move at same time (optional)
 */

class Solution {
    public int maxArea(int[] height) {
        int maxA = 0;      //set to record max area
        int left = 0;
        int right = height.length - 1;
        while (right > left){
            int area = Math.min(height[right],height[left])*(right-left);
            maxA = Math.max(maxA,area);
            if (height[left]<height[right]){
                //int area = Math.min(height[right],height[left])*(right-left);
                // maxa = Math.max(maxa,area);
                left++;
            } else{
                //int area = Math.min(height[right],height[left])*(right-left);
                //maxa = Math.max(maxa,area);
                // if some code parts are same, can put it outside
                right--;
            }
        }
        return maxA;
    }
}

// time: O(n)
// type: two pointers