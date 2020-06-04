/*
Given an array of integers nums, sort the array in ascending order.

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]


Constraints:

1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
 */

// quick sort:
// basic find the basic element, left move to find all bigger than basic
// then we recursive
// https://www.bilibili.com/video/av39093184/
class Solution_quickSort {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int l, int r) {
        // if we only have one element, we no need to change it
        if (l >= r) return;
        int mid = partition(nums, l, r);
        // we recursively sort the rest parts
        quickSort(nums, l , mid);
        quickSort(nums, mid + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        // this is the basic value
        int pivot = nums[l];
        // when the two pointers not meet each other
        while (l < r) {
            // use the right pointer to find the element less pivot
            while (l < r && nums[r] >= pivot) r--;
            // if we find one, we put it at the left side, where all elements will less than pivot
            // we maintain the value of the pivot, so we don't neet tot worry about cover the value
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            // swap the element to the rigth
            nums[r] = nums[l];
        }
        // after the pointer meet togather
        nums[l] = pivot;
        return l;
    }
}
// 4 ms, faster than 89.05% ; 46.1 MB, less than 48.98%
// skiils: keep one value, and then we space to change all others. finally put it back to the middle

//----------------------------------------//

//bubble sort

class Solution_bubble {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        bubbleSort(nums);
        return res;
    }
    // each time we find the largest element, and put it at the end of the array with elements n
    // then we sort the n-1 elemts
    private void bubbleSort(int[] nums) {
        for (int k = nums.length - 1; k >= 1; k--) {
            for (int i = 0; i < k; i++) {
                if (nums[i] > nums[i + 1]) swap(nums, i, i + 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        // how it can work ?
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
        // https://blog.csdn.net/Caysen/article/details/78129113
        // https://blog.csdn.net/yangqinjiang/article/details/90052280
    }
}

// Time Limit Exceeded

//----------------------------------------//
class Solution_insert {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        insertionSort(nums);
        return nums;
    }
    // every time, we choose the first unsorted element to insert into sorted part
    private void insertionSort(int[] nums) {
        // first element of unsorted part
        for (int i = 1; i < nums.length; i++) {
            // compare to insert
            for (int j = i; j >= 1; j--) {
                if (nums[j] >= nums[j - 1]) break;
                swap(nums, j, j - 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}

// 1999 ms, faster than 5.04%, 47.1 MB, less than 6.12%

//-------------------------------------//

// sekecting sort: search the smallest value in the unsorted part, and
// swap with first element of the unsorted part

class Solution_select {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        selectionSort(nums);
        return nums;
    }
    private void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // first element of unsorted part
            int minIndex = i;
            // find the smallest element
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            if (minIndex != i) swap(nums, i, minIndex);
        }
    }
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}

// 1014 ms, faster than 5.04%,  46.7 MB, less than 6.12%

// ------------------------------------------ //