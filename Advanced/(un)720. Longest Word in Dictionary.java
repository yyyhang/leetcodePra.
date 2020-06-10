/*
Given a list of strings words representing an English Dictionary,
find the longest word in words that can be built one character at a time by other words in words.
If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
 */

// brute force

class Solution {
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        // store all to the set
        for (String word: words) wordset.add(word);
        for (String word: words) {
            // check if this one can be the answer
            if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                // check if its all pre fix in the array
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                // update answer
                if (good) ans = word;
            }
        }
        return ans;
    }
}

//  10 ms, faster than 74.39%

// ++++++++++++++++++++++++++++++++++++++++ //

// Trie + Depth-First Search

/*
Put every word in a trie, then depth-first-search from the start of the trie,
only searching nodes that ended a word. Every node found (except the root,
which is a special case) then represents a word with all it's prefixes present.
We take the best such word.
 */

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word: words) {
            trie.insert(word, ++index); //indexed by 1
        }
        trie.words = words;
        return trie.dfs();
    }
}
class Node {
    char c;
    HashMap<Character, Node> children = new HashMap();
    int end;
    public Node(char c){
        this.c = c;
    }
}

class Trie {
    Node root;
    String[] words;
    public Trie() {
        root = new Node('0');
    }

    public void insert(String word, int index) {
        Node cur = root;
        for (char c: word.toCharArray()) {
            cur.children.putIfAbsent(c, new Node(c));
            cur = cur.children.get(c);
        }
        cur.end = index;
    }

    public String dfs() {
        String ans = "";
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            if (node.end > 0 || node == root) {
                if (node != root) {
                    String word = words[node.end - 1];
                    if (word.length() > ans.length() ||
                            word.length() == ans.length() && word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }
                for (Node nei: node.children.values()) {
                    stack.push(nei);
                }
            }
        }
        return ans;
    }
}

//  63 ms, faster than 5.57%


// ++++++++++++++++++++++++++++ //

class Solution {
    public String longestWord(String[] words) {
        Trie root = new Trie();
        root.insert("");
        for (String word : words) {
            root.insert(word);
        }
        return root.search();
    }

    class Trie {
        private Trie[] children;
        private String word;

        public Trie() {
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie parent = this;
            int len = word.length();
            int idx;
            for (int i = 0; i < len; i++) {
                idx = word.charAt(i) - 'a';
                if (parent.children[idx] == null) {
                    parent.children[idx] = new Trie();
                }
                parent = parent.children[idx];
            }
            parent.word = word;
        }

        public String search() {
            String ans = word;
            String res;
            for (Trie t : children) {
                if (t != null && t.word != null) {
                    res = t.search();
                    if (res.length() > ans.length()) {
                        ans = res;
                    }
                }
            }
            return ans;
        }
    }
}

// 9 ms, faster than 75.77%