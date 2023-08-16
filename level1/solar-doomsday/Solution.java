import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int[] solution(int area) {
        List<Integer> res = new ArrayList<>();
        while (area > 0) {
            int temp = (int) Math.sqrt(area);
            if (temp * temp > area) temp--;
            res.add(temp * temp);
            area -= temp * temp;
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}

