/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */

// we use dfs

/*
things we need to care about:
1. how to check if this element is letter or not --> Character.isLetter()
2. how to convert between upper case and lower case --> XOR
3. if we add an instance into an array, we need to new a new one
4. how to change String to char[] --> .toCharArray()
 */

class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(S.toCharArray(), ans, 0);
        return ans;
    }

    private void dfs(char[] S, List<String> ans,int i) {
        if (i == S.length) {
            ans.add(new String(S));
            return;
        }
        // we dfs with current element
        dfs(S, ans, i + 1);
        // when we return back we check if we need to change curr element
        if (!Character.isLetter(S[i])) return;
        // change to curr element to lower case or upper case
        S[i] ^= 1 << 5;
        // dfs again
        dfs(S, ans, i + 1);
        // change it back to original form
        // actually this question no need to return back to the original form though
        // bcz we need to use these two status anyway. even we do not change it now, we will change it later
        S[i] ^= 1 << 5;
    }
}

//  1 ms, faster than 100.00%

/*
1<<5 = 32
ascii('a') = 96, ascii('A') = 64
96 - 64 = 32
which means, the only difference btwe upper and lower is the rightmost 6th binary number
e.g.  y = 01111001    Y = 01011001
      d = 01100100    D = 01000100
 */

// https://zxi.mytechroad.com/blog/searching/leetcode-784-letter-case-permutation/

// O(length*2^l), l = # of letters in the string
// this is bcz if we add the string to the ans, we need O(n)