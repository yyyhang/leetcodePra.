/*
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.



Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination:
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"


Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
 */

/*
Think of it as a graph problem.
Consider the pairs as connected nodes in the graph, what can you do with a connected component of indices ?
We can sort each connected component alone to get the lexicographically minimum string.

first, we build the graph according to pairs
and we sort each connected component and put it back to the original string at the same location
 */


// or we use union find

class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] parent = new int[s.length()];
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        // p is the pair[i1,i2]
        // we join the connecte component together by changing the value as its parent
        for (List<Integer> p: pairs) {
            parent[find(p.get(1), parent)] = find(p.get(0), parent);
        }

        Map<Integer, Queue<Character>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(find(i, parent), k -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < parent.length; i++) {
            res.append(map.get(parent[i]).poll());
        }
        return res.toString();

    }
    // find the parent of this node
    private int find(int i, int[] parent) {
        if (parent[i] != i) {
            parent[i] = find(parent[i], parent);
        }
        return parent[i];
    }
}

// 44 ms, faster than 65.26%

/*
Solution idea:

using paris to construct a union find object. for example: s = "dcab", pairs = [[0,3],[1,2]], UnionFind 0 - 3, 1 -2
build a string array, save every character's union find's root. Use it as group index. Example: indexs = {0, 1, 1, 0}
Create a map, key is an index, value is a PriorityQueue. Example: map = [ 0: {b, d}, 1: {a, c}]
Construct new string based on indexs and map. Example:

indexs: 0 1 1 0 index = 0
result: b

indexs: 0 1 1 0 index = 1
result: b a

indexs: 0 1 1 0 index = 2
result: b a c

indexs: 0 1 1 0 index = 3
result: b a c d
 */