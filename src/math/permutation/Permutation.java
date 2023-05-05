package math.permutation;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    /*********************************************密码破解***************************************************/
    private static final String[] LETTER = new String[]{"a", "b", "c", "d", "e"};

    public static final String PWD = "dabc";

    /**
     * 密码全排列
     * @param res 排列结果
     */
    public void getPwd(ArrayList<String> res) {
        if (res.size() == PWD.length()) {
            System.out.println(res);
            return;
        }

        for (int i = 0; i < LETTER.length; i++) {
            ArrayList<String> newRes = (ArrayList<String>) res.clone();
            newRes.add(LETTER[i]);
            getPwd(newRes);
        }
    }

    /*********************************************田忌赛马***************************************************/

    // 齐王的马跑完所需时间
    private static final HashMap<String, Double> Q_HORSES_TIME = new HashMap() {
        {
            put("q1", 1.0);
            put("q2", 2.0);
            put("q3", 3.0);
        }
    };

    // 田忌的马跑完所需时间
    private static final HashMap<String, Double> T_HORSES_TIME = new HashMap() {
        {
            put("t1", 1.5);
            put("t2", 2.5);
            put("t3", 3.5);
        }
    };

    // 齐王出战的马匹
    private static final ArrayList<String> Q_HORSES = new ArrayList<String>() {
        {
            add("q1");
            add("q2");
            add("q3");
        }
    };

    // 田忌的马匹
    public static final ArrayList<String> T_HORSES = new ArrayList<String>() {
        {
            add("t1");
            add("t2");
            add("t3");
        }
    };

    /**
     * 递归调用，找出田忌所有可能的马匹出战顺序
     *
     * @param toMatchHorses 田忌待匹配马匹
     * @param res           匹配结果
     */
    public void permute(ArrayList<String> toMatchHorses, ArrayList<String> res) {
        if (toMatchHorses.size() == 0) {
            System.out.println(res);
            compare(res, Solution.Q_HORSES);
            return;
        }

        for (int i = 0; i < toMatchHorses.size(); i++) {
            // 从田忌未出战的马匹中选择一匹
            ArrayList<String> newRes = (ArrayList<String>) res.clone();
            newRes.add(toMatchHorses.get(i));
            // 将田忌已出战的马匹从待匹配中移除
            ArrayList<String> restHorses = (ArrayList<String>) toMatchHorses.clone();
            restHorses.remove(i);
            permute(restHorses, newRes);
        }
    }

    private void compare(ArrayList<String> tHorses, ArrayList<String> qHorses) {
        int tWonCnt = 0;
        for (int i = 0; i < tHorses.size(); i++) {
            if (T_HORSES_TIME.get(tHorses.get(i)) < Q_HORSES_TIME.get(qHorses.get(i))) tWonCnt++;
        }

        if (tWonCnt > tHorses.size() / 2) {
            System.out.println("田忌获胜");
        } else {
            System.out.println("齐王获胜");
        }
    }

}

public class Permutation {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.permute(Solution.T_HORSES, new ArrayList<>());
        solution.getPwd(new ArrayList<>());
    }
}
