import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.isEmpty()) return false;

                char x = st.pop();

                if (c == ')' && x != '(') return false;
                if (c == '}' && x != '{') return false;
                if (c == ']' && x != '[') return false;
            }
        }

        return st.isEmpty();
    }
}