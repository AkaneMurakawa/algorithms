package math.combine;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public void combine(ArrayList<String> teams, ArrayList<String> res, int m) {
        if (res.size() == m) {
            System.out.println(res);
            return;
        }

        for (int i = 0; i < teams.size(); i++) {
            ArrayList<String> newRes = (ArrayList<String>) res.clone();
            newRes.add(teams.get(i));
            combine(new ArrayList<String>(teams.subList(i + 1, teams.size())), newRes, m);
        }

    }
}

public class Combine {

    public static void main(String[] args) {
        ArrayList<String> teams = new ArrayList<>(Arrays.asList("t1", "t2", "t3"));
        new Solution().combine(teams, new ArrayList<>(), 2);
    }
}
