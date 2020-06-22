import java.util.HashMap;
import java.util.List;

class Main{
    public static void main(String[] args) {
        // Solution res = new Solution();
        Integer t = 5;
        int i = t + 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(5,3);
        int sum = 2;
        map.put(sum, map.getOrDefault(sum,0)+1);
        sum = 5;
        map.put(sum, map.getOrDefault(sum,0)+1);

    }
}