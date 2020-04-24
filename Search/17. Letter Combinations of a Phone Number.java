/*
Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

ps: the pic just look like your mobile phone keyboard with 9 keys

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

// DFS:
// at first, we build a data structure to store the relations
// for example, 2->3->4, we need to find all possible result
// we use the recursive method, and the end conditon is that it go though all numbers

// DFS using hashmap:
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<String>();
        if(digits == null || digits.length() == 0) return res;

        HashMap<Character, char[]> map = new HashMap <Character, char[]>();
        // HashMap<Character, char[]> map = new HashMap <>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});
        // but why here we use dynamic char[], can i just use char[] s = {blabla}?? is this i have to declare a varible?

        helper("", 0, digits, res, map);
        return res;
    }

    public void helper(String curr, int currIdx, String digits, List<String> res, HashMap<Character, char[]> map){
        if (currIdx == digits.length()){
            res.add(curr);
        } else {
            char c = digits.charAt(currIdx);
            if (map.containsKey(c)){
                for (char ch : map.get(c)){
                    helper(curr+ch, currIdx+1, digits, res, map);
                }
            }
        }

    }
}
//  faster than 38.18%



// DFS1
class Solution1 {
    public List<String> letterCombinations(String digits) {
        String[] d = new String[]{" ",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"};
        char[] cur = new char[digits.length()];
        List<String> ans = new ArrayList<>();
        dfs(digits, d, 0, cur, ans);
        return ans;
    }

    private void dfs(String digits, String[] d, int l, char[] cur, List<String> ans){
        if (l == digits.length()) {
            if (l > 0) ans.add(new String(cur));
            return;
        }
        // s will be "abc", "def" something
        String s = d[Character.getNumericValue(digits.charAt(l))];
        // e.g. Numeric value of a is 10, Numeric value of 4 is 4
        // here, from digits.charAt(l) we can get the number at position l
        // then we use Character.getNumericValue(thisNumber) to check if this number is valid or not, and convert it to integer
        // after that, we can get the relative string in the array as string = array[thisNumber]

        for (int i = 0; i < s.length();i++){
            //++i increments the number before the current expression is evaluted, whereas i++ increments the number after the expression is evaluated.
            // but in loop , there is no diff
            cur[l] = s.charAt(i);
            // cur is an array to record current string, ans is the array to record all finished cur
            // here, for example, cur is already [aj], then we could have [ajd] [aje] [ajf] to pass into dfs() each loop
            dfs(digits, d , l+1, cur, ans);
        }
    }
}
//  faster than 100%

// tips: the use of Character.getNumericValue(), String_instance.charAt()

/*
Read more:

The java.lang.Character.getNumericValue(char ch) returns the int value that the specified Unicode character represents.
For example, the character '\u216C' (the roman numeral fifty) will return an int with a value of 50.

The letters A-Z in their uppercase ('\u0041' through '\u005A'), lowercase ('\u0061' through '\u007A'),
and full width variant ('\uFF21' through '\uFF3A' and '\uFF41' through '\uFF5A') forms have numeric values from 10 through 35.
This is independent of the Unicode specification, which does not assign numeric values to these char values.

This method returns the numeric value of the character, as a nonnegative int value;

-2 if the character has a numeric value that is not a nonnegative integer;

-1 if the character has no numeric value.
 */


// BFS
class Solution2 {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<String>();

        String[] d = new String[]{" ",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"};
        List<String> ans = new ArrayList<>();
        ans.add("");
        // The method toCharArray() returns an Array of chars after converting a String into sequence of characters.
        for (char digit : digits.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            // clean tmp
            for (String t : ans) {
                String s = d[Character.getNumericValue(digit)];
                for (int i = 0; i < s.length(); i++)
                    tmp.add(t + s.charAt(i));
                // here, tmp will add all char for this digit
                // e.g. at first, tmp is [], t is a or b or c. let's say t='a', s="def" this turn,
                // then, tmp will be [ad, ae, af]
            }
            ans = tmp;
        }

        return ans;
    }

}
//  faster than 38.18%

// tips: use loops to realise queue;  toCharArray()

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        //HashMap <Character, String> map = new HashMap<>();
        HashMap<Character, String> map = new HashMap <>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        helper(res, map, digits, 0, "");
        return res;
    }

    public void helper( List<String> res, HashMap<Character, String> map, String digits, int l, String curr){
        if (l == digits.length()){
            res.add(curr);
        } else {
            char c = digits.charAt(l);
            if (map.containsKey(c)){
                for (char ch : map.get(c).toCharArray()){
                    helper(res, map, digits, l+1, curr+ch);
                    // do not forget to change the condition
                }
            }
        }
    }
}

class SolutionBFS {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        HashMap<Character, String> map = new HashMap <>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        res.add("");
        for (char digit : digits.toCharArray()){
            if (!map.containsKey(digit)) continue;
            List<String> tmp = new ArrayList<>();
            for (String ech : res){
                for (char curr : map.get(digit).toCharArray()){
                    tmp.add(ech+curr);
                }
            }
            res = tmp;
        }

        return res;
    }
}

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            // otherwise means all of the same length str have already add that char
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
    //Runtime: 5 ms, faster than 38.18%

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while(ans.peek().length()!=digits.length()){
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length())-'0'];
            for(char c: map.toCharArray()){
                ans.addLast(remove+c);
            }
        }
        return ans;
    }