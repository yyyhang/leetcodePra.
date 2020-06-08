/*
Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.
The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas,
and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead,
and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.



Example 1:

Input: piles = [3,6,7,11], H = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], H = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], H = 6
Output: 23


Note:

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9
 */

/*
while the hours less than the piles

thinking:
we do binary search from 1 to the biggest pile, until we find the minimun
 */

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        Arrays.sort(piles);
        int max = piles[piles.length - 1];
        if (piles.length == H) return max;
        int left = 1;
        int right = max;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            for (int n : piles) {
                hours += n / mid;
                if (n % mid > 0) hours++;
            }
            // [1,2,3] 4
            // when obth point at right ans, we need right to change and return left
            if (hours > H) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
//  21 ms, faster than 26.99%

// if the while condition is i + 1 < j, we still need to check the calue of these two points out the loop. and
//    the pointer can be the middle;
// if the while condition is i < j. there is no need to chexk them after the loop,
//    but the pointer need to be mid + 1/ mid - 1 to avoid infinity, (else they may never equal)
// but still need to note return pointer or pointer - 1 or pointer + 1

// IMPORTANT: do not mix with pointer and array value; remember in which condition one ptr needs to be updated
// in moset cases, return left (while condition is i <= j, i+1, j-1), and if you want to return left,
// it cannot contains = to update; otherwise, after the left points to rigth ans, it will still increased byone.
// [1,2,3], find 2; [1,2,3] find 3
// [4,5] find 4

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int max = 0;
        // midifer here
        for (int pile : piles) max = pile > max ? pile : max;
        int left = 1;
        int right = max;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            for (int n : piles) {
                hours += n / mid;
                if (n % mid > 0) hours++;
            }
            if (hours > H) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}

// 16 ms, faster than 41.25%

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int max = 0;
        for (int pile : piles) max = pile > max ? pile : max;
        int left = 1;
        int right = max;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            // modifer here
            for (int n : piles) {
                hours += (n-1) / mid + 1;
            }
            if (hours > H) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
// 10 ms, faster than 72.92%
// Math.ceil(p / K) = ((p-1) // K) + 1

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        // idk why though
        // ++++++++
        long sum = 0;
        for (int p : piles) {
            sum += p;
        }
        int left = (int) (sum / H);
        left = left == 0 ? 1 : left;
        int right = (int) (sum / (H - piles.length + 1)) + 1;
        // +++++++++
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            for (int n : piles) {
                hours += (n-1) / mid + 1;
            }
            if (hours > H) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
// 1 ms, faster than 100.00%

/* my perference
while (left + 1 < right) {
            final int mid = left + (right - left) / 2;
            if (isValidTarget(piles, H, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (isValidTarget(piles, H, left)) {
            return left;
        }
        return right;

    private boolean isValidTarget(int[] piles, int H, int target) {
    int count = 0;
    for (int p : piles) {
        count +=  (p + target - 1) / target;
    }
    return count <= H;
}
 */

/*
 while(left < right){
            int mid = left + (right - left)/2;
            int cnt = countHours(piles, mid);
            if(cnt > H) left = mid + 1;
            else right = mid; //cnt <= H
        }
        return right;
 */