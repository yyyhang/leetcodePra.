/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        getPermutation (nums, new LinkedList<Integer>(), result, new HashSet<Integer>());
        return result;
    }

    public void getPermutation (int[] nums, List<Integer> curList, List<List<Integer>> result, HashSet<Integer> set) {
        if (curList.size() == nums.length) {
            // if the curr size is equal to the input size, it means we have reach to the result
            // note that if we need add a list, we need deep copy
            result.add(new LinkedList<Integer>(curList));
        } else {
            for (int idx = 0; idx < nums.length; idx++) {
                if (!set.contains(nums[idx])) {
                    curList.add(nums[idx]);
                    int last = curList.size() - 1;
                    set.add(nums[idx]);
                    getPermutation(nums, curList, result, set);
                    set.remove(nums[idx]);
                    curList.remove(curList.size()-1);
                }
            }
        }
    }
}

//  3 ms, faster than 24.67%

// ---------------------------------------- //

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        getPermutation(nums, new boolean[nums.length], new LinkedList<Integer>(), result);
        return result;
    }

    public void getPermutation(int[] nums, boolean[] used, List<Integer> curList, List<List<Integer>> result) {
        if (curList.size() == nums.length) {
            // if the curr size is equal to the input size, it means we have reach to the result
            // note that if we need add a list, we need deep copy
            result.add(new LinkedList<Integer>(curList));
        } else {
            // we choose a number that never appear
            int preNum = Integer.MIN_VALUE;
            for (int idx = 0; idx < nums.length; idx++) {
                if (used[idx] == false) {
                    preNum = nums[idx];
                    curList.add(nums[idx]);
                    used[idx] = true;
                    getPermutation(nums, used, curList, result);
                    // now we don't use this one anymore
                    used[idx] = false;
                    curList.remove(curList.size() - 1);
                }
            }
        }
    }
}

//  2 ms, faster than 50.31%

//-------------------------//

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums.length==0)
            return ans;
        List<Integer> list = new ArrayList();
        list.add(nums[0]);
        ans.add(list);
        return permute(ans, nums, 1);
    }

    public List<List<Integer>>  permute(List<List<Integer>> prev, int[] nums, int index){
        if(index==nums.length)
            return prev;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int number = nums[index];
        for(List<Integer> list : prev){
            int size = list.size();
            for(int i=0; i<=size; i++){
                List<Integer> l = new ArrayList<Integer>();
                l.addAll(list);
                l.add(i, number);
                ans.add(l);
            }
        }

        return permute(ans, nums, index+1);
    }
}
// 1 ms, faster than 91.03%

//-----------------//

class Solution {

    //list to store all the permutaions of list
    List<List<Integer>>res;
    public List<List<Integer>> permute(int[] nums) {
        //initializing the res
        res=new ArrayList<>();

        // visited array to keep track of indexes visited already
        boolean vis[]=new boolean[nums.length];

        //array to store the visited numbers
        int arr[]=new int[nums.length];

        //recursive function to store the results into res
        permute(nums,vis,0,nums.length,arr);

        //returning the product
        return res;
    }

    public void permute(int[] nums,
                        boolean vis[],
                        int cur,
                        int n,
                        int arr[]) {

        // just to make sure cur=current index is not more than n
        if(cur>n)return;

        //when all the elements have been stored in arr.
        if(cur==n){
            List<Integer>list=new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                list.add(arr[i]);
            }

            //returning after converting the array to list.
            res.add(list);
            return;
        }

        //loop for calculating all the posibble elements at index cur.
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                vis[i]=true;
                arr[cur]=nums[i];
                permute(nums,vis,cur+1,n,arr);
                vis[i]=false;
            }
        }
        return;
    }
}

// 0 ms, faster than 100.00%