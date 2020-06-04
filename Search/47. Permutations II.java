/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */

// for this one, we cannot use the hashmap, bcz the hashmap cannot tell the duplications

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Arrays.sort(nums); //BECAUSE WE MAY HAVE DUPLICATION
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        getPermutation (nums, new boolean[nums.length], new LinkedList<Integer>(), result);
        return result;
    }

    public void getPermutation (int[] nums, boolean[] used, List<Integer> curList, List<List<Integer>> result) {
        if (curList.size() == nums.length) {
            // if the curr size is equal to the input size, it means we have reach to the result
            // note that if we need add a list, we need deep copy
            result.add(new LinkedList<Integer>(curList));
        } else {
            // we choose a number that never appear at the bigining of theis depth
            int preNum = nums[0] - 1;
            // then we scan all of these numbers to get the value to add at this depth
            for (int idx = 0; idx < nums.length; idx++) {
                if (used[idx] == false && (nums[idx] != preNum)) {
                    // update the preNum incase we use same number at the same depth
                    preNum = nums[idx];
                    curList.add(nums[idx]);
                    used[idx] = true;
                    // next depth
                    getPermutation(nums, used, curList, result);
                    // now we don't use this one anymore, we use next element
                    used[idx] = false;
                    curList.remove(curList.size()-1);
                }
            }
        }
    }
}

//  1 ms, faster than 99.13%

// in order to avoid duplikcations, we can sort the array, and if at the same depth,
// we skip same number