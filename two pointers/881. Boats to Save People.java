/*
The i-th person has weight people[i], and each boat can carry a maximum weight of limit.

Each boat carries at most 2 people at the same time, provided the sum of the weight of
those people is at most limit.

Return the minimum number of boats to carry every given person.
(It is guaranteed each person can be carried by a boat.)



Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
Note:

1 <= people.length <= 50000
1 <= people[i] <= limit <= 30000
 */

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        // for the end , we need to use the length minus one
        int i = 0, j = people.length - 1;
        int cnt = 0;
        Arrays.sort(people);
        // think about the bound
        while (i <= j){
            if (people[i] + people [j] <= limit){
                i++;
                j--;
                cnt++;
            } else {
                j--;
                cnt++;
            }
        }
        return cnt;
    }
}

// 14ms, 88.94%

/*
for two pointer questions, we use
1. while loop
2. begin and end two pointers
3. might sort it (especially using greedy)
 */