/*
Given a string S, return the "reversed" string where all characters
that are not a letter stay in the same place, and all letters reverse their positions.

Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"


Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122
S doesn't contain \ or "
 */

// use tow pointers and then swap them if the first meet the letter.

class Solution {
    public static String reverseOnlyLetters(String S) {
        int length = S.length();
        int start  = 0;
        int end = length - 1;

        char[] ch = S.toCharArray();
        // there is no need to change the letters we have already changed
        // they are not meet at the middle. instead they meet at a point, where before the start and after the
        // end, all the letter has been changed.
        while (start < end){
            if (!Character.isLetter(ch[start])) start++;
            else if (!Character.isLetter(ch[end])) end--;
            else{
                char tmp = ch[start];
                ch[start] = ch[end];
                ch[end] = tmp;
                start++;
                end--;
            }
        }
        S = String.valueOf(ch);
        return S;
    }
}
// one thinf to notice, String cannot be changed. we need to change it
// we can change String to StringBuilder, or S.toCharArray

// 0ms, 100%

//____________

// for char[] string, you can use basic way to get and change the elements
// but for the String, you need to use String.charAt() to get string

// one loop to push all character into stack, and another loop to traversal from start to end

class Solution {
    public String reverseOnlyLetters(String S) {
        Stack<Character> letters = new Stack();

        for (int i = 0; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) letters.push(S.charAt(i));
        }
        // what's this?
        StringBuilder rS = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) rS.append(letters.pop());
            else rS.append(S.charAt(i));
        }
        return rS.toString();
    }
}
// 1ms 46.89%

// for reverse, using the stack is a good idea
